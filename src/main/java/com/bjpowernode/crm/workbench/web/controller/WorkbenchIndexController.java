package com.bjpowernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.workbench.web.controller
 * @Description: TODO
 * @Date 2023-04-04 0:32
 */
@Controller
public class WorkbenchIndexController {


    @RequestMapping("/workbench/index.do")
    public String index(){
        // 跳转到业务主页面
        return "workbench/index";
    }
}
