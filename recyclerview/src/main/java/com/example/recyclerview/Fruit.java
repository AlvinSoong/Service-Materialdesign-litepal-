package com.example.recyclerview;

/**
 * Created by mac_soong on 2017/2/22.
 */
public class Fruit {

    private String name;
    private int ImageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.ImageId = imageId;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getName(){
        return name;
    }
}
