package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.productDTO.CreateProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.RegisterProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.UpdateProductDTO;
import co.edu.uniquindio.superdulces.exceptions.ProductException;
import co.edu.uniquindio.superdulces.model.documents.Product;

public interface ProductService {

    Product newProduct(CreateProductDTO createProductDTO) throws ProductException;
    Product addProduct(RegisterProductDTO registerProductDTO) throws ProductException;
    Product updateProduct (UpdateProductDTO updateProductDTO) throws ProductException;
    Product deleteProduct (String productID) throws ProductException;

}
