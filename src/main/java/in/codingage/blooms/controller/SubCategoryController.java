package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.SubCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryController {
    // TODO: 09/01/2026 complete crud here

    // create SubCategory
    public void createSubcategory(SubCategoryRequest subCategoryRequest){
        // implementation here
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.getSubCatName());
        subCategory.setDescription(subCategoryRequest.getSubCatDesc());
        subCategory.setCategoryId(subCategoryRequest.getCategoryId());
        subCategory.setId(String.valueOf(System.currentTimeMillis()));
        subCategory.setCreatedDTTM(LocalDateTime.now());
        subCategory.setCreatedBy("ADMIN");
        subCategory.setStatus(Status.PUBLISHED.getDisplayName());
        subCategory.setActive(true);

        // save to db
        Database.getInstance().getSubCategoryList().add(subCategory);
    }

    // read SubCategory
    public SubCategoryResponse getSubcategory(String id){
        // implementation here
        if(id == null || id.isEmpty()) {
            throw new RuntimeException("SubCategory ID required!");
        };

        // Find SubCategory by subCategoryId
        SubCategory subCategory = Database.getInstance()
                .getSubCategoryList()
                .stream()
                .filter(sc -> sc.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SubCategory Not Found!"));

        // Find Category name using categoryId
        String categoryName = Database.getInstance()
                .getCategoryList()
                .stream()
                .filter(cat -> cat.getId().equals(subCategory.getCategoryId()))
                .map(Category::getName)
                .findFirst()
                .orElse("UNKNOWN CATEGORY");

        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .categoryId(subCategory.getCategoryId())
                .name(subCategory.getName())
                .subCatDesc(subCategory.getDescription())
                .categoryName(categoryName)
                .build();
    }

    // get all subCategories
    public List<SubCategoryResponse> getSubcategories(){
        // implementation here
        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();
        List<Category> categoryList = Database.getInstance().getCategoryList();
        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();

        for(SubCategory subCategory : subCategoryList){
            if(!subCategory.isActive()) continue;

            // find category name by categoryID
            String categoryName = categoryList.stream().filter(cat -> cat.getId().equals(subCategory.getCategoryId()))
                    .map(Category::getName)
                    .findFirst()
                    .orElse("UNKNOWN CATEGORY");

            SubCategoryResponse response = SubCategoryResponse.builder()
                    .id(subCategory.getId())
                    .name(subCategory.getName())
                    .subCatDesc(subCategory.getDescription())
                    .categoryId(subCategory.getCategoryId())
                    .categoryName(categoryName)
                    .build();

            subCategoryResponses.add(response);
        }
        return subCategoryResponses;
    }

    // delete subcategory
    public boolean deleteSubCategory(String id){
        // implementation here
        if(id == null || id.isEmpty()) {
            throw new RuntimeException("SubCategory ID required!");
        }

        SubCategory subCategory =  Database.getInstance().getSubCategoryList()
                .stream().filter(sc -> sc.getId().equals(id)).findFirst().orElse(null);

        if (subCategory == null){
            throw new RuntimeException("SubCategory not found!");
        }

        subCategory.setActive(false);
        return true;
    }

    // Update Subcategory
    public SubCategoryResponse updateSubCategory(String id, SubCategoryRequest request){
        // implementation here
        if (id == null || request == null) return null;

        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();

        for(SubCategory subCategory : subCategoryList){
            if(subCategory.getId().equals(id)){
                // update fields
                if (request.getSubCatName() != null) subCategory.setName(request.getSubCatName());
                if (request.getSubCatDesc() != null) subCategory.setDescription(request.getSubCatDesc());

                // prepare response
                SubCategoryResponse response = new SubCategoryResponse();
                response.setId(subCategory.getId());
                response.setName(subCategory.getName());
                response.setSubCatDesc(subCategory.getDescription());
                response.setCategoryId(subCategory.getCategoryId());

                return response;
            }
        }
        return null;
    }

}