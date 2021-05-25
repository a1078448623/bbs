package com.example.demoWeb.dao;

import com.example.demoWeb.domain.ApplyBean;
import com.example.demoWeb.domain.ReApplyBean;

import java.util.List;

public interface ApplyDao {
    public Boolean sendApply(ApplyBean applyBean);
    List<ReApplyBean> getMyApply(int user_id);
}
