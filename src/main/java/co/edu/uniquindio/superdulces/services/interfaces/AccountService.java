package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.AccountInformationDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.CreateAccountDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.model.documents.Account;

public interface AccountService {

    void addUserAccount(CreateAccountDTO account) throws AccountException;
    ValidateCodeDTO validateCode(ValidateCodeDTO validateCodeDTO) throws Exception;
    AccountInformationDTO getAccountInformation(String idUser)  throws AccountException;
    TokenDTO logIn(String token) throws AccountException;

}
