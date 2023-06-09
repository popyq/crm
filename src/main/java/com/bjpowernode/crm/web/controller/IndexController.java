package com.bjpowernode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.web.controller
 * @Description: TODO
 * @Date 2023-04-03 19:38
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        // 请求转发
        return "index";
    }
}
