package co.edu.uniquindio.superdulces.controller;

import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.CreateSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.ItemSupplierDTO;
import co.edu.uniquindio.superdulces.dto.supplierDTO.UpdateSupplierDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.ItemWorkerDTO;
import co.edu.uniquindio.superdulces.exceptions.SupplierException;
import co.edu.uniquindio.superdulces.services.interfaces.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supplier")

public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/actives")
    public ResponseEntity<Page<ItemSupplierDTO>> getActiveWorkers(@RequestParam(defaultValue = "0") int page, @RequestParam (defaultValue = "3") int size){
        PageRequest pageRequest= PageRequest.of(page, size);
        Page<ItemSupplierDTO> suppliers = supplierService.getActiveSuppliers(pageRequest);
        return ResponseEntity.ok(suppliers);
    }
    @GetMapping("/inactives")
    public ResponseEntity<Page<ItemSupplierDTO>> getInactiveWorkers(@RequestParam(defaultValue = "0") int page, @RequestParam (defaultValue = "3") int size){
        PageRequest pageRequest= PageRequest.of(page, size);
        Page<ItemSupplierDTO> suppliers = supplierService.getInactiveSuppliers(pageRequest);
        return ResponseEntity.ok(suppliers);
    }
    @PostMapping("/newSupplier")
    public ResponseEntity<MessageDTO<String>> newSupplier(@Valid @RequestBody CreateSupplierDTO createSupplierDTO) throws SupplierException {
        supplierService.addSupplier(createSupplierDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,"Supplier added successfully"));
    }
    @PutMapping("/updateSupplier")
    public ResponseEntity<MessageDTO<String>> updateSupplier(@Valid @RequestBody UpdateSupplierDTO updateSupplierDTO) throws SupplierException {
        supplierService.updateSupplier(updateSupplierDTO);
        return ResponseEntity.ok(new MessageDTO<>(true,"Supplier updated successfully"));
    }
    @DeleteMapping("/deleteSupplier/{id}")
    public ResponseEntity<MessageDTO<String>> deleteSupplier(@PathVariable String id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(new MessageDTO<>(true,"Supplier deleted successfully"));

    }




}
