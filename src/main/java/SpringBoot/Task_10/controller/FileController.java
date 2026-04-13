package SpringBoot.Task_10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${upload.path:uploads/}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        logger.info("External API call: Processing file [{}]", file.getOriginalFilename());

        try {
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
                logger.info("Environment setup: Created directory {}", root.toAbsolutePath());
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            logger.info("Operation SUCCESS: Saved as {}", fileName);
            return ResponseEntity.ok("File uploaded successfully: " + fileName);

        } catch (IOException e) {
            logger.error("Critical System Error: Local storage failed", e);
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
