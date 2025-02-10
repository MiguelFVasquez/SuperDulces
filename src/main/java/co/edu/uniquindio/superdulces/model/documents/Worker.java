package co.edu.uniquindio.superdulces.model.documents;

import co.edu.uniquindio.superdulces.model.enums.Function;
import co.edu.uniquindio.superdulces.model.enums.State;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document
@Data
@Builder
public class Worker {
    @Id
    private String id;

    private String name;
    private String document;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Float wage;
    private Integer hoursPerWeek;
    private State state;
    private Function function;
    private Date dateOfBirth;



}
