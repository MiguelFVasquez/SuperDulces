package co.edu.uniquindio.superdulces.dto.accountDTO;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountDTO(
        @NotBlank String idUser,
        @NotBlank String name,
        @NotBlank String phoneNumber,
        @NotBlank String email,
        @NotBlank String password
) {
}
