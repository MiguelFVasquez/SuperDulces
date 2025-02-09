package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.supplierDTO.CreateSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.UpdateSupplierDTO;
import co.edu.uniquindio.superdulces.exceptions.SupplierException;
import co.edu.uniquindio.superdulces.model.documents.Supplier;

public interface SupplierService {


    Supplier addSupplier(CreateSupplierDTO createSupplierDTO) throws SupplierException;
    Supplier updateSupplier(UpdateSupplierDTO updateSupplierDTO) throws SupplierException;
    Supplier deleteSupplier(String supplierId) throws SupplierException;


}
