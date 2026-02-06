package mvc.model.dto;

import java.time.LocalDate;

public record StudentResponseDto(
        String uuid,
        String email,
        String username,
        String profile,
        String cardId,
        LocalDate birthOfDate
) {
}
