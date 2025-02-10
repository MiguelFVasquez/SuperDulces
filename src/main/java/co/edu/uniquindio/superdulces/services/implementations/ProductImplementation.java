package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.productDTO.CreateProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.RegisterProductDTO;
import co.edu.uniquindio.superdulces.dto.productDTO.UpdateProductDTO;
import co.edu.uniquindio.superdulces.exceptions.ProductException;
import co.edu.uniquindio.superdulces.model.documents.Product;
import co.edu.uniquindio.superdulces.model.enums.State;
import co.edu.uniquindio.superdulces.repositories.ProductRepository;
import co.edu.uniquindio.superdulces.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImplementation implements ProductService {

    private final ProductRepository productRepository;

    /*
    *Method for adding a product for the first time
    * */
    @Override
    public Product newProduct(CreateProductDTO createProductDTO) throws ProductException {
        Product isnProduct= getProductByName(createProductDTO.name());
        /*If is null means that the artist doesn´t exists*/
        if(isnProduct!=null){
            throw new ProductException("There is already a product with that name");
        }
        Product newProduct = Product.builder()
                .id(String.valueOf(new ObjectId()))
                .name(createProductDTO.name())
                .description(createProductDTO.description())
                .image(createProductDTO.image())
                .quantity(createProductDTO.quantity())
                .price(createProductDTO.price())
                .supplier(createProductDTO.supplier())
                .state(State.ACTIVE)
                .category(createProductDTO.category())
                .build();

        return productRepository.save(newProduct);
    }
    /*
    * Method to add update just the quantity of a product
    * */
    @Override
    public Product addProduct(RegisterProductDTO registerProductDTO) throws ProductException {
        Product product = getProductByName(registerProductDTO.name());
        if(product==null){
            throw new ProductException("There is no product with that name");
        }
        product.setQuantity(product.getQuantity()+registerProductDTO.quantity());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UpdateProductDTO updateProductDTO) throws ProductException {
        Product updateProduct = getProductByName(updateProductDTO.name());
        if(updateProduct==null){
            throw new ProductException("There is no product with that name");
        }
        updateProduct.setName(updateProductDTO.name());
        updateProduct.setDescription(updateProductDTO.description());
        updateProduct.setImage(updateProductDTO.image());
        updateProduct.setPrice(updateProductDTO.price());
        updateProduct.setSupplier(updateProductDTO.supplier());

        return productRepository.save(updateProduct);
    }

    @Override
    public Product deleteProduct(String productID) throws ProductException {
        Product deleteProduct = getProduct(productID);
        deleteProduct.setState(State.INACTIVE);
        return productRepository.save(deleteProduct);
    }

    /*Private methods*/
    /*
    * Get a product by id
    * */
    private Product getProduct(String productID) throws ProductException {
        return productRepository.findById(productID).orElseThrow(() -> new ProductException("Product not found"));
    }

    private Product getProductByName(String name) {
        return productRepository.findByName(name);
    }



}
