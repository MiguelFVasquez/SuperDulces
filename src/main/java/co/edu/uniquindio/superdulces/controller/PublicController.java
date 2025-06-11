package co.edu.uniquindio.superdulces.controller;

import co.edu.uniquindio.superdulces.dto.authDTO.LoginDTO;
import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.dto.authDTO.TokenDTO;
import co.edu.uniquindio.superdulces.exceptions.AccountException;
import co.edu.uniquindio.superdulces.services.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
