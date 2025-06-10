package co.edu.uniquindio.superdulces.repositories;

import co.edu.uniquindio.superdulces.model.documents.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account,String> {

    @Query("{email:  ?0}")
    Optional<Account> getByEmail(String email);
}
