package FileIO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DataWritingService{
    void writeData(Map<String,Object> data){
        final String fileName = "user.csv";
        try(FileWriter fileWriter = new FileWriter(fileName,true);) {
            for (Map.Entry<String,Object> d : data.entrySet() ){
                fileWriter.write(d.getValue() + ",");
            }
            fileWriter.write(System.lineSeparator());
            System.out.println("Write data successfully!");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

public class UseFileWriter {
    static void main() {

        Map<String,Object> data = new HashMap<>(
                Map.of(
                        "ID","2",
                        "NAME","jelly",
                        "EMAIL","jelly23@gmail.com",
                        "PASSWORD","ki12321@#",
                        "ID_DELETE",false
                )
        );

        DataWritingService dataWritingService = new DataWritingService();
        dataWritingService.writeData(data);
    }
}
