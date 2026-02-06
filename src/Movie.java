import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movie {
    private Integer id;
    private String uuId;
    private String name;
    private String description;
    private LocalDate releasedDate;
    private Set<Author> authors;
    private String timeline;
    private Set<String> sponsor;
    private Boolean isTrailer;
}
