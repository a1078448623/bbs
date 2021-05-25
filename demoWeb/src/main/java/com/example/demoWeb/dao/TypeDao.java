package com.example.demoWeb.dao;

import com.example.demoWeb.domain.TypeBean;

import java.util.List;

public interface TypeDao {
    public List<String> getAllParentType();
    public List<TypeBean> getChildType(int par_type_id);
}
