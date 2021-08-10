package home.project.demo.services;

import home.project.demo.dtos.VideoDto;
import home.project.demo.models.User;
import home.project.demo.repositories.UserRepository;
import home.project.demo.repositories.VideoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    VideoRepository videoRepository;

    public UserService(UserRepository userRepository, VideoRepository videoRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    public ResponseEntity<String> createAccount(String username, String password) {

        User user = new User();
        user.setUsername(username);
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<String> logIn(String username, String password) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            if (encoder.matches(password, userOptional.get().getPassword()))
                return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<VideoDto> getUserVideo() {
        //videoRepository.findVideoByUserId(authService.getIdFromJwt()) Id from token
        return  new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
