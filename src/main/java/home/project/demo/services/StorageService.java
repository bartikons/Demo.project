package home.project.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class StorageService {
    @Value("${files.directory}")
    private String rootDirectory;
    private Path root;

    public Boolean store(MultipartFile file) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));


        if (filename.contains("..")) {return false;}

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void initialize(String materialDirectory) {
        try {
            if (!Files.exists(Paths.get(rootDirectory)))
                Files.createDirectory(Paths.get(rootDirectory));

            root = Paths.get(rootDirectory + "/" + materialDirectory);

            if (!Files.exists(root))
                Files.createDirectory(root);

        } catch (IOException e) {
            System.out.println("Couldn't initialize directory");
        }
    }
}
