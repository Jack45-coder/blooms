package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CategoryController {

    // CRUD - Create, Read, Update, Delete

    // ----------- Create Category --------------------
    public void createCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getTitle());
        category.setDescription(categoryRequest.getTitle());
        category.setImageUrl(categoryRequest.getcUrl());

        // for now lets give this access only to admins
        category.setStatus(Status.PUBLISHED.getDisplayName());
        category.setId(String.valueOf(System.currentTimeMillis()));

        category.setCreatedBy("ADMIN");
        category.setActive(true);
        category.setCreatedDTTM(LocalDateTime.now());

        Database database = Database.getInstance();
        database.getCategoryList().add(category);
    }

    // ------------- GetCategory --------------

    public CategoryResponse getCategory(String categoryId){
        List<Category> categoryList = Database.getInstance().getCategoryList();
        for(Category category : categoryList){
            if (category.getId().equals(categoryId)){
                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setcUrl(category.getImageUrl());
                categoryResponse.setId(category.getId());
                categoryResponse.setTitle(category.getName());
                categoryResponse.setDesc(category.getDescription());
                return categoryResponse;
            }
        }
        return null;
    }


    public List<CategoryResponse> getCategories(){
        List<Category> categoryList = Database.getInstance().getCategoryList();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category : categoryList) {
            if (category.isActive()) {
                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setcUrl(category.getImageUrl());
                categoryResponse.setId(category.getId());
                categoryResponse.setTitle(category.getName());
                categoryResponse.setDesc(category.getDescription());
                categoryResponses.add(categoryResponse);
            }
        }
        return categoryResponses;
    }

    public boolean deleteCategory(String categoryId){
        // iterate the list that comes from your database and set the active flag to false
        //return true
        // if id not found, return false;

        return true;
    }

    public CategoryResponse updateCategory(){
        // fetch category by id and update its name desc and cUrl using category request
        //make sure you are updating the found category and the list...
         // return updated category
        return null;
    }

}
