package model;

/**
 * Created by Pradeep on 12/8/2016.
 */
public class ImageDetails {
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

    public String getimage_Details() {
        return image_Details;
    }

    public void setimage_Details(String image_Details) {
        this.image_Details = image_Details;
    }

    public ImageDetails(String itemId, String sub_categoryID, String image_Details) {
        this.itemId = itemId;
        this.sub_categoryID = sub_categoryID;
        this.image_Details = image_Details;
    }

    String sub_categoryID;
    String image_Details;
}
