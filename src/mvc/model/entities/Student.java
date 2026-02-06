package mvc.model.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {
    private Integer id;
    private String uuid;
    private String username;
    private String email;
    private String profile;
    private String cardId;
    private String password;
    private LocalDate birthOfDate;
}
