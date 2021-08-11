package home.project.demo.controllers;

import home.project.demo.commands.JwtCommand;
import home.project.demo.commands.LoginCommand;
import home.project.demo.dtos.JwtDto;
import home.project.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin()
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //temporal string
    @PostMapping("/logIn")
    public ResponseEntity<JwtDto> LogInUser(@RequestBody LoginCommand loginCommand) {
        return userService.logIn(loginCommand);
    }

    @GetMapping("/logOut")
    public ResponseEntity LogOutUser() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/getUserVideo")
    public ResponseEntity getUserVideo() {
        return userService.getUserVideo();
    }

    @PostMapping("/CreateAccount")
    public ResponseEntity<String> createAccount(@RequestBody LoginCommand loginCommand) {
        return userService.createAccount(loginCommand);
    }
}
