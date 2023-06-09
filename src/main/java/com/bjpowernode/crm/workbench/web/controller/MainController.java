package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.web.controller
 * @Description: TODO
 * @Date 2023-04-11 23:14
 */
@Controller
public class MainController {


    @RequestMapping("/workbench/main/index.do")
    public String index(){
        // 跳转main/index.jsp
        return "workbench/main/index";
    }
}
