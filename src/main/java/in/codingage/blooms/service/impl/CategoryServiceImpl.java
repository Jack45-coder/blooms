package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Role;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.repository.CategoryRepository;
import in.codingage.blooms.service.CategoryService;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // map to response
    private CategoryResponse mapToResponse(Category category){
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
        response.setImageUrl(category.getImageUrl());
        return response;
    }

    // Implementation Of Create Category
    public CategoryResponse createCategory(CategoryRequest request){

        if (request == null){
            throw new IllegalArgumentException("Request cannot be null");
        }

        if (request.getName() == null || request.getDescription() == null){
            throw new IllegalArgumentException("Category name & description required");
        }

        categoryRepository.findByName(request.getName())
                .ifPresent(c -> {
                    throw new RuntimeException("Category already exist!");
                });

        Category category = new Category();
        category.setId(RandomIdUtils.generateRandom(6));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());
        category.setStatus(Status.INREVIEW.getDisplayName());
        category.setCreatedBy(Role.USER.name());
        category.setActive(true);
        category.setCreatedDTTM(LocalDateTime.now());

        categoryRepository.save(category);
        return mapToResponse(category);
    }


    // Implementation Of get All Categories:
    public List<CategoryResponse> getCategories(){
        List<Category> categoryList = categoryRepository.findAllByActiveTrue();
        return categoryList.stream().map(this::mapToResponse).toList();
    }

    // Implementation Of Update category:
    public Optional<CategoryResponse> updateCategory(CategoryRequest request ,String id){
        if(request == null || id == null || id.isEmpty()){
            throw new IllegalArgumentException("Request & Category ID required!");
        }
        return categoryRepository.findByIdAndActiveTrue(id).map(category -> {
            if(request.getName() != null) category.setName(request.getName());
            if(request.getDescription() != null) category.setDescription(request.getDescription());
            if(request.getImageUrl() != null) category.setImageUrl(request.getImageUrl());

            Category updatedCategory = categoryRepository.save(category);
            return mapToResponse(updatedCategory);
        });
    }

    public boolean deleteCategory(String categoryId){
        if (categoryId == null || categoryId.isEmpty()){
            throw new IllegalArgumentException("Category ID required!");
        }

        Category category = categoryRepository.findByIdAndActiveTrue(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found!"));

        category.setActive(false);
        categoryRepository.save(category);
        return true;
    }



}
