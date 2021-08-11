package home.project.demo.services;

import home.project.demo.models.Tag;
import home.project.demo.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository){
     this.tagRepository=tagRepository;
    }
    public Set<Tag> getTagsList(List<String> tags) {
        Set<Tag> tagsList = new HashSet<>();
        tags.stream().forEach(tag -> {
            if (!tagRepository.existsByNameTag(tag)) {

                tagsList.add(tagRepository.save(new Tag(tag)));
            } else
                tagsList.add(tagRepository.findTopByNameTag(tag));
        });
        return tagsList;
    }
}
