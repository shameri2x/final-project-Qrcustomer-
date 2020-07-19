package com.example.qrcustomer;

public class Stores {
    public int img;
    public String name;
    public String describe;
    public double evu;

    public Stores(int img, String name, String describe, double evu) {
        this.img = img;
        this.name = name;
        this.describe = describe;
        this.evu = evu;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getEvu() {
        return evu;
    }

    public void setEvu(int evu) {
        this.evu = evu;
    }
}
