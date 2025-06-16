package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.configDTO.EmailDTO;
import co.edu.uniquindio.superdulces.services.interfaces.EmailService;
import org.springframework.scheduling.annotation.Async;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailImplementation implements EmailService {
    @Override
    @Async
    public void sendEmail(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from("unieventosq@gmail.com")
                .to(emailDTO.address())
                .withSubject(emailDTO.subject())
                .withHTMLText(emailDTO.body()) // Use this for HTML content
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "unieventosq@gmail.com", "uljn laxv yiwk eerv")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);
        }
    }
}
