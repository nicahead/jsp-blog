package com.nic.model;

import java.util.Map;

public class Category {
    private int main_id;
    private String main_name;
    private int sub_id;
    private  String sub_name;
    private Number sub_count;

    public Category() {
    }

    public Category(int main_id, String main_name, int sub_id, String sub_name, Number sub_count) {
        this.main_id = main_id;
        this.main_name = main_name;
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.sub_count = sub_count;
    }
    public Category(Map<String, Object> map) {
        this.main_id = (int) map.get("id");
        this.main_name = (String) map.get("name");
        if (map.get("sub_id")!=null)
            this.sub_id = (int) map.get("sub_id");
        if (map.get("sub_name")!=null)
            this.sub_name = (String) map.get("sub_name");
        this.sub_count =  (Number)map.get("sub_count");
    }

    public int getMain_id() {
        return main_id;
    }

    public void setMain_id(int main_id) {
        this.main_id = main_id;
    }

    public String getMain_name() {
        return main_name;
    }

    public void setMain_name(String main_name) {
        this.main_name = main_name;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public Number getSub_count() {
        return sub_count;
    }

    public void setSub_count(Number sub_count) {
        this.sub_count = sub_count;
    }
}
