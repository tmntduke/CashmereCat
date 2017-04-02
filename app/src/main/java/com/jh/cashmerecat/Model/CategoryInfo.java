package com.jh.cashmerecat.Model;

/**
 * 分类实体
 * Created by tmnt on 2016/12/1.
 */
public class CategoryInfo {

    private String categoryID;
    private String category_name;
    private String category_image;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }
}
