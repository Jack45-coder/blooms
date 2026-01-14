package in.codingage.blooms.dto;

import in.codingage.blooms.models.CategoryMapping;

import java.util.List;

public class BlogRequest {
    private String title;
    private String description;
    private String content;
    private List<CategoryMapping> categoryMappings;

    public BlogRequest(String title, String description, String content, List<CategoryMapping> categoryMappings) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.categoryMappings = categoryMappings;
    }

    public BlogRequest(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CategoryMapping> getCategoryMappings() {
        return categoryMappings;
    }

    public void setCategoryMappings(List<CategoryMapping> categoryMappings) {
        this.categoryMappings = categoryMappings;
    }
}
