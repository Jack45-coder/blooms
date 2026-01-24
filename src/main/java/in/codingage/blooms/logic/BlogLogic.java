package in.codingage.blooms.logic;

import in.codingage.blooms.models.Blog;
import in.codingage.blooms.models.CategoryMapping;

import java.util.ArrayList;
import java.util.List;

public class BlogLogic {
    public static void main(String[] args) {
        BlogLogic blogLogic = new BlogLogic();
        blogLogic.createBlog();
    }

    // createBlog
   public void createBlog(){
       Blog blog = new Blog();
       blog.setAuthorId("author001");
       blog.setTitle("AI Impacts and Future");
       blog.setDescription("AI is transforming the world...");
       List<CategoryMapping> categoryMappings = new ArrayList<>();

       // -------- Category A --------

       CategoryMapping categoryMappingA = new CategoryMapping();
       categoryMappingA.setCategoryId("cat001");

       List<String> subCategoryIdsA = new ArrayList<>();
       subCategoryIdsA.add("cat001_SubA");
       subCategoryIdsA.add("cat001_SubB");

       categoryMappingA.setSubCategoryIds(subCategoryIdsA);
       categoryMappings.add(categoryMappingA);

       // -------- Category B --------
       CategoryMapping categoryMappingB = new CategoryMapping();
       categoryMappingB.setCategoryId("cat002");

       List<String> subCategoryIdsB = new ArrayList<>();
       subCategoryIdsB.add("cat002_SubA");
       subCategoryIdsB.add("cat002_SubB");

       categoryMappingB.setSubCategoryIds(subCategoryIdsB);
       categoryMappings.add(categoryMappingB);

       // -------- Category C --------
       CategoryMapping categoryMappingC = new CategoryMapping();
       categoryMappingC.setCategoryId("cat003");

       List<String> subCategoryIdsC = new ArrayList<>();
       subCategoryIdsC.add("cat003_SubA");
       subCategoryIdsC.add("cat003_SubB");

       categoryMappingC.setSubCategoryIds(subCategoryIdsC);
       categoryMappings.add(categoryMappingC);


       blog.setCategoryMappings(categoryMappings);

       for (CategoryMapping cm : categoryMappings){
           System.out.println("Category ID: " + cm.getCategoryId());
           for(String subCatId : cm.getSubCategoryIds()){
               System.out.println(" - SubCategory ID: " +subCatId);
           }
       }

       /// ... set


   }
}
