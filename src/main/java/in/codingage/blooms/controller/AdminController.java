package in.codingage.blooms.controller;

import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserRepository;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.service.CategoryService;
import in.codingage.blooms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://blooms-ui.onrender.com"})
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // get all users
    @GetMapping("/account/all")
    public List<User> getAllUsers(@RequestParam int page, @RequestParam int size){
        Sort sort = Sort.by("name").ascending();
        PageRequest pageRequest = PageRequest.of(page, size).withSort(sort);
        return userRepository.findAll(pageRequest).getContent();
    }

    // ------------- Get All Categories --------------
    @GetMapping("/all/categories/InReview")
    public ApiResponse<List<CategoryResponse>> getInReviewCategories(){
        return new ApiResponse<>(true, null, categoryService.getInReviewCategories());
    }

    // ----------- Create Category --------------------
    @PostMapping("/category/create")
    public ApiResponse<CategoryResponse> createCategory(HttpServletRequest httpServletRequest, @RequestBody CategoryRequest request){
        return new ApiResponse<>(true, "Category Created Successfully", categoryService.createCategory(httpServletRequest, request));
    }

    // update category
    @PutMapping("/category/update/{id}")
    public ApiResponse<CategoryResponse> updateCategory(@RequestBody CategoryRequest request, @PathVariable String id) {
        ////        // fetch category by id and update its name desc and cUrl using category request
        ////        //make sure you are updating the found category and the list...
        ////        // return updated category
        ////        // Validation to return from here only if id is not present.
        CategoryResponse response = categoryService.updateCategory(request, id)
                .orElseThrow(() -> new ApplicationException("Category Not Found with id: " +id));
        return new ApiResponse<>(true, "Category Updated Successfully", response);
    }

    // update status
    @PutMapping("/category/update/status")
    public ApiResponse<CategoryResponse> categoryUpdateStatus(HttpServletRequest httpServletRequest, @RequestParam String categoryId, @RequestParam Status status){
        return new ApiResponse<>(true, "Category Status updated successfully", categoryService.updateCategoryStatus(httpServletRequest, categoryId, status));
    }

    @DeleteMapping("/account/delete/{id}")
    public ApiResponse<User> deleteById(@PathVariable String id){
        return new ApiResponse<>(true, "User Deleted Successfully", userService.deleteById(id));
    }

}
