package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.AccountInformationDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.CreateAccountDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.model.documents.Account;
import co.edu.uniquindio.superdulces.model.entitys.User;
import co.edu.uniquindio.superdulces.model.entitys.ValidationCode;
import co.edu.uniquindio.superdulces.model.enums.AccountState;
import co.edu.uniquindio.superdulces.repositories.AccountRepository;
import co.edu.uniquindio.superdulces.services.interfaces.AccountService;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;


@AllArgsConstructor
public class AccountImplementation implements AccountService {
    //Repositories
    private final AccountRepository accountRepository;
    //Vars
    private static final String CHARACTERS = "ABCDEFGHIJKMNOPQRSTUVWXYZ1234567890";
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
        ValidationCode code= new ValidationCode();
        String idUser= String.valueOf(new ObjectId());
        Account newAccount = Account.builder()
                .id(idUser)
                .email(account.email())
                .password(encryptedPassword)
                .user(new User(account.idUser(),account.name(),account.phoneNumber()))
                .validationCode(code)
                .state(AccountState.INACTIVE)
                .build();
        // TODO send email
        accountRepository.save(newAccount);
    }

    @Override
    public ValidateCodeDTO validateCode(ValidateCodeDTO validateCodeDTO) throws Exception {
        return null;
    }

    @Override
    public AccountInformationDTO getAccountInformation(String idUser) throws AccountException {
        return null;
    }

    @Override
    public TokenDTO logIn(String token) throws AccountException {
        return null;
    }

    //---------------JWT METHOD----------------
    public String encriptarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode( password );
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
}
