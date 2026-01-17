package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Blog {
    private String id;
    private String title;
    private String description;
    private String content;
    private String status;
    private boolean isActive;
    private String authorId;
    private LocalDateTime createdDTTM;
    private List<CategoryMapping> categoryMappings;


}
