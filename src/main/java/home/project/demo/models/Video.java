package home.project.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "videos")
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file", columnDefinition = "TEXT")
    private String file;

    @OneToOne
    @JoinColumn(name = "Users_id", referencedColumnName = "id")
    private User user;

    @ToString.Exclude
    @OneToMany
    @JoinColumn(name = "Comment_id", referencedColumnName = "id")
    private Set<Comment> comment;

    @ManyToMany
    @JoinTable(name = "VIDEO_TAG",
            joinColumns = @JoinColumn(name = "VIDEO_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private Set<Tag> tags;

    public Video(String title, String description) {
    }

    public Video() {

    }
}