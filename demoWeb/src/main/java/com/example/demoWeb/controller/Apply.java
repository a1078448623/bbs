package com.example.demoWeb.controller;

import com.example.demoWeb.dao.ApplyDao;
import com.example.demoWeb.domain.ApplyBean;
import com.example.demoWeb.domain.ReApplyBean;
import com.example.demoWeb.domain.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: Zhao Shuqing
 * @Date: 2021/5/17 20:36
 * @Description:
 */
@Controller
@RequestMapping("/apply")
public class Apply {
    @Autowired
    private ApplyDao applyDao;

    @ResponseBody
    @RequestMapping("/sendApply")
    public Boolean sendApply (ApplyBean applyBean) {
        return applyDao.sendApply(applyBean);
    }

    @ResponseBody
    @RequestMapping("/myApply")
    public List<ReApplyBean> myApply (HttpSession session) {
        int user_id = ((UserBean)session.getAttribute("user")).getU_id();
        List<ReApplyBean> myApply = applyDao.getMyApply(user_id);
        if(myApply!=null){
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            for(ReApplyBean rb:myApply){
                rb.setFormate_time(sdf.format(rb.getApply_time()));
            }
            return myApply;
        }
        return null;
    }
}
