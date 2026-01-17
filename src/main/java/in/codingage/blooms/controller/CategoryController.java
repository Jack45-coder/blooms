package in.codingage.blooms.controller;

import in.codingage.blooms.Database;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// step1 : put rest controller annotation
// step2 : put a common category prefix using request mapping.


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // CRUD - Create, Read, Update, Delete

    // ----------- Create Category --------------------
    @PostMapping("/create")
    public void createCategory(@RequestBody CategoryRequest categoryRequest){
        if(categoryRequest == null){
            throw new RuntimeException("Request is required!");
        }
        Category category = new Category();
        category.setName(categoryRequest.getTitle());
        category.setDescription(categoryRequest.getDesc());
        category.setImageUrl(categoryRequest.getImageUrl());

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

    @GetMapping
    public CategoryResponse getCategory(@RequestParam String categoryId){
        List<Category> categoryList = Database.getInstance().getCategoryList();

        return categoryList.stream()
                .filter(cat -> cat.getId()
                        .equals(categoryId))
                .map(cat -> {
                    CategoryResponse response = new CategoryResponse();
                    response.setId(cat.getId());
                    response.setName(cat.getName());
                    response.setDescription(cat.getDescription());
                    response.setImageUrl(cat.getImageUrl());
                    return response;
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Category not Found!"));

    }


    @GetMapping("/all")
    public List<CategoryResponse> getCategories(){
        List<Category> categoryList = Database.getInstance().getCategoryList();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for(Category category : categoryList) {
            if (category.isActive()) {
                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setImageUrl(category.getImageUrl());
                categoryResponse.setId(category.getId());
                categoryResponse.setName(category.getName());
                categoryResponse.setDescription(category.getDescription());
                categoryResponses.add(categoryResponse);
            }
        }
        return categoryResponses;
    }

    @DeleteMapping
    public boolean deleteCategory(String categoryId){
        // iterate the list that comes from your database and set the active flag to false
        //return true
        // if id not found, return false;

        List<Category> categoryList = Database.getInstance().getCategoryList();
        for (Category category : categoryList){
            if (category.getId().equals(categoryId)){
                category.setActive(false); // soft delete [db hai, you have status field which is inactive]
                return true;
            }
        }
        return false;
    }

    @PutMapping
    public CategoryResponse updateCategory(String categoryId, CategoryRequest request){
        // fetch category by id and update its name desc and cUrl using category request
        //make sure you are updating the found category and the list...
        // return updated category
        // Validation to return from here only if id is not present.
        if(categoryId == null || request == null){
            //we will send error to UI later.
            return null;
        }
        List<Category> categoryList = Database.getInstance().getCategoryList();
        for(Category category : categoryList){
            if(category.getId().equals(categoryId)){

                // update fields
                if(request.getTitle() != null) category.setName(request.getTitle());
                if (request.getDesc() != null) category.setDescription(request.getDesc());
                if (request.getImageUrl() != null) category.setImageUrl(request.getImageUrl());

                // prepare response
                CategoryResponse response = new CategoryResponse();
                response.setId(category.getId());
                response.setName(category.getName());
                response.setDescription(category.getDescription());
                response.setImageUrl(category.getImageUrl());

                return response;  // updated category response
            }
        }
        return null;
    }

}