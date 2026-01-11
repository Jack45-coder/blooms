package in.codingage.blooms;

import in.codingage.blooms.models.Admin;
import in.codingage.blooms.models.Category;
import in.codingage.blooms.models.SubCategory;
import in.codingage.blooms.models.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    // singleton design pattern.

    private static Database instance = null;

    // Collection of Category
    private List<Category> categoryList = new ArrayList<>();

    // Collection of SubCategory
    private  List<SubCategory> subCategoryList = new ArrayList<>();

    // Collection of User
    private List<User> userList = new ArrayList<>();

    // Collection of Admin.
    private List<Admin> adminList = new ArrayList<>();

    private Database(){
    }
    
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public List<Category> getCategoryList(){
        return categoryList;
    }

    public List<SubCategory> getSubCategoryList(){
        return subCategoryList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }
}
