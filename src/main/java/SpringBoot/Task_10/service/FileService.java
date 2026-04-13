package SpringBoot.Task_10.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public void saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        Path path = Paths.get(uploadDir + "/" + fileName);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());
    }
}