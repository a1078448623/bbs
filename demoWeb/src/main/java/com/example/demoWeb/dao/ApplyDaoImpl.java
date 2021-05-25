package com.example.demoWeb.dao;

import com.example.demoWeb.domain.ApplyBean;
import com.example.demoWeb.domain.ReApplyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Zhao Shuqing
 * @Date: 2021/5/17 20:29
 * @Description:
 */
@Repository
public class ApplyDaoImpl implements ApplyDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean sendApply(ApplyBean applyBean) {
        String sql="insert into apply  values (null,?,?,?,default,default);";  //apply 数据库表定义为 编号（自增）， 申请类型（1为新分区，2为申请版主,3为其他，例如举报反馈等等）， 申请者id， 申请内容描述， 申请时间（默认now）， 处理状态（0为待处理。1为统一，2为拒绝）
        return jdbcTemplate.update(sql, applyBean.getApply_type(), applyBean.getApply_user_id(), applyBean.getApply_content())>0;
    }

    @Override
    public List<ReApplyBean> getMyApply(int user_id) {
        try{
            String sql = "select apply_type,apply_time,apply_content,apply_result" +
                    "from apply" +
                    "where apply_user_id = ?" +
                    " order by apply_time desc ";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReApplyBean>(ReApplyBean.class), user_id);
        }catch (DataAccessException e){
            return null;
        }
    }
}
