package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.productDTO.CreateProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.RegisterProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.UpdateProductDTO;
import co.edu.uniquindio.superdulces.exceptions.ProductException;
import co.edu.uniquindio.superdulces.model.documents.Product;
import co.edu.uniquindio.superdulces.repositories.ProductRepository;
import co.edu.uniquindio.superdulces.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImplementation implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product newProduct(CreateProductDTO createProductDTO) throws ProductException {
        return null;
    }

    @Override
    public Product addProduct(RegisterProductDTO registerProductDTO) throws ProductException {
        return null;
    }

    @Override
    public Product updateProduct(UpdateProductDTO updateProductDTO) throws ProductException {
        return null;
    }

    @Override
    public Product deleteProduct(String productID) throws ProductException {
        return null;
    }
}
