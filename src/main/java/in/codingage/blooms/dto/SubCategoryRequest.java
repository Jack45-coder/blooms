package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryRequest {
    private String categoryId;
    private String name;
    private String description;
    private String imageUrl;
    private String createdBy;
}