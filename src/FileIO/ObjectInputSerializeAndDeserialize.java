package FileIO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Data
class Book implements Serializable {
    private transient Integer id;
    private String name;
    private LocalDate releasedDate;
}

public class ObjectInputSerializeAndDeserialize {
    private static void writeObject(Book book){
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("book.db"))){
            writer.writeObject(book);
            System.out.println("Object has been written.");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static Book readObject(){
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream("book.db"))){
            return (Book) reader.readObject();
        }catch (IOException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    static void main() {

        Book book = readObject();
        System.out.println(book );

    }
}
