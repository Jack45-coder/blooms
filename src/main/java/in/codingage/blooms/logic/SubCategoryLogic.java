package in.codingage.blooms.logic;

import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryLogic {

    private List<SubCategory> subCategories = new ArrayList<>();

    public List<SubCategory> getSubCategories(){
        createSubCategory();
        return subCategories;
    }

    public void createSubCategory(){
        List<Category> categories = new CategoryLogic().getCategories();

        for(Category category : categories){
            SubCategory subCategoryA = new SubCategory();
            subCategoryA.setId(category.getId() + "_subA");
            subCategoryA.setCategoryId(category.getId());
            subCategoryA.setName("Subcategory A of: " + category.getName());
            subCategories.add(subCategoryA);

            SubCategory subCategoryB = new SubCategory();
            subCategoryA.setId(category.getId() + "_subB");
            subCategoryB.setCategoryId(category.getId());
            subCategoryB.setName("Subcategory B of: " + category.getName());
            subCategories.add(subCategoryB);

            SubCategory subCategoryC = new SubCategory();
            subCategoryA.setId(category.getId() + "_subC");
            subCategoryC.setCategoryId(category.getId());
            subCategoryC.setName("Subcategory C of: " + category.getName());
            subCategories.add(subCategoryC);
        }
    }
}
