package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private String id;
    private String categoryId;
    private String subCatName;
    private String subCatDesc;
    private String subCatImageUrl;





    public CategoryResponse(String id, String title, String desc, String imageUrl) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public CategoryResponse() {

    }
}