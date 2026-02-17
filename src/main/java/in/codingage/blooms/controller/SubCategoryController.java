package in.codingage.blooms.controller;

import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/subcategories")
public class SubCategoryController {
    // TODO: 09/01/2026 complete crud here

    @Autowired
    private SubCategoryService subCategoryService;

    // create SubCategory
    @PostMapping
    public ApiResponse<SubCategoryResponse> createSubcategory(@RequestBody SubCategoryRequest subCategoryRequest){
        return new ApiResponse<>(true, "Created Subcategories Successfully", subCategoryService.createSubCategory(subCategoryRequest));
    }

    // Get SubCategory By ID
    @GetMapping("/id/{id}")
    public ApiResponse<SubCategoryResponse> getSubCatById(@PathVariable String id){
        return new ApiResponse<>(true, null, subCategoryService.getSubCatById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<List<SubCategoryResponse>> getSubCategoriesByCategoryId(@PathVariable String categoryId){
        List<SubCategoryResponse> responses = subCategoryService.getSubCategoriesByCategoryId(categoryId);
        return new ApiResponse<>(true, null, responses);
    }

    // Get SubCategory By Name
    @GetMapping("/name/{name}")
    public ApiResponse<SubCategoryResponse> getSubCatByName(String name){
        return new ApiResponse<>(true, null, subCategoryService.getSubCatByName(name));
    }

    @GetMapping("/all")
    public ApiResponse<List<SubCategoryResponse>> getSubCategories(){
        return new ApiResponse<>(true, null, subCategoryService.getAll());
    }

    @DeleteMapping("/{subCategoryId}")
    public ApiResponse<String> deleteSubCategory(@PathVariable String subCategoryId){
        subCategoryService.delete(subCategoryId);
        return new ApiResponse<>(true, "Deleted Subcategory Successfully", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<SubCategoryResponse> updateSubcategory(@RequestBody SubCategoryRequest request, @PathVariable String id){
        SubCategoryResponse response = subCategoryService.updateSubcategory(request, id);
        return new ApiResponse<>(true, "Updated Subcategory Successfully", response);
    }

//    // read SubCategory
//    @GetMapping
//    public SubCategoryResponse getSubcategory(String id){
//        // implementation here
//        if(id == null || id.isEmpty()) {
//            throw new RuntimeException("SubCategory ID required!");
//        };
//
//        // Find SubCategory by subCategoryId
//        SubCategory subCategory = Database.getInstance()
//                .getSubCategoryList()
//                .stream()
//                .filter(sc -> sc.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("SubCategory Not Found!"));
//
//        // Find Category name using categoryId
//        String categoryName = Database.getInstance()
//                .getCategoryList()
//                .stream()
//                .filter(cat -> cat.getId().equals(subCategory.getCategoryId()))
//                .map(Category::getName)
//                .findFirst()
//                .orElse("UNKNOWN CATEGORY");
//
//        return SubCategoryResponse.builder()
//                .id(subCategory.getId())
//                .categoryId(subCategory.getCategoryId())
//                .name(subCategory.getName())
//                .desc(subCategory.getDescription())
//                .categoryName(categoryName)
//                .build();
//    }
//
//    // get all subCategories
//    @GetMapping("/all")
//    public List<SubCategoryResponse> getSubcategories(){
//        // implementation here
//        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();
//        List<Category> categoryList = Database.getInstance().getCategoryList();
//        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();
//
//        for(SubCategory subCategory : subCategoryList){
//            if(!subCategory.isActive()) continue;
//
//            // find category name by categoryID
//            String categoryName = categoryList.stream().filter(cat -> cat.getId().equals(subCategory.getCategoryId()))
//                    .map(Category::getName)
//                    .findFirst()
//                    .orElse("UNKNOWN CATEGORY");
//             SubCategoryResponse response = SubCategoryResponse.builder()
//                    .id(subCategory.getId())
//                    .name(subCategory.getName())
//                    .desc(subCategory.getDescription())
//                    .categoryId(subCategory.getCategoryId())
//                    .categoryName(categoryName)
//                    .build();
//
//            subCategoryResponses.add(response);
//        }
//        return subCategoryResponses;
//    }
//
//    // delete subcategory
//    @DeleteMapping
//    public boolean deleteSubCategory(String id){
//        // implementation here
//        if(id == null || id.isEmpty()) {
//            throw new RuntimeException("SubCategory ID required!");
//        }
//
//        SubCategory subCategory =  Database.getInstance().getSubCategoryList()
//                .stream().filter(sc -> sc.getId().equals(id)).findFirst().orElse(null);
//
//        if (subCategory == null){
//            throw new RuntimeException("SubCategory not found!");
//        }
//
//        subCategory.setActive(false);
//        return true;
//    }
//
//    // Update Subcategory
//    @PutMapping
//    public SubCategoryResponse updateSubCategory(String id, SubCategoryRequest request){
//        // implementation here
//        if (id == null || request == null) return null;
//
//        List<SubCategory> subCategoryList = Database.getInstance().getSubCategoryList();
//
//        for(SubCategory subCategory : subCategoryList){
//            if(subCategory.getId().equals(id)){
//                // update fields
//                if (request.getName() != null) subCategory.setName(request.getName());
//                if (request.getDescription() != null) subCategory.setDescription(request.getDescription());
//
//                // prepare response
//                return SubCategoryResponse.builder()
//                        .id(id)
//                        .categoryId(request.getCategoryId())
//                        .categoryName(request.getName())
//                        .name(request.getName())
//                        .desc(request.getDescription())
//                        .build();
//            }
//        }
//        return null;
//    }

}