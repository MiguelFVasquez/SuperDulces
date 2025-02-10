package co.edu.uniquindio.superdulces.dto.supplierDTO;

import co.edu.uniquindio.superdulces.model.documents.Product;
import co.edu.uniquindio.superdulces.model.enums.State;

import java.util.List;

public record CreateSupplierDTO(String name, String description, String nit, String address, String phone, String email, State state) {
}
