package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.CodesDTO.ValidateCodeDTO;
import co.edu.uniquindio.superdulces.dto.configDTO.EmailDTO;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendEmail(EmailDTO emailDTO) throws Exception;
}
