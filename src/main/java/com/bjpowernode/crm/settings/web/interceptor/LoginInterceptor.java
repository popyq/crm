package com.bjpowernode.crm.settings.web.interceptor;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.settings.web.interceptor
 * @Description: TODO
 * @Date 2023-04-11 21:21
 */

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果用户没有登录成功,则跳转到登录页面
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        if (user == null){
            System.out.println(request.getContextPath());
            response.sendRedirect("/"); // 重定向时,url必须加项目的名称  这个方法获取的就是斜杠加项目名
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
