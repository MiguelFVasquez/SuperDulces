package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.config.JWTUtils;
import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.AccountInformationDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.CreateAccountDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.LoginDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.dto.configDTO.EmailDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.model.documents.Account;
import co.edu.uniquindio.superdulces.model.entitys.User;
import co.edu.uniquindio.superdulces.model.entitys.ValidationCode;
import co.edu.uniquindio.superdulces.model.enums.AccountState;
import co.edu.uniquindio.superdulces.model.enums.Rol;
import co.edu.uniquindio.superdulces.repositories.AccountRepository;
import co.edu.uniquindio.superdulces.services.interfaces.AccountService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import co.edu.uniquindio.superdulces.services.interfaces.EmailService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@AllArgsConstructor
@Transactional
@Service
public class AccountImplementation implements AccountService {
    //Repositories
    private final AccountRepository accountRepository;
    private final EmailService emailService;
    //Vars
    private static final String CHARACTERS = "ABCDEFGHIJKMNOPQRSTUVWXYZ1234567890";
    private final JWTUtils jwtUtils;
    private static final int CHARACTERS_LENGTH = CHARACTERS.length();


    @Override
    public void addUserAccount(CreateAccountDTO account) throws AccountException {
        Optional<Account> currentAccount = accountRepository.getByEmail(account.email());
        if (currentAccount.isPresent()) {
            throw new AccountException("Account with the email" + account.email() +" already exists");
        }
        if (account.password().length() < 8) {
            throw new AccountException("Password must use minimum 8 characters  ");
        }
        String encryptedPassword = encriptarPassword(account.password());
        ValidationCode code= new ValidationCode(LocalDateTime.now(), generateValidationCode());
        String idUser= String.valueOf(new ObjectId());
        Account newAccount = Account.builder()
                .id(idUser)
                .email(account.email())
                .password(encryptedPassword)
                .user(new User(account.idUser(),account.name(),account.phoneNumber()))
                .validationCode(code)
                .state(AccountState.INACTIVE)
                .rol(Rol.CLIENT)
                .build();
        try {
            emailService.sendEmail(new EmailDTO(
                    "Validation Code",
                    "<h1>Validation Code</h1>" +
                            "<p>Your validation code is: <strong>" + code.getCode() + "</strong></p>" +
                            "<p>This code will expire in 15 minutes.</p>",
                    account.email()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        accountRepository.save(newAccount);
    }

    @Override
    public ValidateCodeDTO validateCode(ValidateCodeDTO validateCodeDTO) throws AccountException {
        Account account = getAccountByEmail(validateCodeDTO.email());
        //If the code is incorrect
        if (!account.getValidationCode().getCode().equals(validateCodeDTO.code())){
            throw new AccountException("The code of the account does not match");
        }
        if (!account.getValidationCode().getCreationDate().plusMinutes(15).isBefore(LocalDateTime.now())){
            throw new AccountException("The code of the account has expired");
        }
        account.setState(AccountState.ACTIVE);
        account.setValidationCode(null);
        accountRepository.save(account);
        return validateCodeDTO;
    }

    @Override
    public AccountInformationDTO getAccountInformation(String idUser) throws AccountException {
        Account account = getAccountByEmail(idUser);
        return new AccountInformationDTO(
            account.getUser().getName(), account.getEmail(),account.getId());
    }

    @Override
    public Account deleteAccount(String email) throws AccountException {
        Account account = getAccountByEmail(email);
        account.setState(AccountState.DELETED);
        return accountRepository.save(account);
    }

    @Override
    public TokenDTO logIn(LoginDTO loginDTO) throws AccountException {
        Account account = getAccountByEmail(loginDTO.email());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if( !passwordEncoder.matches(loginDTO.password(), account.getPassword()) ) {
            throw new AccountException("The password is incorrect");
        }
        if(account.getState()!=AccountState.ACTIVE){
            throw new AccountException("Your account is inactive");
        }
        Map<String, Object> map = construirClaims(account);
        return new TokenDTO( jwtUtils.genereteToken(account.getEmail(), map));
    }

    //---------------JWT METHOD----------------
    public String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );
    }
    private Map<String, Object> construirClaims(Account account) {
        return Map.of(
                "rol", account.getRol(),
                "email", account.getEmail(),
                "id", account.getId()
        );
    }
    //----------Private methods----------------
    private String generateValidationCode() {
        char[] code = new char[6];
        for (int i = 0; i < 6; i++) {
            int index = ThreadLocalRandom.current().nextInt(CHARACTERS_LENGTH);
            code[i] = CHARACTERS.charAt(index);
        }
        return new String(code);
    }

    /**
     *
     * @param email
     * @return
     * @throws AccountException
     */
    private Account getAccountByEmail(String email) throws AccountException {
        return accountRepository.getByEmail(email).orElseThrow(()->new AccountException("Account with the email " + email +" does not exist"));
    }

}
