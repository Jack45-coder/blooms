package in.codingage.blooms.service;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Status;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryResponse createCategory(HttpServletRequest httpServletRequest, CategoryRequest request);

    List<CategoryResponse> getCategories();

    List<CategoryResponse> getInReviewCategories();

    Optional<CategoryResponse> updateCategory(CategoryRequest request, String id);

    boolean deleteCategory(String categoryId);

    CategoryResponse getCategoryById(String id);

    CategoryResponse getCategoryByName(String name);

    CategoryResponse updateCategoryStatus(HttpServletRequest httpServletRequest, String categoryId, Status status);
}
