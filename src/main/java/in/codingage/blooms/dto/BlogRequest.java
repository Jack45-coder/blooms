package in.codingage.blooms.dto;

import in.codingage.blooms.models.CategoryMapping;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlogRequest {
    private String title;
    private String description;
    private String content;
    private String authorId;
    private List<CategoryMapping> categoryMappings;
}