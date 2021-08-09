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
@Table(name = "File_types")
@EntityListeners(AuditingEntityListener.class)
public class FileType {

    public FileType() {
    }

    public FileType(String mime, String extension, String type) {
        this.mime = mime;
        this.extension = extension;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "mime", columnDefinition = "TEXT")
    private String mime;

    @Column(name = "extension", columnDefinition = "TEXT")
    private String extension;

    @Column(name = "type", columnDefinition = "TEXT")
    private String type;


}
