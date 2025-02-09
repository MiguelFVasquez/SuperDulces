package co.edu.uniquindio.superdulces.repositories;

import co.edu.uniquindio.superdulces.model.documents.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier,String> {

    @Query
    Supplier findByName(String name);
}
