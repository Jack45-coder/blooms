package in.codingage.blooms.controller;

import in.codingage.blooms.Database;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.repository.CategoryRepository;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// step1 : put rest controller annotation
// step2 : put a common category prefix using request mapping.


@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // CRUD - Create, Read, Update, Delete
    // supplier<Integer> capacitySupplier = () -> (int) (Math.random() * 10) + 1;

    @Autowired
    private CategoryRepository categoryRepository;

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
        category.setId(RandomIdUtils.generateRandom(6));

        category.setCreatedBy("ADMIN");
        category.setActive(true);
        category.setCreatedDTTM(LocalDateTime.now());

        // local db - item save
        Database database = Database.getInstance();

        // persist category object to database
        categoryRepository.save(category);

        database.getCategoryList().add(category);
    }

    // ------------- GetCategory --------------

    @GetMapping
    public CategoryResponse getCategory(@RequestParam String categoryId){
//      List<Category> categoryList = categoryRepository.findAll();
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            CategoryResponse response = new CategoryResponse();
            response.setId(category.get().getId());
            response.setName(category.get().getName());
            response.setDescription(category.get().getDescription());
            response.setImageUrl(category.get().getImageUrl());
            return response;
        }
        return null;
    }


    @GetMapping("/all")
    public List<CategoryResponse> getCategories(){
        List<Category> categoryList = categoryRepository.findAll();
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
    public boolean deleteCategory(@RequestParam String categoryId){
        // iterate the list that comes from your database and set the active flag to false
        //return true
        // if id not found, return false;

//        List<Category> categoryList = Database.getInstance().getCategoryList();
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setActive(false);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @PutMapping
    public CategoryResponse updateCategory(@RequestParam String categoryId, CategoryRequest request){
        // fetch category by id and update its name desc and cUrl using category request
        //make sure you are updating the found category and the list...
        // return updated category
        // Validation to return from here only if id is not present.
        if(categoryId == null || request == null){
            //we will send error to UI later.
            return null;
        }
//        List<Category> categoryList = Database.getInstance().getCategoryList();
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
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