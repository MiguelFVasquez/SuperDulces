package co.edu.uniquindio.superdulces.dto.productDTO;

import co.edu.uniquindio.superdulces.model.documents.Supplier;

public record UpdateProductDTO(String name, String description, String image, Float price, Supplier supplier) {
}
