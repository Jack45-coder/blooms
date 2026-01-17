package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Category {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean active;
    private LocalDateTime createdDTTM;
    private String createdBy;
    private String status;

}
