package com.wood.crowd.mvc.interceptor;

import com.wood.crowd.constant.Constants;
import com.wood.crowd.entity.Admin;
import com.wood.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(Constants.ATTR_NAME_LOGIN_ADMIN);
        if (admin == null) {
            throw new AccessForbiddenException(Constants.MESSAGE_ACCESS_FORBIDEN);
        }
        return true;
    }
}
