package model;

/**
 * Created by Pradeep on 12/8/2016.
 */
public class ExerciseDetails {
    String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSub_categoryID() {
        return sub_categoryID;
    }

    public void setSub_categoryID(String sub_categoryID) {
        this.sub_categoryID = sub_categoryID;
    }

    public String getItem_Details() {
        return item_Details;
    }

    public void setItem_Details(String item_Details) {
        this.item_Details = item_Details;
    }

    public ExerciseDetails(String itemId, String sub_categoryID, String item_Details) {
        this.itemId = itemId;
        this.sub_categoryID = sub_categoryID;
        this.item_Details = item_Details;
    }

    String sub_categoryID;
    String item_Details;
}
