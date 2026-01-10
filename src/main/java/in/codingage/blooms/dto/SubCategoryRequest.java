package in.codingage.blooms.dto;

public class SubCategoryRequest {
    private String title;
    private String desc;
    private String categoryId;

    public SubCategoryRequest(String title, String desc, String categoryId) {
        this.title = title;
        this.desc = desc;
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
