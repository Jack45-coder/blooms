package in.codingage.blooms.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private String title;
    private String desc;
    private String imageUrl;

    public  CategoryRequest() {
    }

    public CategoryRequest(String title, String desc, String imageUrl){
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }
}