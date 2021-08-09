package home.project.demo.dtos;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VideoDto {
    private Long id;
    private String file;
    private UserDto user;

    private List<CommentDto> comment;
    private List<TagDto> tags;
}
