package home.project.demo.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginCommand {
    String username;
    String password;
}
