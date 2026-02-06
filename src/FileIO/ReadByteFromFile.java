package FileIO;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadByteFromFile {
    static void main() {
        try (FileInputStream fileInputStream = new FileInputStream("user.txt");){
            int data = 0;
            while((data=fileInputStream.read()) != -1){
                System.out.print((char)data);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
