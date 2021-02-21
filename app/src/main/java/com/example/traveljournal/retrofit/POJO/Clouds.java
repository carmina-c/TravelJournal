package com.example.traveljournal.retrofit.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    private int all;

    public Clouds(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}

