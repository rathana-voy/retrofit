package com.rathana.retrofit_demo.data.entiry;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("ID")
    private int id;
    @SerializedName("NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
