package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.BlogRequest;
import in.codingage.blooms.dto.BlogResponse;
import in.codingage.blooms.models.Blog;
import in.codingage.blooms.models.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BlogController {

    public BlogResponse createBlog(BlogRequest request, String authorId){
        Blog blog = new Blog();
        blog.setId(String.valueOf(System.currentTimeMillis()));
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setAuthorId(authorId);
        blog.setStatus(Status.INERVIEW.getDisplayName());
        blog.setActive(true);
        blog.setCreatedDTTM(LocalDateTime.now());
        blog.setCategoryMappings(request.getCategoryMappings());

        List<Blog> blogs = Database.getInstance().getBlogList();
        blogs.add(blog);  // store in db

        // response
        BlogResponse response = new BlogResponse();
        response.setId(blog.getId());
        response.setTitle(blog.getTitle());
        response.setDescription(blog.getDescription());
        response.setContent(blog.getContent());
        response.setStatus(blog.getStatus());
        System.out.println("Blog created successfully.");
        return response;
    }

    // Get Blog By ID
    public BlogResponse getBlogById(String blogId){
        if (blogId == null || blogId.isEmpty()){
            System.out.println("Blog ID required!");
            return null;
        }

        List<Blog> blogs = Database.getInstance().getBlogList();
        for (Blog blog : blogs){
            if (blog.isActive() && blog.getId().equals(blogId)){
                BlogResponse response = new BlogResponse();
                response.setTitle(blog.getTitle());
                response.setDescription(blog.getDescription());
                response.setContent(blog.getContent());
                response.setAuthorId(blog.getAuthorId());
                response.setStatus(blog.getStatus());
                response.setCategoryMappings(blog.getCategoryMappings());
                response.setCreatedDTTM(blog.getCreatedDTTM());

                return response;
            }
        }

        System.out.println("Blog not found!");
        return null;
    }

    // Get Blog By AuthorId
    public List<BlogResponse> getBlogByAuthorId(String authId){
        if (authId == null || authId.isEmpty()){
            System.out.println("Blog ID required!");
            return null;
        }

        List<Blog> blogs = Database.getInstance().getBlogList();
        List<BlogResponse> responses = new ArrayList<>();
        for (Blog blog : blogs){

            if (!blog.isActive()) continue;
            if (!blog.getAuthorId().equals(authId)) continue;

            BlogResponse response = new BlogResponse();

            response.setTitle(blog.getTitle());
            response.setDescription(blog.getDescription());
            response.setContent(blog.getContent());
            response.setAuthorId(blog.getAuthorId());
            response.setStatus(Status.PUBLISHED.getDisplayName());
            response.setCategoryMappings(blog.getCategoryMappings());
            response.setCreatedDTTM(blog.getCreatedDTTM());

            responses.add(response);

        }

        if (responses.isEmpty()){
            System.out.println("No blogs found for this author!");
        }

       return responses;
    }

    // Get ALl Blog
    public List<BlogResponse> getAllBlog(){
        List<Blog> blogs = Database.getInstance().getBlogList();
        List<BlogResponse> responses = new ArrayList<>();
        for (Blog blog : blogs){
            if (blog.isActive()) {
                BlogResponse response = new BlogResponse();
                response.setId(blog.getId());
                response.setTitle(blog.getTitle());
                response.setDescription(blog.getDescription());
                response.setContent(blog.getContent());
                response.setAuthorId(blog.getAuthorId());
                response.setCategoryMappings(blog.getCategoryMappings());
                response.setCreatedDTTM(blog.getCreatedDTTM());

                responses.add(response);
            }
        }
        return responses;
    }

    // Delete Blog By ID
    public boolean deleteBlogById(String blogId){
        if (blogId == null || blogId.isEmpty()){
            System.out.println("Blog ID required!");
            return false;
        }
        List<Blog> blogs = Database.getInstance().getBlogList();
        for (Blog blog : blogs){
            if (blog.getId().equals(blogId)){
                if(!blog.isActive()){
                    System.out.println("Blog already deleted!");
                    return false;
                }
                blog.setActive(false);
                System.out.println("Blog deleted successfully!");
                return true;
            }
        }
        System.out.println("Blog not found!");
        return false;
    }

    // Delete Blog By Author ID
    public boolean deleteByAuthID(String authId){
        if (authId == null || authId.isEmpty()){
            System.out.println("Author ID required!");
            return false;
        }

        List<Blog> blogs = Database.getInstance().getBlogList();
        boolean foundActiveBlog = false;

        for (Blog blog : blogs){
            if (blog.getAuthorId().equals(authId) && blog.isActive()){
                    blog.setActive(false);
                    foundActiveBlog = true;
            }
        }
        if (foundActiveBlog) {
            System.out.println("All blogs of author deleted successfully.");
            return true;
        }

        System.out.println("No active blogs found for this author!");
        return false;
    }

    // Update Blog By ID
    public BlogResponse updateBlogById(BlogRequest request , String blogId){
        if (blogId == null || blogId.isEmpty()){
            System.out.println("Blog ID required!");
            return null;
        }
        if (request == null){
            System.out.println("Request cannot be null!");
            return null;
        }

        List<Blog> blogs = Database.getInstance().getBlogList();
        for (Blog blog : blogs) {
            if (!blog.isActive()) continue;
            if (blog.getId().equals(blogId)) {
                if (request.getTitle() != null) blog.setTitle(request.getTitle());
                if (request.getDescription() != null) blog.setDescription(request.getDescription());
                if (request.getContent() != null) blog.setContent(request.getContent());
                if (request.getCategoryMappings() != null) blog.setCategoryMappings(request.getCategoryMappings());

                BlogResponse response = new BlogResponse();

                response.setId(blog.getId());
                response.setTitle(blog.getTitle());
                response.setDescription(blog.getDescription());
                response.setContent(blog.getContent());
                response.setAuthorId(blog.getAuthorId());
                response.setStatus(Status.UPDATED.getDisplayName());
                response.setCategoryMappings(blog.getCategoryMappings());
                response.setCreatedDTTM(blog.getCreatedDTTM());

                return response;
            }
        }

        System.out.println("Blog not found!");
        return null;
    }
}
