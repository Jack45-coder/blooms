package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.SubCategory;
import in.codingage.blooms.repository.CategoryRepository;
import in.codingage.blooms.repository.SubCategoryRepository;
import in.codingage.blooms.service.SubCategoryService;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    // map to response
    private SubCategoryResponse mapToResponse(SubCategory subCategory){
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .desc(subCategory.getDescription())
                .categoryId(subCategory.getCategoryId())
                .build();
    }

    @Override
    public SubCategoryResponse createSubCategory(SubCategoryRequest subCategoryRequest) {
        // Implementation for creating a SubCategory
        if (subCategoryRequest == null) throw new RuntimeException("Request null not required!");

        String catId = subCategoryRequest.getCategoryId();
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new RuntimeException("Id not found!"));

        if (category.isActive()) {

            SubCategory subCategory = new SubCategory();
            subCategory.setName(subCategoryRequest.getName());
            subCategory.setDescription(subCategoryRequest.getDescription());
            subCategory.setCategoryId(subCategoryRequest.getCategoryId());
            subCategory.setId(RandomIdUtils.generateRandom(6));
            subCategory.setCreatedDTTM(LocalDateTime.now());
            subCategory.setCreatedBy("ADMIN");
            subCategory.setStatus(Status.PUBLISHED.getDisplayName());
            subCategory.setActive(true);

            // save in db
            subCategoryRepository.save(subCategory);
            return mapToResponse(subCategory);
        }
        throw new RuntimeException("Parent category is inactive! ID: " + catId);
    }


    public List<SubCategoryResponse> getAll(){
        List<SubCategory> subCategories = subCategoryRepository.findAllByActiveTrue();
        return subCategories.stream().map(this::mapToResponse).toList();
    }

    public boolean delete(String subCategoryId){
        SubCategory subCategory = subCategoryRepository.findByIdAndActiveTrue(subCategoryId).orElseThrow(() -> new RuntimeException("Subcategory not found!"));
        subCategory.setActive(false);
        subCategoryRepository.save(subCategory);
        return true;
    }


}
