package home.project.demo.controllers;

import home.project.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(originPatterns = "*", maxAge = 3600, allowCredentials = "true")
public class UserController {
    final UserService userService;
    public UserController(UserService userService){
     this.userService=userService;
    }

    @GetMapping("/login")
    public ResponseEntity LogInUser(){
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping("/logout")
    public ResponseEntity LogOutUser(){
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
    @GetMapping("/getUserVideo")
    public ResponseEntity getUserVideo(){
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}
