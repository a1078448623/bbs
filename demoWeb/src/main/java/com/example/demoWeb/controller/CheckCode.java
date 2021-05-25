package com.example.demoWeb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@ResponseBody
@RequestMapping("/checkcode")
public class CheckCode {
    @RequestMapping("/gen")
    public void genCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = 100;
        int height = 50;

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width - 1,height - 1);
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        String code="";
        g.setFont(new Font("Tacoma", Font.BOLD, 22));
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            code+=ch;
            g.drawString(ch+"",width/5*i-5,height/2+5);
        }
        request.getSession().setAttribute("checkcode",code);
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
}

