package com.nic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubCategory {
    private int id;
    private String name;
    private int main_id;
    private Number sub_count;

    public SubCategory() {}

    public SubCategory(Map<String, Object> map){
        this.id = (int)map.get("id");
        this.name = (String)map.get("name");
        this.main_id = (int)map.get("main_id");
        this.sub_count = (Number)map.get("sub_count");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMain_id() {
        return main_id;
    }

    public void setMain_id(int main_id) {
        this.main_id = main_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Number getSub_count() {
        return sub_count;
    }

    public void setSub_count(int sub_count) {
        this.sub_count = sub_count;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", main_id=" + main_id +
                ", sub_count=" + sub_count +
                '}';
    }

    public List toList() {
        List list = new ArrayList();
        list.add(name);
        list.add(main_id);
        return list;
    }
}
