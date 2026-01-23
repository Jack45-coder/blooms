package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.dto.SubCategoryResponse;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.SubCategory;
import in.codingage.blooms.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/subcategories")
public class SubCategoryController {
    // TODO: 09/01/2026 complete crud here

    @Autowired
    private SubCategoryService subCategoryService;

    // create SubCategory
    @PostMapping
    public SubCategoryResponse createSubcategory(@RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.createSubCategory(subCategoryRequest);
    }

    @GetMapping
    public List<SubCategoryResponse> getSubCategories(){
        return subCategoryService.getAll();
    }

    @DeleteMapping("/{subCategoryId}")
    public boolean deleteSubCategory(@PathVariable String subCategoryId){
        return subCategoryService.delete(subCategoryId);
    }

    @PutMapping("/{id}")
    public SubCategoryResponse updateSubcategory(@PathVariable SubCategoryRequest request, String id){
        return subCategoryService.updateSubcategory(request, id);
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