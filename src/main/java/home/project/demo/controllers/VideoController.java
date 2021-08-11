package home.project.demo.controllers;

import home.project.demo.dtos.VideoDto;
import home.project.demo.models.Video;
import home.project.demo.services.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/Video")
@CrossOrigin
public class VideoController {
    final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<VideoDto> upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam("tags") List<String> tags,
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {
        Video video = new Video(title, description);
        return videoService.addVideo(file, video,tags);
    }

    @GetMapping("/getVideo")
    public ResponseEntity getVideo() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/getComment")
    public ResponseEntity getComment() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity DeleteVideo() {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}
