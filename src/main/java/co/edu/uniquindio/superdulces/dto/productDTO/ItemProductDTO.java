package co.edu.uniquindio.superdulces.dto.productDTO;

import co.edu.uniquindio.superdulces.model.enums.Category;

public record ItemProductDTO(String name, String image, Integer quantity, Float price, Category category) {
}
