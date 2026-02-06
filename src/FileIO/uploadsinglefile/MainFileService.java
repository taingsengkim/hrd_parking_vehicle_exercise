package FileIO.uploadsinglefile;

import java.io.File;

public class MainFileService {
    static void main() {
        FileService fileService = new FileService();

        System.out.println(
                fileService.uploadSingleFile(new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\51e9f56167e05d0be510e36ccdd68a75.jdpg"))
        );
    }
}
