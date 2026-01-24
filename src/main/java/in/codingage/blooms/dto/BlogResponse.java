package in.codingage.blooms.dto;

import in.codingage.blooms.models.CategoryMapping;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BlogResponse {
    private String id;
    private String title;
    private String description;
    private String content;
    private String status;
    private String authorId;
    private List<CategoryMapping> categoryMappings;
    private LocalDateTime createdDTTM;

}