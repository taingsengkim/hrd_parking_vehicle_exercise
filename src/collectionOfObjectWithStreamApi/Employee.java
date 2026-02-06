package collectionOfObjectWithStreamApi;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Employee{
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    private Double salary;
}