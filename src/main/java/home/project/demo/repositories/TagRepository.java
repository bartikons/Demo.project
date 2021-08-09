package home.project.demo.repositories;

import home.project.demo.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByNameTag(String nameTag);
    Tag findTopByNameTag(String nameTag);
}