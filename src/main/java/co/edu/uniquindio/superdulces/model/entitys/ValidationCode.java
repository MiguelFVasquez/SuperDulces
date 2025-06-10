package co.edu.uniquindio.superdulces.model.entitys;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ValidationCode {
    private LocalDateTime creationDate;
    private String code;

}
