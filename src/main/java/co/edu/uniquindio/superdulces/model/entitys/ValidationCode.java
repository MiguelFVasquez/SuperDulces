package co.edu.uniquindio.superdulces.model.entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ValidationCode {
    private LocalDateTime creationDate;
    private String code;

}
