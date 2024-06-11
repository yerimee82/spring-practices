package fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class FileUploadService {
    private static String SAVE_PATH = "/Users/yerim/Desktop/fileupload-files";
    public String restore(MultipartFile file) {
        String url = null;
        File uploadDirectory = new File(SAVE_PATH);
        if(!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        if(file.isEmpty()) {
            return url;
        }

        String originalFilename = file.getOriginalFilename();
        long fileSize = file.getSize();

        System.out.println("originalFilename = " + originalFilename);
        System.out.println("fileSize = " + fileSize);
        return null;
    }
}
