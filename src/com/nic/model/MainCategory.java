package com.nic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainCategory {
    private int id;
    private String name;
    private Number main_count;
    private List<SubCategory> sublist;

    public MainCategory() {}

    public MainCategory(Map<String, Object> map){
        this.id = (int)map.get("id");
        this.name = (String)map.get("name");
        this.main_count = (Number)map.get("main_count");
    }

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

    public Number getMain_count() {
        return main_count;
    }

    public void setMain_count(int main_count) {
        this.main_count = main_count;
    }

    public List<SubCategory> getSublist() {
        return sublist;
    }

    public void setSublist(List<SubCategory> sublist) {
        this.sublist = sublist;
    }

    @Override
    public String toString() {
        return "MainCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", main_count=" + main_count +
                ", sublist=" + sublist +
                '}';
    }
}
