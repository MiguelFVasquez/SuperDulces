package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.supplierDTO.CreateSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.ItemSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.UpdateSupplierDTO;
import co.edu.uniquindio.superdulces.exceptions.SupplierException;
import co.edu.uniquindio.superdulces.model.documents.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {


    Supplier addSupplier(CreateSupplierDTO createSupplierDTO) throws SupplierException;
    Supplier updateSupplier(UpdateSupplierDTO updateSupplierDTO) throws SupplierException;
    Supplier deleteSupplier(String supplierId) throws SupplierException;


    Page<ItemSupplierDTO> getActiveSuppliers(Pageable pageable) throws SupplierException;

    Page<ItemSupplierDTO> getInactiveSuppliers(Pageable pageable) throws SupplierException;
}
