package in.codingage.blooms.service;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest request);

    List<CategoryResponse> getCategories();

    Optional<CategoryResponse> updateCategory(CategoryRequest request, String id);

    boolean deleteCategory(String categoryId);

    CategoryResponse getCategoryById(String id);

    CategoryResponse getCategoryByName(String name);
}
