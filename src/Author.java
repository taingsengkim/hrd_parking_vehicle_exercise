import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {
    private Integer id;
    private String uuid;
    private String name;
    private  String profile;
}
