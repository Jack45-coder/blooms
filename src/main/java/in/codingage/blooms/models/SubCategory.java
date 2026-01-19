package in.codingage.blooms.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
//@Table(name = "subcategories")
//@Entity
public class SubCategory {

    @Id
    private String id;
    private String categoryId;
    private String name;
    private String description;
    private boolean active;
    private String status;
    private String createdBy;
    private LocalDateTime createdDTTM;



}