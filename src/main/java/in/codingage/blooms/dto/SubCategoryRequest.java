package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubCategoryRequest {
    private String id;
    private String categoryId;
    private String subCatName;
    private String subCatDesc;
    private String subCatImageUrl;
}