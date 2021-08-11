package home.project.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
    private String token;
    private String type;

    public JwtDto(String token, String type) {
    this.token=token;
    this.type=type;
    }
}
