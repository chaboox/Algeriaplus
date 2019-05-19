package com.chaboox.algeriaplus.model;

public class JsonCategoriesModel {
    private String categoriesDescription;
    private int categoriesId;
    private String categoriesName;

    public int getCategoriesId() {
        return this.categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return this.categoriesName;
    }

    public void setCategoriesName(String categoriedName) {
        this.categoriesName = categoriedName;
    }

    public String getCategoriesDescription() {
        return this.categoriesDescription;
    }

    public void setCategoriesDescription(String categoriesDescription) {
        this.categoriesDescription = categoriesDescription;
    }
}
