package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.BlogRequest;
import in.codingage.blooms.dto.BlogResponse;
import in.codingage.blooms.dto.CategoryDetail;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping("/categories")
    public ApiResponse<List<CategoryDetail>> getAllCategoriesWithSubCategories() {
        return new ApiResponse<>(true, null, blogService.getAllCategoriesWithSubCategories());
    }

    //CREATE BLOG
    @PostMapping
    public ApiResponse<BlogResponse> createBlog(@RequestBody BlogRequest request) {
        return new ApiResponse<>(true, "Blog Created Successfully", blogService.createBlog(request, request.getAuthorId()));
    }

    // Get ALl Blog
    @GetMapping("/all")
    public ApiResponse<List<BlogResponse>> getAllBlogs() {
        return new ApiResponse<>(true, null, blogService.getAllBlogs());
    }

    // Get Blog By ID
    @GetMapping("/{blogId}")
    public ApiResponse<BlogResponse> getBlogById(@PathVariable String blogId) {
        return new ApiResponse<>(true, null, blogService.getBlogById(blogId));
    }

    // Get Blog By AuthorId
    @GetMapping("/author/{authorId}")
    public ApiResponse<List<BlogResponse>> getBlogByAuthorId(@PathVariable String authorId) {
        return new ApiResponse<>(true, null, blogService.getBlogByAuthorId(authorId));
    }

    // Delete Blog By ID
    @DeleteMapping("/{blogId}")
    public ApiResponse<Boolean> deleteBlogById(@PathVariable String blogId) {
        boolean deleted =  blogService.deleteBlogById(blogId);
        if (!deleted){
            throw new ApplicationException("Blog Not Found with id: " + blogId);
        }
        return new ApiResponse<>(true, "Blog Deleted Successfully",null);
    }

    // Update Blog By ID
    @PutMapping("/{blogId}")
    public ApiResponse<BlogResponse> updateBlog(@RequestBody BlogRequest request, @PathVariable String blogId){
        BlogResponse response = blogService.updateBlog(request, blogId);
        return new ApiResponse<>(true, "Blog Updated Successfully", response);
    }









//    // Delete Blog By Author ID
//    public boolean deleteByAuthID(String authId){
//        if (authId == null || authId.isEmpty()){
//            System.out.println("Author ID required!");
//            return false;
//        }
//
//        List<Blog> blogs = Database.getInstance().getBlogList();
//        boolean foundActiveBlog = false;
//
//        for (Blog blog : blogs){
//            if (blog.getAuthorId().equals(authId) && blog.isActive()){
//                    blog.setActive(false);
//                    foundActiveBlog = true;
//            }
//        }
//        if (foundActiveBlog) {
//            System.out.println("All blogs of author deleted successfully.");
//            return true;
//        }
//
//        System.out.println("No active blogs found for this author!");
//        return false;
//    }
//
//    // Update Blog By ID
//    public BlogResponse updateBlogById(BlogRequest request , String blogId){
//        if (blogId == null || blogId.isEmpty()){
//            System.out.println("Blog ID required!");
//            return null;
//        }
//        if (request == null){
//            System.out.println("Request cannot be null!");
//            return null;
//        }
//
//        List<Blog> blogs = Database.getInstance().getBlogList();
//        for (Blog blog : blogs) {
//            if (!blog.isActive()) continue;
//            if (blog.getId().equals(blogId)) {
//                if (request.getTitle() != null) blog.setTitle(request.getTitle());
//                if (request.getDescription() != null) blog.setDescription(request.getDescription());
//                if (request.getContent() != null) blog.setContent(request.getContent());
//                if (request.getCategoryMappings() != null) blog.setCategoryMappings(request.getCategoryMappings());
//
//                BlogResponse response = new BlogResponse();
//
//                response.setId(blog.getId());
//                response.setTitle(blog.getTitle());
//                response.setDescription(blog.getDescription());
//                response.setContent(blog.getContent());
//                response.setAuthorId(blog.getAuthorId());
//                response.setStatus(Status.UPDATED.getDisplayName());
//                response.setCategoryMappings(blog.getCategoryMappings());
//                response.setCreatedDTTM(blog.getCreatedDTTM());
//
//                return response;
//            }
//        }
//
//        System.out.println("Blog not found!");
//        return null;
//    }
}
