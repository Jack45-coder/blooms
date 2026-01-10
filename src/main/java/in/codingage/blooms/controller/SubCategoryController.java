package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.SubCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubCategoryController {
    // TODO: 09/01/2026 complete crud here

    // create SubCategory
    public void createSubcategory(SubCategoryRequest subCategoryRequest){
        // implementation here
        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.getTitle());
        subCategory.setDescription(subCategoryRequest.getDesc());
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
        if(id == null) return null;

        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();

        for(SubCategory subCategory : subCategoryList){

            if(subCategory.getId().equals(id)){
                SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
                subCategoryResponse.setId(subCategory.getId());
                subCategoryResponse.setTitle(subCategory.getName());
                subCategoryResponse.setDesc(subCategory.getDescription());
                subCategoryResponse.setCategoryId(subCategory.getCategoryId());

                return subCategoryResponse;
            }
        }
        return null;
    }

    public List<SubCategoryResponse> getSubcategories(){
        // implementation here
        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();
        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();

        for(SubCategory subCategory : subCategoryList){
            if(subCategory.isActive()){
                SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
                subCategoryResponse.setId(subCategory.getId());
                subCategoryResponse.setTitle(subCategory.getName());
                subCategoryResponse.setDesc(subCategory.getDescription());
                subCategoryResponse.setCategoryId(subCategory.getCategoryId());
                subCategoryResponses.add(subCategoryResponse);
            }
        }
        return subCategoryResponses;
    }

    // delete subcategory
    public boolean deleteSubCategory(String id){
        // implementation here
        if(id == null) return false;
        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();
        for(SubCategory subCategory : subCategoryList){
            if (subCategory.getId().equals(id)){
                subCategory.setActive(false);
                return true;
            }
        }
        return false;
    }

    // Update Subcategory
    public SubCategoryResponse updateSubCategory(String id, SubCategoryRequest request){
        // implementation here
        if (id == null || request == null) return null;

        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();

        for(SubCategory subCategory : subCategoryList){
            if(subCategory.getId().equals(id)){
                // update fields
                subCategory.setName(request.getTitle());
                subCategory.setDescription(request.getDesc());
                subCategory.setCategoryId(request.getCategoryId());

                // prepare response
                SubCategoryResponse response = new SubCategoryResponse();
                response.setId(subCategory.getId());
                response.setTitle(subCategory.getName());
                response.setDesc(subCategory.getDescription());
                response.setCategoryId(subCategory.getCategoryId());

                return response;
            }
        }
        return null;
    }

}
