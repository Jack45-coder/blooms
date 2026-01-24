package in.codingage.blooms.controller;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public CategoryResponse createCategory(@RequestBody CategoryRequest request){
        return categoryService.createCategory(request);
    }

    // ------------- GetCategory --------------
    @GetMapping("/all")
    public List<CategoryResponse> getCategories(){
        return categoryService.getCategories();
    }

        @PutMapping("/{id}")
        public CategoryResponse updateCategory(@RequestBody CategoryRequest request, @PathVariable String id) {
            ////        // fetch category by id and update its name desc and cUrl using category request
            ////        //make sure you are updating the found category and the list...
            ////        // return updated category
            ////        // Validation to return from here only if id is not present.
            return categoryService.updateCategory(request, id).orElseThrow(() ->new RuntimeException("Category not found!"));
        }

         @DeleteMapping("/{categoryId}")
         public String deleteCategory(@PathVariable String categoryId){
            // iterate the list that comes from your database and set the active flag to false
            //return true
            // if id not found, return false;

            // List<Category> categoryList = Database.getInstance().getCategoryList();

             try {
                 boolean deleted = categoryService.deleteCategory(categoryId);
                 return deleted ? "Category Deleted Successfully" : "Category Not Found!";
             }catch (RuntimeException e){
                 return "Error: " + e.getMessage();
             }
    }
}

//await api.delete("/categories", { params: { categoryId: id } });