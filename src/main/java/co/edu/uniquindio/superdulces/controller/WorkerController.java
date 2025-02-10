package co.edu.uniquindio.superdulces.controller;


import co.edu.uniquindio.superdulces.dto.configDTO.MessageDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.CreateWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.ItemWorkerDTO;
import co.edu.uniquindio.superdulces.dto.workerDTO.UpdateWorkerDTO;
import co.edu.uniquindio.superdulces.services.interfaces.WorkerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/worker")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/actives")
    public ResponseEntity<Page<ItemWorkerDTO>> getActiveWorkers(@RequestParam (defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ItemWorkerDTO> workers = workerService.getActiveWorkers(pageRequest);
        return ResponseEntity.ok(workers);
    }
    @GetMapping("/inactives")
    public ResponseEntity<Page<ItemWorkerDTO>> getInactiveWorkers(@RequestParam (defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ItemWorkerDTO> workers = workerService.getInactiveWorkers(pageRequest);
        return ResponseEntity.ok(workers);
    }

    @PostMapping("/newWorker")
    public ResponseEntity<MessageDTO<String>> newWorker(@Valid @RequestBody CreateWorkerDTO createWorkerDTO) {
        workerService.addWorker(createWorkerDTO);
        return ResponseEntity.ok(new MessageDTO<>(false,"Worker added successfully"));
    }

    @PutMapping("/updateWorker")
    public ResponseEntity<MessageDTO<String>> updateWorker(@Valid @RequestBody UpdateWorkerDTO updateWorkerDTO) {
        workerService.updateWorker(updateWorkerDTO);
        return ResponseEntity.ok(new MessageDTO<>(true,"Worker updated successfully"));
    }
    @DeleteMapping("/deleteWorker")
    public ResponseEntity<MessageDTO<String>> deleteWorker(@RequestParam String id) {
        workerService.deleteWorker(id);
        return ResponseEntity.ok(new MessageDTO<>(true,"Worker deleted successfully"));
    }



}
