package home.project.demo.services;

import home.project.demo.dtos.TagDto;
import home.project.demo.dtos.UserDto;
import home.project.demo.dtos.VideoDto;
import home.project.demo.models.FileType;
import home.project.demo.models.Tag;
import home.project.demo.models.User;
import home.project.demo.models.Video;
import home.project.demo.repositories.FileTypeRepository;
import home.project.demo.repositories.TagRepository;
import home.project.demo.repositories.UserRepository;
import home.project.demo.repositories.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VideoService {
    @Value("${files.size}")
    Long maxFileSize ;
    private final TagRepository tagRepository;
    private final StorageService storageService;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final FileTypeRepository fileTypeRepository;

    @Autowired
    public VideoService(TagRepository tagRepository, StorageService storageService, UserRepository userRepository, VideoRepository videoRepository,FileTypeRepository fileTypeRepository) {
        this.tagRepository = tagRepository;
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.fileTypeRepository = fileTypeRepository;
    }

    public ResponseEntity<VideoDto> addVideo(MultipartFile file, Video video, List<String> tags) {

        file.getSize();
        Optional<User> userOptional = getUserById(1L);
        if (file.getSize() > (maxFileSize * 1024 * 1024)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if(!verifyVideoExtension(file)){
            return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        if (!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        Set<Tag> ListTag = getTagsList(tags);
        video.setTags(ListTag);
        video = saveVideo(video);
        video.setFile(uploadFiles(file, video.getId()));
        VideoDto videoDto = parserVideoToDTO(video, new ArrayList<>(ListTag), user);
        return new ResponseEntity<>(videoDto, HttpStatus.OK);
    }

    public boolean verifyVideoExtension(MultipartFile video) {

        List<FileType> availableTypes = fileTypeRepository.findAllByType("MOVIE");
        availableTypes.forEach(System.out::println);
        System.out.println(video.getContentType());
        List<String> types = availableTypes.stream().map(FileType::getMime).collect(Collectors.toList());
        if (types.contains(video.getContentType())) {
            return true;
        }
        return false;
    }

    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
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

    public String uploadFiles(MultipartFile file, Long videoId) {
        String filename = "";
        storageService.initialize(videoId.toString());
        if (storageService.store(file)) {
            filename = file.getOriginalFilename();
        }
        return filename;
    }

    public VideoDto parserVideoToDTO(Video video, List<Tag> listTags, User user) {
        ModelMapper mapper = new ModelMapper();
        VideoDto videoDto = mapper.map(video, VideoDto.class);

        List<TagDto> tagDtoList = listTags.stream()
                .map(oneTag ->mapper.map(oneTag, TagDto.class))
                .collect(Collectors.toList());

        videoDto.setTags(tagDtoList);
        UserDto userDto = mapper.map(user, UserDto.class);
        videoDto.setUser(userDto);

        return videoDto;
    }
}

