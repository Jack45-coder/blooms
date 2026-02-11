package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.BlogRequest;
import in.codingage.blooms.dto.BlogResponse;
import in.codingage.blooms.dto.CategoryDetail;
import in.codingage.blooms.dto.SubCategoryDetail;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.models.Blog;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.Status;
import in.codingage.blooms.models.SubCategory;
import in.codingage.blooms.repository.BlogRepository;
import in.codingage.blooms.repository.CategoryRepository;
import in.codingage.blooms.repository.SubCategoryRepository;
import in.codingage.blooms.service.BlogService;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    // Helper method to map Blog to BlogResponse
    private BlogResponse mapToResponse(Blog blog) {
        BlogResponse response = new BlogResponse();
        response.setId(blog.getId());
        response.setTitle(blog.getTitle());
        response.setDescription(blog.getDescription());
        response.setContent(blog.getContent());
        response.setStatus(blog.getStatus());
        response.setAuthorId(blog.getAuthorId());
        response.setCreatedDTTM(blog.getCreatedDTTM());
        response.setCategoryMappings(blog.getCategoryMappings());
        return response;
    }

    public List<CategoryDetail> getAllCategoriesWithSubCategories() {
        List<CategoryDetail> categoryDetails = new ArrayList<>();
        List<Category> categories = categoryRepository.findAllByActiveTrue();
        categories.forEach(category -> {
            List<SubCategory> subCategories = subCategoryRepository.findAllByCategoryIdAndActiveTrue(category.getId());
            // you can map the subCategories to your CategoryDetail DTO as needed.
            CategoryDetail categoryDetail = new CategoryDetail();
            categoryDetail.setCategoryId(category.getId());
            categoryDetail.setName(category.getName());
            // Assuming you have a method to set sub categories
            List<SubCategoryDetail> subCategoryDetails = new ArrayList<>();
            subCategories.forEach(subCategory -> {
                SubCategoryDetail detail = new SubCategoryDetail();
                detail.setSubCategoryId(subCategory.getId());
                detail.setName(subCategory.getName());
                subCategoryDetails.add(detail);
            });
            categoryDetail.setSubCategoryDetailList(subCategoryDetails);
            categoryDetails.add(categoryDetail);
        });
        return categoryDetails;
    }

    //Implementation of CREATE Blogs By AuthorID
    public BlogResponse createBlog(BlogRequest request, String authorId){

        if (request == null || request.getTitle() == null || request.getTitle().isEmpty()){
            throw new ApplicationException("Blog title is required!");
        }

        if (authorId == null || authorId.isEmpty()){
            throw new ApplicationException("Author ID is required!");
        }

        Blog blog = new Blog();
        blog.setId(RandomIdUtils.generateRandom(7));
        blog.setAuthorId(authorId);
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setActive(true);
        blog.setStatus(Status.PUBLISHED.toString());
        blog.setCreatedDTTM(LocalDateTime.now());
        blog.setCategoryMappings(request.getCategoryMappings());

        Blog saveBlog = blogRepository.save(blog);

        return mapToResponse(saveBlog);
    }

    // Implementation Of Find all Blogs:
    public List<BlogResponse> getAllBlogs(){
        List<Blog> blogs = blogRepository.findByActiveTrue();
        List<BlogResponse> responses = new ArrayList<>();
        for (Blog blog : blogs){
            BlogResponse response = new BlogResponse();
            response.setId(blog.getId());
            response.setTitle(blog.getTitle());
            response.setDescription(blog.getDescription());
            response.setContent(blog.getContent());
            response.setAuthorId(blog.getAuthorId());
            response.setCreatedDTTM(blog.getCreatedDTTM());
            response.setCategoryMappings(blog.getCategoryMappings());
            response.setStatus(blog.getStatus());
            responses.add(response);
        }
        return responses;
    }

    // Implementation Of Find Blog By BlogID:
    public BlogResponse getBlogById(String blogId){
        if (blogId == null || blogId.isEmpty()) {
            throw new ApplicationException("Blog ID required!");
        }

        Blog blog = blogRepository
                .findById(blogId)
                .filter(b -> b.isActive())
                .orElseThrow(() -> new ApplicationException("Blog not found with id: " + blogId));

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

    // Implementation Of Find all Blogs By AuthorID:
    public List<BlogResponse> getBlogByAuthorId(String authId){
        List<Blog> blogs = blogRepository.findByAuthorIdAndActiveTrue(authId);

        return blogs.stream().map(blog ->{
            BlogResponse response = new BlogResponse();
            response.setTitle(blog.getTitle());
            response.setDescription(blog.getDescription());
            response.setContent(blog.getContent());
            response.setAuthorId(blog.getAuthorId());
            response.setStatus(Status.PUBLISHED.getDisplayName());
            response.setCategoryMappings(blog.getCategoryMappings());
            response.setCreatedDTTM(blog.getCreatedDTTM());
            return response;
        }).toList();
    }

    // Implementation Of Delete Blog By blogID:
    public boolean deleteBlogById(String blogId, String loggedInUserId){
        if(blogId == null || blogId.isEmpty()){
            throw new ApplicationException("Blog ID required!");
        }

        Blog blog = blogRepository.findByIdAndActiveTrue(blogId).orElseThrow(() -> new ApplicationException("Blog not found with ID: " +blogId));

        // 🔐 OWNER VALIDATION LOGIC
        if(!blog.getAuthorId().equals(loggedInUserId)){
            throw new ApplicationException("You are not allowed to delete this blog");
        }
        blog.setActive(false);
        blogRepository.save(blog);
        return true;
    }

    // Implementation of UpdateBlog By ID
    public BlogResponse updateBlog(BlogRequest request, String blogId, String loggedInUserId){
        Blog blog = blogRepository.findByIdAndActiveTrue(blogId).orElseThrow(() -> new ApplicationException("ID not found!"));

        if (!blog.getAuthorId().equals(loggedInUserId)) {
            throw new ApplicationException("You are not allowed to edit this blog");
        }

        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        blog.setCategoryMappings(request.getCategoryMappings());

        Blog updatedBlog = blogRepository.save(blog);
        return mapToResponse(updatedBlog);
    }
}
