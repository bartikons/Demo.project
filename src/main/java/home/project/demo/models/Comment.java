package home.project.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "contents", columnDefinition = "TEXT")
    private String contents;

}
