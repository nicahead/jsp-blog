package com.nic.dao;

import com.nic.model.User;
import com.nic.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    public boolean VerifyUser(String username,String password){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection(); // 获取数据库链接
        StringBuilder sql = new StringBuilder("select * from user where 1=1");
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(username);
        sql.append(" and username = ?");
        paramList.add(password);
        sql.append(" and password = ?");
        List<Map<String, Object>> result = null;
        try {
            result = jdbcUtil.findResult(sql.toString(),paramList);
            if (result.size()!=0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
