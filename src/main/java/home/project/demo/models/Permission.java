package home.project.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "permission")
@EntityListeners(AuditingEntityListener.class)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "permission", columnDefinition = "TEXT")
    private String permission;
    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Set<User> user;

}
