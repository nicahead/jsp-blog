package com.nic.dao;

import com.nic.model.Category;
import com.nic.model.MainCategory;
import com.nic.model.SubCategory;
import com.nic.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryDao {
    /**
     * 获取所有分类的信息
     * @return 分类查询结果的列表
     */
    public List<Category> getCategory(Category searchModel){
        List<Category> result = new ArrayList<Category>();
        // 存放查询参数
        List<Object> paramList = new ArrayList<Object>();
        int main_id = searchModel.getMain_id();
        int sub_id = searchModel.getSub_id();
        StringBuilder sql = new StringBuilder("select m.*,s.id sub_id,s.name sub_name," +
                "(select count(id) from article where main_id = s.main_id and sub_id = s.id) sub_count " +
                "from maincategory m,subcategory s where m.id = s.main_id");
        if (main_id != 0 ) {
            sql.append(" and s.main_id = ?");
            paramList.add(main_id);
        }
        if (sub_id != 0 ) {
            sql.append(" and s.sub_id = ?");
            paramList.add(sub_id);
        }
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库链接
            List<Map<String, Object>> categoryResult = jdbcUtil.findResult(sql.toString(), paramList);
            if (categoryResult != null) {
                for (Map<String, Object> map : categoryResult) {
                    Category s = new Category(map);
                    result.add(s);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据异常！", e);
        } finally {
            if (jdbcUtil != null) {
                jdbcUtil.releaseConn(); // 释放资源
            }
        }
        return result;
    }
    /**
     * 获取所有主分类的列表
     * @return 主分类查询结果的列表
     */
    public List<MainCategory> getMaincategory(){
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection(); // 获取数据库链接
        StringBuilder sql = new StringBuilder("select *,(select count(*) from article where main_id = m.id) main_count " +
                "from maincategory m;");
        List<Map<String, Object>> CatetoryList = null;
        List<MainCategory> result = new ArrayList<MainCategory>();
        List<SubCategory> sublist = null;
        try {
            CatetoryList = jdbcUtil.findResult(sql.toString(),new ArrayList<>());
            if (CatetoryList!=null){
                for (Map<String, Object> map : CatetoryList) {
                    MainCategory catetory = new MainCategory(map);
                    sublist = getSubcategory(Integer.parseInt(map.get("id").toString()));
                    catetory.setSublist(sublist);
                    result.add(catetory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获取选中主分类对应的二级分类
     * @return 二级分类查询结果的列表
     */
    public List<SubCategory> getSubcategory(int main_id){
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection(); // 获取数据库链接
        StringBuilder sql = new StringBuilder("select *,(select count(*) from article where sub_id = s.id) sub_count" +
                " from subcategory s where 1=1 ");
        List<Object> paramList = new ArrayList<Object>();
        List<Map<String, Object>> subCategoryList = null;
        List<SubCategory> result = new ArrayList<SubCategory>();
        if (main_id != 0 ) {
            sql.append(" and main_id = ?");
            paramList.add(main_id);
        }
        try {
            subCategoryList = jdbcUtil.findResult(sql.toString(),paramList);
            if (subCategoryList!=null){
                for (Map<String, Object> map : subCategoryList) {
                      result.add(new SubCategory(map));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 添加主分类*/
    public boolean addMainCategory(MainCategory mainCategory){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("insert into maincategory(name) values(?);");
        JdbcUtil jdbcUtil = null;
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(mainCategory.getName());
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 添加二级分类*/
    public boolean addSubCategory(SubCategory subCategory){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("insert into subcategory(name,main_id) values(?,?);");
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),subCategory.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 修改主分类*/
    public boolean updateMainCategory(MainCategory mainCategory){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("update maincategory set name = ? where id = ? ");
        List<Object> paramlist = new ArrayList<Object>();
        paramlist.add(mainCategory.getName());
        paramlist.add(mainCategory.getId());
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 修改二级分类*/
    public boolean updateSubCategory(SubCategory subCategory){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("update subcategory set name = ?,main_id = ? where id = ? ");
        List paramlist = new ArrayList();
        paramlist.add(subCategory.getName());
        paramlist.add(subCategory.getMain_id());
        paramlist.add(subCategory.getId());
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除主分类*/
    public boolean deleteMainCategory(int id){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("delete from maincategory where id = ?;");
        List<Object> paramlist = new ArrayList<Object>();
        paramlist.add(id);
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除二级分类*/
    public boolean deleteSubCategory(int id){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("delete from subcategory where id = ?;");
        List paramlist = new ArrayList();
        paramlist.add(id);
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
