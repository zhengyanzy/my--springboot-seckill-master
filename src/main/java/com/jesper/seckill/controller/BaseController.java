package com.jesper.seckill.controller;

import com.jesper.seckill.bean.Goods;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    @ModelAttribute//如果没有此方法，当访问/user的时候，获取的user是一个空对象,而我们可以通过@ModelAttribute注解拦截请求，通过该Model给request请求域中额外添加属性
    public void userinit(HttpServletResponse response, HttpServletRequest request, Model model){
        String a = request.getParameter("token");
        Cookie[] cookie = request.getCookies();
        for (Cookie c:cookie){
            if ("token".equals(c.getName())){
                System.out.println(c.getValue());
            }
        }
        Goods g = new Goods();
        g.setId(1L);
        model.addAttribute("goods",g);
    }
}
