package co.edu.uniquindio.superdulces.dto.productDTO;

import co.edu.uniquindio.superdulces.model.documents.Supplier;
import co.edu.uniquindio.superdulces.model.enums.Category;
import co.edu.uniquindio.superdulces.model.enums.State;

public record CreateProductDTO(String name,
                               String description,
                               String image,
                               Integer quantity,
                               Float price,
                               Supplier supplier,
                               State state,
                               Category category) {
}
