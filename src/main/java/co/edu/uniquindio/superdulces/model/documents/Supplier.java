package co.edu.uniquindio.superdulces.model.documents;

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
    private List<Product> productList;

}
