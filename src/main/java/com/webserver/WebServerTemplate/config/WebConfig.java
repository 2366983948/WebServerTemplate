package com.webserver.WebServerTemplate.config;

import com.webserver.WebServerTemplate.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 解决跨域问题
     *
     * @param registry 注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
    /**
     * 添加静态资源
     *
     * @param registry 注册
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*静态资源的位置*/
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }

    /**
     * 添加拦截器 注入到容器中
     *
     * @param registry 注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建拦截器对象
        HandlerInterceptor interceptor = new JWTInterceptor();
        //指定拦截的地址
        String[] path = {"/TestToken","/user/update"};
        //指定不拦截的地址:对登录、首页、静态资源等进行放行
//        String[] excludePath = {
//                "/user/login", "/css/**", "/fonts/**", "/images/**", "/js/**", "/lib/**","/test/**","/test"
//        };
        registry.addInterceptor(interceptor)
                .addPathPatterns(path);
//                .excludePathPatterns(excludePath);
    }
}
