package co.edu.uniquindio.superdulces.controller;

import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.CreateProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.ItemProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.RegisterProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.UpdateProductDTO;
import co.edu.uniquindio.superdulces.exceptions.ProductException;
import co.edu.uniquindio.superdulces.services.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("actives")
    public ResponseEntity<Page<ItemProductDTO>> getActiveProducts(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "3") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ItemProductDTO> products = productService.getActiveProducts(pageRequest);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/inactives")
    public ResponseEntity<Page<ItemProductDTO>> getInactiveProducts(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "3") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ItemProductDTO> products = productService.getInactiveProducts(pageRequest);
        return ResponseEntity.ok(products);
    }



    @PostMapping("/newProduct")
    public ResponseEntity<MessageDTO<String>> newProduct(@Valid @RequestBody CreateProductDTO productDTO) throws ProductException {
        productService.newProduct(productDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,"Product created successfully"));
    }
    @PutMapping("/addProduct")
    public ResponseEntity<MessageDTO<String>> addProduct(@Valid @RequestBody RegisterProductDTO productDTO) throws ProductException {
        productService.addProduct(productDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,"Product added successfully"));
    }
    @PutMapping("/updateProduct")
    public ResponseEntity<MessageDTO<String>>updateProduct(@Valid @RequestBody UpdateProductDTO productDTO) throws ProductException {
        productService.updateProduct(productDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,"Product updated successfully"));
    }
    @DeleteMapping("/deleteProduct/{idProduct}")
    public ResponseEntity<MessageDTO<String>>deleteProduct(@PathVariable String idProduct){
        productService.deleteProduct(idProduct);
        return ResponseEntity.ok(new MessageDTO<>(false,"Product deleted successfully"));
    }




}
