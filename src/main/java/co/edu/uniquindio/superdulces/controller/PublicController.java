package co.edu.uniquindio.superdulces.controller;

import co.edu.uniquindio.superdulces.dto.accountDTO.CreateAccountDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.LoginDTO;
import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.services.interfaces.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
        accountService.addUserAccount(createAccountDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,"Account created successfully"));
    }


}