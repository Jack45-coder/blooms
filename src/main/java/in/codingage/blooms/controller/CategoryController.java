package in.codingage.blooms.controller;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// step1 : put rest controller annotation
// step2 : put a common category prefix using request mapping.


//@CrossOrigin(
//        origins = "http://localhost:5173",
//        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
//)

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
 
    // CRUD - Create, Read, Update, Delete
    // supplier<Integer> capacitySupplier = () -> (int) (Math.random() * 10) + 1;


    // ----------- Create Category --------------------
    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        return new ApiResponse<>(true, "Category Created Successfully", categoryService.createCategory(request));
    }

    // ------------- Get Category ById -----------------
    @GetMapping("/id/{id}")
    public ApiResponse<CategoryResponse> getCategoryById(@PathVariable String id){
        return new ApiResponse<>(true, null, categoryService.getCategoryById(id));
    }

    // ------------- Get Category By Name -----------------
    @GetMapping("/name/{name}")
    public ApiResponse<CategoryResponse> getCategoryByName(@PathVariable String name){
        return new ApiResponse<>(true, null, categoryService.getCategoryByName(name));
    }

    // ------------- Get All Categories --------------
    @GetMapping("/all")
    public ApiResponse<List<CategoryResponse>> getCategories(){
        return new ApiResponse<>(true, null, categoryService.getCategories());
    }




    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@RequestBody CategoryRequest request, @PathVariable String id) {
        ////        // fetch category by id and update its name desc and cUrl using category request
        ////        //make sure you are updating the found category and the list...
        ////        // return updated category
        ////        // Validation to return from here only if id is not present.
        CategoryResponse response = categoryService.updateCategory(request, id)
                .orElseThrow(() -> new ApplicationException("Category Not Found with id: " +id));
        return new ApiResponse<>(true, "Category Updated Successfully", response);
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<String> deleteCategory(@PathVariable String categoryId) {
        // iterate the list that comes from your database and set the active flag to false
        //return true
        // if id not found, return false;
        // List<Category> categoryList = Database.getInstance().getCategoryList();

        categoryService.deleteCategory(categoryId);
        return new ApiResponse<>(true, "Category Deleted Successfully", null);
    }
}

//await api.delete("/categories", { params: { categoryId: id } });