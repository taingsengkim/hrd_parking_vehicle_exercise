package mvc.model.dto;

import java.time.LocalDate;

public record StudentUpdateDto (
        String username,
        String email,
        String profile,
        LocalDate birthOfDate
){
}
