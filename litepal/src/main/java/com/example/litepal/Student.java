package com.example.litepal;

import org.litepal.crud.DataSupport;

/**
 * Created by mac_soong on 2017/2/23.
 */
public class Student extends DataSupport {
    private int id;
    private String name;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
