package in.codingage.blooms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SubCategoryResponse {
    private String id;
    private String categoryId;
    private String name;
    private String desc;

}