package co.edu.uniquindio.superdulces.dto.workerDTO;

import co.edu.uniquindio.superdulces.model.enums.Function;

import java.util.Date;

public record CreateWorkerDTO(String name,String document, String surname, String email, String address, Float wage, Integer hoursPerWeek, Function  function, Date dateOfBirth, String phone) {
}
