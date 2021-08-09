package home.project.demo.repositories;

import home.project.demo.models.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileTypeRepository extends JpaRepository<FileType, Long> {
    List<FileType> findAllByType(String type);
}