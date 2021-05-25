package com.example.demoWeb.domain;

/**
 * @Author: Zhao Shuqing
 * @Date: 2021/5/17 20:15
 * @Description:
 */
public class ApplyBean {
    private int apply_type;
    private int apply_user_id;
    private String apply_content;

    public int getApply_type() {
        return apply_type;
    }

    public void setApply_type(int apply_type) {
        this.apply_type = apply_type;
    }

    public int getApply_user_id() {
        return apply_user_id;
    }

    public void setApply_user_id(int apply_user_id) {
        this.apply_user_id = apply_user_id;
    }

    public String getApply_content() {
        return apply_content;
    }

    public void setApply_content(String apply_content) {
        this.apply_content = apply_content;
    }
}
