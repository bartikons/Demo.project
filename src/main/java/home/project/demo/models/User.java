package home.project.demo.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nickname", columnDefinition = "TEXT")
    private String username;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
