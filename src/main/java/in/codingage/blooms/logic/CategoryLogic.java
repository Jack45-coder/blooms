package in.codingage.blooms.logic;

import in.codingage.blooms.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryLogic {

    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories(){
        createCategory();
        return categories;
    }




    // create category
    public void createCategory(){
        Category category = new Category();
        category.setName("Technology");
        category.setId("Cat001");
        categories.add(category);

        Category categoryB = new Category();
        categoryB.setName("Health");
        categoryB.setId("Cat002");
        categories.add(categoryB);

        Category categoryC = new Category();
        categoryC.setName("Politics");
        categoryC.setId("Cat003");
        categories.add(categoryC);


    }
}
