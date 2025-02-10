package co.edu.uniquindio.superdulces.model.documents;

import co.edu.uniquindio.superdulces.model.enums.Category;
import co.edu.uniquindio.superdulces.model.enums.State;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Product {
    @Id
    private String id;

    private String name;
    private String description;
    private String image;
    private Integer quantity;
    private Float price;
    private Supplier supplier;
    private State state;
    private Category category;
}
