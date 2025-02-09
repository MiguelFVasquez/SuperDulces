package co.edu.uniquindio.superdulces.services.implementations;


import co.edu.uniquindio.superdulces.dto.supplierDTO.CreateSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.UpdateSupplierDTO;
import co.edu.uniquindio.superdulces.exceptions.SupplierException;
import co.edu.uniquindio.superdulces.model.documents.Supplier;
import co.edu.uniquindio.superdulces.repositories.SupplierRepository;
import co.edu.uniquindio.superdulces.services.interfaces.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class SupplierImplementation implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public Supplier addSupplier(CreateSupplierDTO createSupplierDTO) throws SupplierException {
        return null;
    }

    @Override
    public Supplier updateSupplier(UpdateSupplierDTO updateSupplierDTO) throws SupplierException {
        return null;
    }

    @Override
    public Supplier deleteSupplier(String supplierId) throws SupplierException {
        return null;
    }
}
