package com.example.demoWeb.domain;

import java.util.Date;

/**
 * @Author: Zhao Shuqing
 * @Date: 2021/5/17 20:55
 * @Description:
 */
public class ReApplyBean {
    private int apply_type;
    private Date apply_time;
    private String apply_content;
    private int result;

    public String getFormate_time() {
        return formate_time;
    }

    public void setFormate_time(String formate_time) {
        this.formate_time = formate_time;
    }

    private String formate_time;

    public int getApply_type() {
        return apply_type;
    }

    public void setApply_type(int apply_type) {
        this.apply_type = apply_type;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public String getApply_content() {
        return apply_content;
    }

    public void setApply_content(String apply_content) {
        this.apply_content = apply_content;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
