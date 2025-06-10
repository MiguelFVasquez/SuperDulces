package co.edu.uniquindio.superdulces.model.entitys;
import lombok.*;
@Data
public class User {
    private String idUser;
    private String name;
    private String phoneNumber;

    @Builder
    public User(String idUser, String name, String phoneNumber) {
        this.idUser = idUser;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}
