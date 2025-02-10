package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.workerDTO.CreateWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.ItemWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.UpdateWorkerDTO;
import co.edu.uniquindio.superdulces.exceptions.WorkerException;
import co.edu.uniquindio.superdulces.model.documents.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.edu.uniquindio.superdulces.model.enums.State;
import co.edu.uniquindio.superdulces.repositories.WorkerRepository;
import co.edu.uniquindio.superdulces.services.interfaces.WorkerService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerImplementation implements WorkerService{

    private final WorkerRepository workerRepository;

    @Override
    public Worker addWorker(CreateWorkerDTO createWorkerDTO) throws WorkerException {
        Worker isnWorker = getWorkerByDocument(createWorkerDTO.document());
        if (isnWorker != null) {
            throw new WorkerException("Worker already exists");
        }

        Worker newWorker = Worker.builder()
                .id(String.valueOf(new ObjectId()))
                .name(createWorkerDTO.name())
                .document(createWorkerDTO.document())
                .surname(createWorkerDTO.surname())
                .email(createWorkerDTO.email())
                .address(createWorkerDTO.address())
                .wage(createWorkerDTO.wage())
                .hoursPerWeek(createWorkerDTO.hoursPerWeek())
                .state(State.ACTIVE)
                .function(createWorkerDTO.function())
                .dateOfBirth(createWorkerDTO.dateOfBirth())
                .build();

        return workerRepository.save(newWorker);
    }

    @Override
    public Worker updateWorker(UpdateWorkerDTO updateWorkerDTO) throws WorkerException {
        Worker updateWorker= getWorkerByDocument(updateWorkerDTO.document());
        if (updateWorker == null) {
            throw new WorkerException("Worker not found");
        }
        updateWorker.setName(updateWorkerDTO.name());
        updateWorker.setSurname(updateWorkerDTO.surname());
        updateWorker.setEmail(updateWorkerDTO.email());
        updateWorker.setPhone(updateWorkerDTO.phone());
        updateWorker.setAddress(updateWorkerDTO.address());
        updateWorker.setWage(updateWorkerDTO.wage());
        updateWorker.setHoursPerWeek(updateWorkerDTO.hoursPerWeek());
        updateWorker.setFunction(updateWorkerDTO.function());
        return workerRepository.save(updateWorker);
    }

    @Override
    public Worker deleteWorker(String workerId) throws WorkerException {
        Worker deleteWorker = getWWorker(workerId);
        deleteWorker.setState(State.INACTIVE);
        return workerRepository.save(deleteWorker);
    }

    @Override
    public Page<ItemWorkerDTO> getActiveWorkers(Pageable pageable) throws WorkerException {
        Page<Worker> activeWorkers = workerRepository.findByState(State.ACTIVE, pageable);
        if (activeWorkers.isEmpty()){
            throw new WorkerException("There is no active workers");
        }
        return activeWorkers.map(worker -> new ItemWorkerDTO(
                worker.getName(),
                worker.getDocument(),
                worker.getSurname(),
                worker.getEmail(),
                worker.getPhone()
        ));
    }

    @Override
    public Page<ItemWorkerDTO> getInactiveWorkers(Pageable pageable) throws WorkerException {
        Page<Worker> inactiveWorkers = workerRepository.findByState(State.INACTIVE, pageable);
        if (inactiveWorkers.isEmpty()){
            throw new WorkerException("There is no inactive workers");
        }
        return inactiveWorkers.map(worker -> new ItemWorkerDTO(worker.getName(),
                worker.getDocument(),
                worker.getSurname(),
                worker.getEmail(),
                worker.getPhone()
        ));
    }

    /*Private methods*/

    private Worker getWWorker(String WorkerId) throws WorkerException {
        return workerRepository.findById(WorkerId).orElseThrow(()->new WorkerException("Worker not found"));
    }
    private Worker getWorkerByName(String WorkerName) throws WorkerException {
        return workerRepository.findByName(WorkerName);
    }
    private Worker getWorkerByDocument(String document) throws WorkerException{
        return workerRepository.findByDocument(document);
    }




}
