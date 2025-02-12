package co.edu.uniquindio.superdulces.repositories;

import co.edu.uniquindio.superdulces.model.documents.Product;
import co.edu.uniquindio.superdulces.model.enums.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends MongoRepository<Product,String> {

    @Query
    Product findByName(String name);
    @Query
    Page<Product> getProductByState (State state,Pageable pageable);
}
