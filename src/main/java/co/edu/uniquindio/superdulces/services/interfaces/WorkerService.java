package co.edu.uniquindio.superdulces.services.interfaces;

import co.edu.uniquindio.superdulces.dto.workerDTO.CreateWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.ItemWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.UpdateWorkerDTO;
import co.edu.uniquindio.superdulces.exceptions.WorkerException;
import co.edu.uniquindio.superdulces.model.documents.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkerService {

    Worker addWorker(CreateWorkerDTO createWorkerDTO) throws WorkerException;
    Worker updateWorker(UpdateWorkerDTO updateWorkerDTO) throws WorkerException;
    Worker deleteWorker(String WorkerId) throws WorkerException;

    Page<ItemWorkerDTO> getActiveWorkers(Pageable pageable) throws WorkerException;

    Page<ItemWorkerDTO> getInactiveWorkers(Pageable pageable) throws WorkerException;
}
