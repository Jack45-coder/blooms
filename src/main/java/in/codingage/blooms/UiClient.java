package in.codingage.blooms;

import in.codingage.blooms.controller.CategoryController;
import in.codingage.blooms.dto.CategoryRequest;
import in.codingage.blooms.dto.CategoryResponse;
import in.codingage.blooms.models.Category;

public class UiClient {
    public static void main(String[] args) {
        System.out.println("UI Client Started");
        // Register - register a new user, we will also have an admin user
        // login - login existing user
        // admin dashboard - manage categories, sub-categories, blogs, users
        // user dashboard - view blogs, profile, settings, create blogs

        CategoryController categoryController = new CategoryController();
        categoryController.createCategory(new CategoryRequest("Technology", "All about technology", "https://www.vecteezy.com/free-vector/tech-logo"));

        for(Category category : Database.getInstance().getCategoryList()){
            System.out.println("ID: " + category.getId());
            System.out.println("Name: " + category.getName());
            System.out.println("Desc: " + category.getDescription());
            System.out.println("Url: " + category.getImageUrl());
            System.out.println("--------------------");
        }

    }
}
