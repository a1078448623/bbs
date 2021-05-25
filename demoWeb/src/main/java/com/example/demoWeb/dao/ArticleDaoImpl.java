package com.example.demoWeb.dao;

import com.example.demoWeb.domain.ArticleBean;
import com.example.demoWeb.domain.PublishArticleBean;
import com.example.demoWeb.domain.RecommendBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ArticleBean> findAllArts(int type) {
        try {
            String sql = "select * from article where type_id = ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleBean>(ArticleBean.class), type);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public String findAuthor(int art_id) {
        String sql="select username from user where u_id=?";
        return jdbcTemplate.queryForObject(sql,String.class,art_id);

    }
    public ArticleBean findSingelArt(int art_id){
        String sql="select * from article where art_id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<ArticleBean>(ArticleBean.class),art_id);
    }

    @Override
    public String findNickName(int u_id) {
        String sql="select nickname from user where u_id=?";

        return jdbcTemplate.queryForObject(sql,String.class,u_id);
    }

    @Override
    public String getType(int type_id) {
        String sql="select t_name from art_type where t_id=?";
        return jdbcTemplate.queryForObject(sql,String.class,type_id);
    }

    @Override
    public boolean addArticle(PublishArticleBean pab) {
        String sql="insert into article  values (null,?,?,?,?,default,default,default);";
        int i = jdbcTemplate.update(sql, Integer.parseInt(pab.getAu_id()),
                Integer.parseInt(pab.getType_id()), pab.getTitle(), pab.getContent());
        return i>0;
    }

    @Override
    public List<RecommendBean> getMyArts(int u_id) {

        try{
            String sql = "select art_id,nickname,t_name,title,likes,comments,cre_time " +
                    "from article,user,art_type " +
                    "where auth_id=? and u_id=article.auth_id and type_id=art_type.t_id" +
                    " order by cre_time desc ";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RecommendBean>(RecommendBean.class), u_id);
        }catch (DataAccessException e){
            return null;
        }

    }

    @Override
    public List<ArticleBean> findAllArtsAndOrder(int type, int orderBy) {
        try {
            String sql = "select * from article where type_id = ? order by ?";
            if (orderBy == 1) return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleBean>(ArticleBean.class), type, "comments");
            else if (orderBy == 2) return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleBean>(ArticleBean.class), type, "likes");
            else return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ArticleBean>(ArticleBean.class), type, "cre_time");
        } catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public Boolean deleteArt(int u_id, int art_id) {
        try {
            String sql1 = "select auth_id from article where art_id = ?";
            int auth_id = jdbcTemplate.queryForObject(sql1, Integer.class, art_id);
            if (u_id == auth_id) {
                String sql2 = "delete from article where art_id = ?";
                return (1 == jdbcTemplate.update(sql2,art_id));
            } else {
                return false;
            }
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean changeArticle(PublishArticleBean pab, String cur_art_id, int u_id) {
        String sql1 = "select auth_id from article where art_id = ?";
        int auth_id = jdbcTemplate.queryForObject(sql1, Integer.class, cur_art_id);
        if (u_id == auth_id) {
            String sql = "update article  set title = ?, content = ? where art_id = ?";
            return jdbcTemplate.update(sql, pab.getTitle(), pab.getContent(), Integer.parseInt(cur_art_id)) > 0;
        } else {
            return false;
        }
    }

}
