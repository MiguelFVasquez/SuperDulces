package co.edu.uniquindio.superdulces.repositories;

import co.edu.uniquindio.superdulces.model.documents.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends MongoRepository<Product,String> {

    @Query
    Product findByName(String name);

}
