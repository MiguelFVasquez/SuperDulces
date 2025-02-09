package co.edu.uniquindio.superdulces.services.implementations;

import co.edu.uniquindio.superdulces.dto.workerDTO.CreateWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.UpdateWorkerDTO;
import co.edu.uniquindio.superdulces.exceptions.WorkerException;
import co.edu.uniquindio.superdulces.model.documents.Worker;
import co.edu.uniquindio.superdulces.repositories.WorkerRepository;
import co.edu.uniquindio.superdulces.services.interfaces.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerImplementation implements WorkerService{

    private final WorkerRepository workerRepository;

    @Override
    public Worker addWorker(CreateWorkerDTO createWorkerDTO) throws WorkerException {
        return null;
    }

    @Override
    public Worker updateWorker(UpdateWorkerDTO updateWorkerDTO) throws WorkerException {
        return null;
    }

    @Override
    public Worker deleteWorker(String WorkerId) throws WorkerException {
        return null;
    }
}
