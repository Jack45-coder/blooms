package in.codingage.blooms.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryResponse {
    private String id;
    private String categoryId;
    private String categoryName;
    private String name;
    private String subCatDesc;

}