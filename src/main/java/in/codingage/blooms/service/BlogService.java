package in.codingage.blooms.service;
import in.codingage.blooms.dto.BlogRequest;
import in.codingage.blooms.dto.BlogResponse;
import in.codingage.blooms.dto.CategoryDetail;

import java.util.List;
import java.util.Optional;


public interface BlogService {
    List<CategoryDetail> getAllCategoriesWithSubCategories();

    BlogResponse createBlog(BlogRequest request, String authorId);

    List<BlogResponse> getAllBlogs();

    BlogResponse getBlogById(String blogId);

    List<BlogResponse> getBlogByAuthorId(String authId);

    boolean deleteBlogById(String blogId);

    BlogResponse updateBlog(BlogRequest request, String blogId);
}
