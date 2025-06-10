package co.edu.uniquindio.superdulces.dto.CodesDTO;

import jakarta.validation.constraints.NotBlank;

public record ValidateCodeDTO(
        @NotBlank String email,
        @NotBlank String code
) {
}
