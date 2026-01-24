package in.codingage.blooms.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//@Table(name = "subcategories")
//@Entity
@Document(collection = "subcategories")
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