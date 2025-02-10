package co.edu.uniquindio.superdulces.repositories;

import co.edu.uniquindio.superdulces.model.documents.Worker;
import co.edu.uniquindio.superdulces.model.enums.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends MongoRepository<Worker,String> {

    @Query
    Worker findByName(String name);
    @Query
    Worker findByDocument(String email);
    @Query
    Page<Worker> findByState(State state,Pageable pageable);

}
