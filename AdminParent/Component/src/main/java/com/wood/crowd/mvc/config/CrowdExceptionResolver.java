package com.wood.crowd.mvc.config;

import com.google.gson.Gson;
import com.wood.crowd.constant.Constants;
import com.wood.crowd.exception.AccessForbiddenException;
import com.wood.crowd.exception.LoginFailedException;
import com.wood.crowd.util.CommonUtils;
import com.wood.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CrowdExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CrowdExceptionResolver.class);

    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "admin-login";
        return commonResolve(viewName, exception, request, response);
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "admin-login";
        return commonResolve(viewName, exception, request, response);
    }

    private ModelAndView commonResolve(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) {
        boolean isAjax = CommonUtils.isAjaxRequest(request);
        if (isAjax) {
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(resultEntity);
            try {
                response.getWriter().write(json);
            } catch (IOException e) {
                logger.error(String.format("response write %s error", json));
            }
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(Constants.ATTR_NAME_EXCEPTION, exception);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
