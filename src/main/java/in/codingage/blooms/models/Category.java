package in.codingage.blooms.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
//@Table(name = "categories")

@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private boolean active;
    private LocalDateTime createdDTTM;
    private String createdBy;
    private String status;

}
