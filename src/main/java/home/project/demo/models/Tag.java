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
@Table(name = "Tags")
@EntityListeners(AuditingEntityListener.class)
public class Tag {
    public Tag() {

    }

    public Tag(String nameTag) {
        this.nameTag = nameTag;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nameTag", columnDefinition = "TEXT")
    private String nameTag;

    @ManyToMany(mappedBy = "tags")
    @ToString.Exclude
    private Set<Video> video;

}
