package com.nic.dao;

import com.nic.util.JdbcUtil;
import com.nic.model.Article;
import com.nic.model.Pager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {
    /**
     * 根据查询条件，查询文章分页信息
     * @param searchModel 封装查询条件
     * @param pageNum 查询第几页数据
     * @param pageSize 每页显示多少条记录
     * @return 查询结果
     */
    public Pager<Article> findArticle(Article searchModel, int pageNum, int pageSize) {
        Pager<Article> result = null;
        // 存放查询参数
        List<Object> paramList = new ArrayList<Object>();
        int id = searchModel.getId();
        int sub_id = searchModel.getSub_id();
        int main_id = searchModel.getMain_id();
        StringBuilder sql = new StringBuilder("select a.*,m.name mname,s.name sname from " +
                    "article a left join maincategory m on a.main_id=m.id left join subcategory s on a.sub_id = s.id where 1=1 ");
        StringBuilder countSql = new StringBuilder("select count(id) as totalRecord from article where 1=1 ");
        if (id != 0 ) {
            sql.append(" and a.id = ?");
            countSql.append(" and id = ?");
            paramList.add(id);
        }
        if (sub_id != 0 ) {
            sql.append(" and a.sub_id = ? ");
            countSql.append(" and sub_id = ? ");
            paramList.add(sub_id);
        }
        if (main_id != 0 ) {
            sql.append(" and a.main_id= ? ");
            countSql.append(" and main_id = ? ");
            paramList.add(main_id);
        }
        sql.append(" ORDER BY top desc,createdate desc ");
        countSql.append(" ORDER BY top desc,createdate desc ");
        // 起始索引
        int fromIndex = pageSize * (pageNum - 1);
        // 使用limit关键字，实现分页
        sql.append(" limit " + fromIndex + ", " + pageSize);
        // 存放所有查询出的文章对象
        List<Article> studentList = new ArrayList<Article>();
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库链接
            // 获取总记录数
            List<Map<String, Object>> countResult = jdbcUtil.findResult(countSql.toString(), paramList);
            Map<String, Object> countMap = countResult.get(0);
            int totalRecord = ((Number) countMap.get("totalRecord")).intValue();
            // 获取查询的文章记录
            List<Map<String, Object>> studentResult = jdbcUtil.findResult(sql.toString(), paramList);
            if (studentResult != null) {
                for (Map<String, Object> map : studentResult) {
                    Article s = new Article(map);
                    studentList.add(s);
                }
            }
            //获取总页数
            int totalPage = totalRecord / pageSize;
            if (totalRecord % pageSize != 0) {
                totalPage++;
            }
            // 组装pager对象
            result = new Pager<Article>(pageSize, pageNum,totalRecord, totalPage, studentList);

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
     * 添加新文章
     * @param article 文章对象
     * @return 插入结果
     */
    public boolean addArticle(Article article) {
        boolean result = false;
        StringBuilder sql;
        if (article.getSub_id()!=0){
            sql =new StringBuilder("insert into article(title,subtitle,md_content,html_content," +
                            "createdate,sub_id,main_id,top) values(?,?,?,?,?,?,?,?);");
        }else{
            sql =new StringBuilder("insert into article(title,subtitle,md_content,html_content," +
                            "createdate,main_id,top) values(?,?,?,?,?,?,?);");
        }
        JdbcUtil jdbcUtil = null;
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),article.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 修改文章
     * @param article 文章对象
     * @param article 文章
     * @return 更新结果
     */
    public boolean updateArticle(Article article,int id) {
        boolean result = false;
        StringBuilder sql;
        if (article.getSub_id()!=0){
            sql = new StringBuilder("update article set title = ?,subtitle = ?,md_content = ?,html_content = ?," +
                            "createdate = ?,sub_id = ?,main_id = ?,top = ?  where id = ? ");
        }
        else {
            sql = new StringBuilder("update article set title = ?,subtitle = ?,md_content = ?,html_content = ?," +
                    "createdate = ?,main_id = ?,top = ?  where id = ? ");
        }
        JdbcUtil jdbcUtil = null;
        ArrayList paramList = (ArrayList) article.toList();
        paramList.add(id);
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除文章
     * @param id 文章id
     * @return 删除结果
     */
    public boolean deleteArticle(int id) {
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("delete from article where id = ?;");
        JdbcUtil jdbcUtil = null;
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(id);
        try {
            jdbcUtil = new JdbcUtil();
            jdbcUtil.getConnection(); // 获取数据库连接
            result = jdbcUtil.updateByPreparedStatement(sql.toString(),paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
