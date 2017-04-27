package model;

/**
 * Created by Pradeep on 12/8/2016.
 */
public class SubCategory_Item {

    public String getSub_categoryID() {
        return sub_categoryID;
    }

    public void setSub_categoryID(String sub_categoryID) {
        this.sub_categoryID = sub_categoryID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    String sub_categoryID;

    public SubCategory_Item(String sub_categoryID, String categoryID, String imagePath,String category_name) {
        this.sub_categoryID = sub_categoryID;
        this.categoryID = categoryID;
        this.imagePath = imagePath;
        this.category_name = category_name;
    }

    String categoryID;
    String imagePath;

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    String category_name;

}
