package home.project.demo.services;

import home.project.demo.commands.JwtCommand;
import home.project.demo.commands.LoginCommand;
import home.project.demo.dtos.JwtDto;
import home.project.demo.dtos.VideoDto;
import home.project.demo.models.User;
import home.project.demo.models.Video;
import home.project.demo.repositories.UserRepository;
import home.project.demo.repositories.VideoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final AuthenticationService authenticationService;

    public UserService(UserRepository userRepository, VideoRepository videoRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.authenticationService = authenticationService;
    }

    public ResponseEntity<String> createAccount(@RequestBody LoginCommand loginCommand) {
        if (userRepository.findByUsername(loginCommand.getUsername()).isPresent()){
            return new ResponseEntity<>("User with this username have been found",HttpStatus.FOUND);
        }
        User user = new User();
        user.setUsername(loginCommand.getUsername());
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        user.setPassword(encoder.encode(loginCommand.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public ResponseEntity<JwtDto> logIn(@RequestBody LoginCommand loginCommand) {
        JwtCommand jwtCommand = authenticationService.auth(loginCommand);
        return new ResponseEntity<>(parseJwtCommandToDto(jwtCommand), HttpStatus.OK);
    }

    private JwtDto parseJwtCommandToDto(JwtCommand jwtCommand) {
        return new JwtDto(jwtCommand.getToken(), jwtCommand.getType());
    }

    public ResponseEntity<VideoDto> getUserVideo() {
        Optional<Video> videoOptional = videoRepository.findVideoByUserId(authenticationService.getIdFromJwt());
        if (videoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
