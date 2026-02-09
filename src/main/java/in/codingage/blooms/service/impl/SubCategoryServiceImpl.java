package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;
import in.codingage.blooms.exception.ApplicationException;
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
import java.util.Optional;

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
        if (subCategoryRequest == null) throw new ApplicationException("Request null not required!");

        String catId = subCategoryRequest.getCategoryId();
        Category category = categoryRepository.findById(catId).orElseThrow(() -> new ApplicationException("Id not found!"));

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
        throw new ApplicationException("Parent category is inactive! ID: " + catId);
    }


    public List<SubCategoryResponse> getAll(){
        List<SubCategory> subCategories = subCategoryRepository.findAllByActiveTrue();
        return subCategories.stream().map(this::mapToResponse).toList();
    }

    public boolean delete(String subCategoryId){
        SubCategory subCategory = subCategoryRepository.findByIdAndActiveTrue(subCategoryId).orElseThrow(() -> new ApplicationException("Subcategory not found!"));
        subCategory.setActive(false);
        subCategoryRepository.save(subCategory);
        return true;
    }

    public SubCategoryResponse updateSubcategory(SubCategoryRequest request, String id){
        if (request == null || id == null || id.isEmpty()){
            throw new ApplicationException("Subcategory ID required!");
        }

       SubCategory subCategory = subCategoryRepository.findById(id)
               .orElseThrow(() -> new ApplicationException("SubCategory not found: " + id));

        if(!subCategory.isActive()){
            throw new ApplicationException("Subcategory is deleted! not updated!");
        }
        if (request.getName() != null) subCategory.setName(request.getName());
        if (request.getDescription() != null) subCategory.setDescription(request.getDescription());
        if (request.getCategoryId() != null) subCategory.setCategoryId(request.getCategoryId());

        SubCategory updatedSubCategory = subCategoryRepository.save(subCategory);
        return mapToResponse(updatedSubCategory);
    }

    public SubCategoryResponse getSubCatById(String id){
        if(id == null || id.isEmpty()){
            throw new ApplicationException("SubCategory ID required!");
        }

        SubCategory subCategory = subCategoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ApplicationException("Not Found Subcategory with ID: "+id));
        return mapToResponse(subCategory);
    }

    public SubCategoryResponse getSubCatByName(String name){
        if(name == null || name.isEmpty()){
            throw new ApplicationException("SubCategory name required!");
        }

        SubCategory subCategory = subCategoryRepository.findByNameAndActiveTrue(name)
                .orElseThrow(() -> new ApplicationException("SubCategory not found with name: " + name));
        return mapToResponse(subCategory);
    }


}
