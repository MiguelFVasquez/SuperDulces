package co.edu.uniquindio.superdulces.model.documents;

import co.edu.uniquindio.superdulces.model.enums.State;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Data
@Builder
public class Supplier {
    @Id
    private String id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String email;
    private State state;
    private List<Product> productList;

}
