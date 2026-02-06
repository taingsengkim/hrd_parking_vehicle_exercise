package mvc.model.dto;

import java.time.LocalDate;

public record StudentCreateDto(
        String username,
        String email,
        String password,
        LocalDate birthOfDate
) {
}
