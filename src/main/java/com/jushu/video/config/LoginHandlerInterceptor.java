package com.jushu.video.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 大奉
 * @date 2020/1/8 11:34
 * @blame 大奉
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        logger.info("afterCompletion---" + user + "---" + request.getRequestURI());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        logger.info("postHandle---" + user + "---" + request.getRequestURI());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        logger.info("preHandle---" + user + "---" + request.getRequestURI());
        if(user == null) {
            request.setAttribute("msg", "无权限，请先登录!");
            //获取request返回页面到登录页
            response.sendRedirect(request.getContextPath() + "");
            return false;
        }
        return true;
    }
}
