package com.beetle.hanghuasso.config;

import com.beetle.hanghuasso.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * token认证拦截器
 * @author zhaojie
 * @date 2021-05-02
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {


    /**
     * 预处理回调，true为放行，false为拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //请求地址过滤
        String uri = request.getRequestURI();

        //对登陆请求进行
        if (uri.contains("/login")) {
            return true;
        }

        if (uri.contains("/verifyToken")) {

            return false;
        }

        //验证Token的有效性
        String token = request.getParameter("token");
        JWTUtil.verifierToken(token);


        //通过token获取用户信息，并放入缓存



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
