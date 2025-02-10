package co.edu.uniquindio.superdulces.services.implementations;


import co.edu.uniquindio.superdulces.dto.supplierDTO.CreateSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.ItemSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.UpdateSupplierDTO;
import co.edu.uniquindio.superdulces.exceptions.SupplierException;
import co.edu.uniquindio.superdulces.model.documents.Product;
import co.edu.uniquindio.superdulces.model.documents.Supplier;
import co.edu.uniquindio.superdulces.model.enums.State;
import co.edu.uniquindio.superdulces.repositories.SupplierRepository;
import co.edu.uniquindio.superdulces.services.interfaces.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor

public class SupplierImplementation implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public Supplier addSupplier(CreateSupplierDTO createSupplierDTO) throws SupplierException {
        Supplier isnSupplier= getSupplierByName(createSupplierDTO.name());

        if (isnSupplier != null) {
            throw new SupplierException("Supplier already exists");
        }

        Supplier newSupplier = Supplier.builder()
                .id(String.valueOf(new ObjectId()))
                .name(createSupplierDTO.name())
                .nit(createSupplierDTO.nit())
                .address(createSupplierDTO.address())
                .phone(createSupplierDTO.phone())
                .email(createSupplierDTO.email())
                .state(State.ACTIVE)
                .productList(new ArrayList<Product>())
                .build();

        return supplierRepository.save(newSupplier);
    }

    @Override
    public Supplier updateSupplier(UpdateSupplierDTO updateSupplierDTO) throws SupplierException {
        Supplier updatedSupplier = getSupplierByName(updateSupplierDTO.name());
        if (updatedSupplier == null) {
            throw new SupplierException("Supplier not found");
        }
        updatedSupplier.setName(updateSupplierDTO.name());
        updatedSupplier.setAddress(updateSupplierDTO.address());
        updatedSupplier.setPhone(updateSupplierDTO.phone());
        updatedSupplier.setEmail(updateSupplierDTO.email());

        return supplierRepository.save(updatedSupplier);
    }

    @Override
    public Supplier deleteSupplier(String supplierId) throws SupplierException {
        Supplier deleteSupplier = getSupplier(supplierId);
        deleteSupplier.setState(State.INACTIVE);
        return supplierRepository.save(deleteSupplier);
    }

    /**/
    @Override
    public Page<ItemSupplierDTO> getActiveSuppliers(Pageable pageable) throws SupplierException{
        Page<Supplier> activeSuppliers= supplierRepository.getSuppliersByState(State.ACTIVE,pageable);
        if (activeSuppliers.isEmpty()) {
            throw new SupplierException("There is no active suppliers");
        }
        return activeSuppliers.map(supplier -> new ItemSupplierDTO(
                supplier.getName(),
                supplier.getNit(),
                supplier.getPhone(),
                supplier.getEmail()
        ));

    }
    @Override
    public Page<ItemSupplierDTO> getInactiveSuppliers(Pageable pageable) throws SupplierException{
        Page<Supplier> inactiveSuppliers = supplierRepository.getSuppliersByState(State.INACTIVE,pageable);
        if (inactiveSuppliers.isEmpty()) {
            throw new SupplierException("There is no inactive suppliers");
        }
        return inactiveSuppliers.map(supplier -> new ItemSupplierDTO(
                supplier.getName(),
                supplier.getNit(),
                supplier.getPhone(),
                supplier.getEmail()
        ));

    }


    /*-----------------Private methods-----------------------*/

    private Supplier getSupplier(String supplierID) throws SupplierException {
        return supplierRepository.findById(supplierID).orElseThrow(()-> new SupplierException("Supplier not found"));
    }
    private Supplier getSupplierByName(String name){
        return supplierRepository.findByName(name);
    }




}
