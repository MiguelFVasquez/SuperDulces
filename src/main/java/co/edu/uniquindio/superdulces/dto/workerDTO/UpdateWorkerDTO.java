package co.edu.uniquindio.superdulces.dto.workerDTO;

import co.edu.uniquindio.superdulces.model.enums.Function;
import co.edu.uniquindio.superdulces.model.enums.State;

public record UpdateWorkerDTO(String name, String surname, String email, String phone, String address, Float wage, Integer hoursPerWeek, Function function) {
}
