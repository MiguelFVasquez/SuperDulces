package co.edu.uniquindio.superdulces.model.documents;
import co.edu.uniquindio.superdulces.model.entitys.User;
import co.edu.uniquindio.superdulces.model.entitys.ValidationCode;
import co.edu.uniquindio.superdulces.model.enums.AccountState;
import co.edu.uniquindio.superdulces.model.enums.State;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Account {
    @Id
    private String id;
    private User user;
    private String email;
    private String password;
    private AccountState state;
    private ValidationCode validationCode;

}
