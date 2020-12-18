package com.jesper.seckill.controller;

import com.jesper.seckill.bean.Goods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController extends BaseController{


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String toLogin(Goods goods) {
        return "login";
    }
}
