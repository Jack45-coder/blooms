package in.codingage.blooms;

import in.codingage.blooms.controller.CategoryController;
import in.codingage.blooms.controller.SubCategoryController;
import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.SubCategoryRequest;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.SubCategory;

public class UiClient {
    public static void main(String[] args) {
        System.out.println("UI Client Started");
        // Register - register a new user, we will also have an admin user
        // login - login existing user
        // admin dashboard - manage categories, sub-categories, blogs, users
        // user dashboard - view blogs, profile, settings, create blogs

        CategoryController categoryController = new CategoryController();
        categoryController.createCategory(new CategoryRequest("Technology", "All about technology", "https://www.vecteezy.com/free-vector/tech-logo"));

        Category cate = Database.getInstance().getCategoryList().get(0);

        SubCategoryController subCategoryController = new SubCategoryController();
        subCategoryController.createSubcategory(new SubCategoryRequest("Tech", "Tech is all about Technology", cate.getId()));

        for(Category category : Database.getInstance().getCategoryList()){
            System.out.println("ID: " + category.getId());
            System.out.println("Name: " + category.getName());
            System.out.println("Desc: " + category.getDescription());
            System.out.println("Url: " + category.getImageUrl());
            System.out.println("--------------------");
        }

        for (SubCategory subCategory : Database.getInstance().getSubCategoryList()){
            System.out.println("Category ID: " + subCategory.getCategoryId());
            System.out.println("SubCategory ID: " + subCategory.getId());
            System.out.println("Title: " + subCategory.getName());
            System.out.println("Description: " + subCategory.getDescription());
            System.out.println("___________________________");
        }

    }
}
