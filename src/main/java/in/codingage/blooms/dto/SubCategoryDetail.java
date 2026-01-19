package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class SubCategoryDetail {
    @Id
    private String subCategoryId;
    private String name;

}
