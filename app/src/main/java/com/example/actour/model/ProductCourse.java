package com.example.actour.model;


public class ProductCourse {

    private String courseName;
    private double courseTime;
    private int coursePrice;
    private String caution;
    private String details;
    private int index;
    private int productId;

    public ProductCourse(String courseName, double courseTime, int coursePrice, String caution, String details,
                         int index, int productId) {
        super();
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.coursePrice = coursePrice;
        this.caution = caution;
        this.details = details;
        this.index = index;
        this.productId = productId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(double courseTime) {
        this.courseTime = courseTime;
    }

    public int getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(int coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

