package home.project.demo.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtCommand {
    private String token;
    private String type = "Bearer";

    public JwtCommand(String accessToken) {
        this.token = accessToken;
    }

}