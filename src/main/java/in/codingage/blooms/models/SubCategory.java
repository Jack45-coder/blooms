package in.codingage.blooms.models;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubCategory {
    private String id;
    private String categoryId;
    private String name;
    private String description;
    private boolean active;
    private String status;
    private String createdBy;
    private LocalDateTime createdDTTM;



}