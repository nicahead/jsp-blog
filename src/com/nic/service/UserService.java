package com.nic.service;

import com.nic.dao.UserDao;

public class UserService {
    private UserDao userDao;
    public UserService(){
        userDao = new UserDao();
    }
    public boolean VerifyUser(String username,String password){
        boolean result=userDao.VerifyUser(username,password);
        return result;
    }
}
