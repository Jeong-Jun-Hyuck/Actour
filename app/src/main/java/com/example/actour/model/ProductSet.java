package com.example.actour.model;

import java.util.ArrayList;

public class ProductSet {

    private ProductModel productModel;
    private ArrayList<ProductCourse> productCourses;

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public ArrayList<ProductCourse> getProductCourses() {
        return productCourses;
    }

    public void setProductCourses(ArrayList<ProductCourse> productCourses) {
        this.productCourses = productCourses;
    }

    public ProductSet(ProductModel productModel, ArrayList<ProductCourse> productCourses) {
        this.productModel = productModel;
        this.productCourses = productCourses;
    }
}
