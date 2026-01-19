package in.codingage.blooms.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
//@Table(name = "blogs")
//@Entity
public class Blog {

    @Id
    private String id;
    private String title;
    private String description;
    private String content;
    private String status;
    private boolean isActive;
    private String authorId;
    private LocalDateTime createdDTTM;

//    @ElementCollection
    private List<CategoryMapping> categoryMappings;


}
