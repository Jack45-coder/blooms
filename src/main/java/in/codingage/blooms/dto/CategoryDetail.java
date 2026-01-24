package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class CategoryDetail {
    @Id
    private String categoryId;
    private String name;

    private List<SubCategoryDetail> subCategoryDetailList;
}
