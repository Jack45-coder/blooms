package in.codingage.blooms;

import in.codingage.blooms.controller.*;
import in.codingage.blooms.dto.*;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.CategoryMapping;
import in.codingage.blooms.models.SubCategory;
import in.codingage.blooms.models.User;

import java.util.ArrayList;
import java.util.List;

public class UiClient {
    public static void main(String[] args) {
        System.out.println("UI Client Started");
        // Register - register a new user, we will also have an admin user
        // signin - signin existing user
        // admin dashboard - manage categories, sub-categories, blogs, users
        // user dashboard - view blogs, profile, settings, create blogs

//        UserController userController = new UserController();
//        userController.signup(new UserRequest("Jack1234", "jack1234@gmail.com", "Jackey Kumar", "12345678", "https://jackeyImage.png"));
//
//        AdminController adminController = new AdminController();
//        adminController.signup(new AdminRequest("Rishi1234", "rishi1234@gmail.com", "rishiSir123"));
//
//        CategoryController categoryController = new CategoryController();
//        categoryController.createCategory(new CategoryRequest("Technology", "All about technology", "https://www.vecteezy.com/free-vector/tech-logo"));
//
//        Category cate = Database.getInstance().getCategoryList().get(0);

//        SubCategoryController subCategoryController = new SubCategoryController();
//        subCategoryController.createSubcategory(new SubCategoryRequest("Tech", "Tech is all about Technology", cate.getId()));

//        CategoryMapping categoryMapping = new CategoryMapping();
//        categoryMapping.setCategoryId("CAT001");
//        categoryMapping.setCategoryId("");
//
//        List<CategoryMapping> categoryMappings = new ArrayList<>();
//        categoryMappings.add(categoryMapping);
//
//        BlogRequest blogRequest = new BlogRequest("Java Basics", "This blog is about Java fundamentals", "Java is an object-oriented programming language...", categoryMappings);
//        BlogRequest blogRequest2 = new BlogRequest("Python", "This blog is about PYTHON Programming", "Python is an fundamental programming language...", categoryMappings);
//        BlogController blogController = new BlogController();
//
//        BlogResponse createdBlog = blogController.createBlog(blogRequest,"AUTH001");
//        BlogResponse createdBlog2 = blogController.createBlog(blogRequest2, "AUTH001");
//        BlogRequest updateRequest = new BlogRequest();
//        updateRequest.setTitle("Advanced Java Concepts");
//        updateRequest.setContent("Updated Java content...");
//        blogController.updateBlogById(updateRequest, createdBlog.getId());
//        blogController.deleteByAuthID("AUTH001");


//        String blogId = createdBlog.getId();
//        BlogResponse blog = blogController.getBlogById(blogId);
//        System.out.println(blog.getAuthorId());
//        System.out.println(blog.getTitle());
//        System.out.println(blog.getDescription());
//        System.out.println(blog.getCategoryMappings());

//          List<BlogResponse> blogs = blogController.getAllBlogs();
//          if(blogs.isEmpty()){
//              System.out.println("No blog found!");
//          }else {
//              for(BlogResponse blog : blogs){
//                  System.out.println(blog.getId());
//                  System.out.println(blog.getTitle());
//                  System.out.println(blog.getDescription());
//                  System.out.println(blog.getAuthorId());
//              }
//          }



//        for(Category category : Database.getInstance().getCategoryList()){
//            System.out.println("ID: " + category.getId());
//            System.out.println("Name: " + category.getTitle());
//            System.out.println("Desc: " + category.getDescription());
//            System.out.println("Url: " + category.getcUrl());
//            System.out.println("--------------------");
//        }

//        for (SubCategory subCategory : Database.getInstance().getSubCategoryList()){
//            System.out.println("Category ID: " + subCategory.getCategoryId());
//            System.out.println("SubCategory ID: " + subCategory.getId());
//            System.out.println("Title: " + subCategory.getTitle());
//            System.out.println("Description: " + subCategory.getDescription());
//            System.out.println("___________________________");
//        }
//
//        for (User user : Database.getInstance().getUserList()){
//            System.out.println("Name: " + user.getTitle());
//            System.out.println("Username: " + user.getUsername());
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Profile Link: " + user.getProfileUrl());
//            System.out.println("--------------------------------");
//        }

//        userController.signin(new UserRequest("Jack1234", "12345678"));
//        adminController.signinAdmin(new AdminRequest("Rishi1234","rishi1234@gmail.com", "rishiSir123"));

    }
}
