package com.nic.service;

import com.nic.dao.CategoryDao;
import com.nic.model.Category;
import com.nic.model.MainCategory;
import com.nic.model.SubCategory;

import java.util.List;

public class CategoryService {
    CategoryDao categoryDao;
    public CategoryService(){
        categoryDao = new CategoryDao();
    }
    /**
     * 获取所有分类的信息
     * @return 主分类查询结果的列表
     */
    public List<Category> getCategory(Category searchModel){
        return categoryDao.getCategory(searchModel);
    }
    /**
     * 获取所有主分类的键值对
     * @return 主分类查询结果的列表
     */
    public List<MainCategory> getMaincategory(){
        return categoryDao.getMaincategory();
    }
    /**
     * 获取选中主分类对应的二级分类的键值对
     * @return 二级分类查询结果的列表
     */
    public List<SubCategory> getSubcategory(int main_id){
        return categoryDao.getSubcategory(main_id);
    }
    /**
     * 添加主分类*/
    public boolean addMainCategory(MainCategory mainCategory){
        return categoryDao.addMainCategory(mainCategory);
    }
    /**
     * 添加二级分类*/
    public boolean addSubCategory(SubCategory subCategory){
        return categoryDao.addSubCategory(subCategory);
    }
    /**
     * 修改主分类*/
    public boolean updateMainCategory(MainCategory mainCategory) {
        return categoryDao.updateMainCategory(mainCategory);
    }
    /**
     * 修改二级分类*/
    public boolean updateSubCategory(SubCategory subCategory){
        return categoryDao.updateSubCategory(subCategory);
    }
    /**
     * 删除主分类*/
    public boolean deleteMainCategory(int id){
        System.out.println("service执行");
        return categoryDao.deleteMainCategory(id);
    }
    /**
     * 删除二级分类*/
    public boolean deleteSubCategory(int id){
        return categoryDao.deleteSubCategory(id);
    }
}
