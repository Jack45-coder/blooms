package in.codingage.blooms.service;

import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponse createSubCategory(SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> getAll();

    boolean delete(String subCategoryId);

    SubCategoryResponse updateSubcategory(SubCategoryRequest request, String id);

    SubCategoryResponse getSubCatById(String id);

    SubCategoryResponse getSubCatByName(String name);

    List<SubCategoryResponse> getSubCategoriesByCategoryId(String categoryId);
}
