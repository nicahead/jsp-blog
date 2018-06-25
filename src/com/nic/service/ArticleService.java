package com.nic.service;

import com.nic.dao.ArticleDao;
import com.nic.model.Article;
import com.nic.model.Pager;
import com.nic.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleService {
    private ArticleDao articleDao;
    public ArticleService(){
        articleDao = new ArticleDao();
    }
    /**
     * 根据查询条件，查询文章分页信息
     */
    public Pager<Article> findArticle(Article searchModel, int pageNum,int pageSize) {
        return articleDao.findArticle(searchModel, pageNum,pageSize);
    }
    /**
     * 插入文章
     */
    public boolean addArticle(Article article) {
        return articleDao.addArticle(article);
    }
    /**
     * 修改文章
     */
    public boolean updateArticle(Article article,int id) {
        return articleDao.updateArticle(article,id);
    }
    /**
     * 删除文章
     * @param id 文章id
     * @return 删除结果
     */
    public boolean deleteArticle(int id) {
        return articleDao.deleteArticle(id);
    }
}
