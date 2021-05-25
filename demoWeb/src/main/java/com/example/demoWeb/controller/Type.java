package com.example.demoWeb.controller;

import com.example.demoWeb.dao.TypeDao;
import com.example.demoWeb.domain.TypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Zhao Shuqing
 * @Date: 2021/5/18 16:53
 * @Description:
 */
@Controller
@RequestMapping("/type")
public class Type {

    @Autowired
    TypeDao typeDao;

    @ResponseBody
    @RequestMapping("/getAllParentType")
    public List<String> getAllParentType () {
        return typeDao.getAllParentType();
    }

    @ResponseBody
    @RequestMapping("/getChildType")
    public List<TypeBean> getChildType (int par_type_id) {
        return typeDao.getChildType(par_type_id);
    }
}
