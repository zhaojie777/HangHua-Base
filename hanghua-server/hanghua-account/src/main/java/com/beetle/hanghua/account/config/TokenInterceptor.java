package com.beetle.hanghua.account.config;

import com.beetle.hanghua.account.dto.ResultDTO;
import com.beetle.hanghua.common.jwt.JwtTokenUtil;
import com.beetle.hanghua.account.util.ResultEnum;
import com.beetle.hanghua.common.util.JsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        if (!"".equals(token) && token != null) {
            JwtTokenUtil.verifierTokenByRS256(token);
        } else {
            falseResult(response, new ResultDTO<>(ResultEnum.ERR_NOT_TOKEN));
            return false;
        }

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

    /**
     * 被拦截后，响应给客户端的错误信息
     * @param response
     */
    private void falseResult(HttpServletResponse response, ResultDTO resultDTO) throws IOException {
        // 解决汉字序列化时乱码问题
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(JsonUtil.toJsonString(resultDTO));
    }
}
