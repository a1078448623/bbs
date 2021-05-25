package com.example.demoWeb.dao;

import com.example.demoWeb.domain.TypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Zhao Shuqing
 * @Date: 2021/5/18 17:01
 * @Description:
 */
@Repository
public class TypeDaoImpl implements TypeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getAllParentType() {
        String sql="select par_type_name from parentType;";
        return jdbcTemplate.queryForList(sql,String.class);
    }

    @Override
    public List<TypeBean> getChildType(int par_type_id) {
        String sql="select t_id, t_name from art_type where father_id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<TypeBean>(TypeBean.class),par_type_id);
    }
}
