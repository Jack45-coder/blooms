package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String createdBy;
    private String status;

    public CategoryResponse() {
    }

}