package home.project.demo;

import home.project.demo.models.FileType;
import home.project.demo.models.User;
import home.project.demo.repositories.FileTypeRepository;
import home.project.demo.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StartValue implements ApplicationRunner {
    private final UserRepository userRepository;
    private final FileTypeRepository fileTypeRepository;
    public StartValue(UserRepository userRepository,FileTypeRepository fileTypeRepository){
        this.userRepository=userRepository;
        this.fileTypeRepository=fileTypeRepository;

    }
    //Temporal
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user =new User("name", "pass");
        User user1 =new User("name", "pass");
        userRepository.save(user);
        userRepository.save(user1);

        List<FileType> nonRestrictedExtensions = new ArrayList<>();
        nonRestrictedExtensions.add(new FileType("video/mp4", "mp4", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/webm", "webm", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/ogv", "ogv", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/ogg", "ogg", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/ogm", "ogm", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/m4v", "m4v", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/asf", "asf", "MOVIE"));
        nonRestrictedExtensions.add(new FileType("video/avi", "avi", "MOVIE"));
        fileTypeRepository.saveAll(nonRestrictedExtensions);
    }
}
