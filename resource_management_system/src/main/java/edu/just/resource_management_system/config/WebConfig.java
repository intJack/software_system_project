//package edu.just.resource_management_system.config;
//
//import edu.just.resource_management_system.interceptor.LoginInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 注册 拦截器 实现对于token失效或者缺少token 的拦截功能
// * 只有在用户点击下载资源的时候 才会触发拦截功能
// */
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册拦截器
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("#");
//    }
//}
