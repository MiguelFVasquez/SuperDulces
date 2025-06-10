package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.configDTO.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
