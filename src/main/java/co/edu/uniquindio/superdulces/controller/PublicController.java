package co.edu.uniquindio.superdulces.controller;

import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.accountDTO.CreateAccountDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.LoginDTO;
import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.services.interfaces.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<MessageDTO<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws AccountException {
        TokenDTO tokenDTO = accountService.logIn(loginDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,tokenDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDTO<String>> save (@Valid @RequestBody CreateAccountDTO  createAccountDTO) throws AccountException {
        try {
            accountService.addUserAccount(createAccountDTO);
            return ResponseEntity.ok(new MessageDTO<>(false,"Account created successfully"));
        }catch (AccountException e){
            return ResponseEntity.badRequest().body(new MessageDTO<>(true,e.getMessage()));
        }
    }
    @PostMapping("/verify-code")
    public ResponseEntity<MessageDTO<String>> validarCodigo(@Valid @RequestBody ValidateCodeDTO validateCodeDTO) throws AccountException {
        try {
            accountService.validateCode(validateCodeDTO);
            String message= "Cuenta activada con exito";
            return ResponseEntity.ok(new MessageDTO<>(false,message));
        }catch (AccountException cx){
            return ResponseEntity.badRequest().body(new MessageDTO<>(true,cx.getMessage()));
        }
    }

}