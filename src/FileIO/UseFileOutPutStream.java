package FileIO;

import java.io.FileOutputStream;

public class UseFileOutPutStream {
    static void main(String[] args) {
        try(FileOutputStream fileOutputStream = new FileOutputStream("user.txt")){
            String[] names = {"jeje","jim"};
            for (String name : names){
                fileOutputStream.write(name.getBytes());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
