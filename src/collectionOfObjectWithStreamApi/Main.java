package collectionOfObjectWithStreamApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.lang.classfile.instruction.TableSwitchInstruction;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class Main {
    static void main() {

        Employee emp1 = new Employee(1, UUID.randomUUID().toString(),"jame","jame123@gmail.com",234.33);
        Employee emp2 = new Employee(2, UUID.randomUUID().toString(),"sokkieng","sokkieng@gmail.com",264.33);
        Employee emp3 = new Employee(3, UUID.randomUUID().toString(),"dana","daana@gmail.com",334.33);
        List<Employee> employees = new ArrayList<>(List.of(emp1,emp2,emp3));

        Table table = new Table(5, BorderStyle.UNICODE_BOX);
        String[] col = {"ID","UUID","Name","Email","Salary"};
        for(String c : col){
            table.addCell(c.toUpperCase(),new CellStyle(CellStyle.HorizontalAlign.center));
        }

        for (Employee emp : employees){
            table.addCell(emp.getId().toString(),1);
            table.addCell(emp.getUuid(),1);
            table.addCell(emp.getName(),1);
            table.addCell(emp.getEmail(),1);
            table.addCell(emp.getSalary().toString(),1);
        }

        System.out.println(table.render());

        employees.stream()
                .filter(e->e.getSalary() > 250.0)
                .map(e->e.getSalary()/10)
                .forEach(System.out::println);
    }
}
