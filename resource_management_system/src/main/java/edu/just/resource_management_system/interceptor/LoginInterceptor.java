package edu.just.resource_management_system.interceptor;

import edu.just.resource_management_system.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;



public class LoginInterceptor implements HandlerInterceptor {
    //判断令牌是否有效
    //有效放行 无效拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        boolean flag = TokenUtil.validateToken(token);
        //如果令牌失效 重定向到首页 进行登录
        if(!flag){
            response.sendRedirect("home");
            return false;
        }else{
            return true;
        }
    }
}
