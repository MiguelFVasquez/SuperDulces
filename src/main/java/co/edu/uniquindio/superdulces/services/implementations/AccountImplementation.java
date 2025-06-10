package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.AccountInformationDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.CreateAccountDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.model.documents.Account;
import co.edu.uniquindio.superdulces.services.interfaces.AccountService;

public class AccountImplementation implements AccountService {
    @Override
    public Account addUserAccount(CreateAccountDTO account) throws AccountException {
        return null;
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
}
