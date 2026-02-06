package FileIO.uploadsinglefile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

public class FileService {
    private final String serverLocation = "media/";
    public Map<String,String> uploadSingleFile(File file){
        try {
            int dotSymbolLocation =   file.getName().lastIndexOf('.');
            String extension = file.getName().substring(dotSymbolLocation+1);

            if(!extension.equals("png")){
                throw new RuntimeException("Only Images Is Allowed");
            }

            String newFileName = UUID.randomUUID()+"."+extension;
            String oldFileName = file.getAbsoluteFile().toString();

            Files.copy(Path.of(oldFileName),new FileOutputStream(serverLocation+newFileName));

            return Map.of(
                    "preview","localhost:8080/media/"+newFileName,
                    "downloadLink","localhost:8080/api/v1/media/download/"+newFileName);

        }catch (Exception e){
            System.out.println(e.getMessage() + " ");
        }
        return null;
    }
}
