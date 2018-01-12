package com.liu.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 73559 on 2017/12/28.
 */
@Configuration
public class WebSecurityConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "username";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/node/**");
        addInterceptor.excludePathPatterns("/userSubmit/**");
        addInterceptor.excludePathPatterns("/images/**");

        addInterceptor.excludePathPatterns("/**");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }


    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            //登陆不做拦截
            if(request.getRequestURI().equals("/login")){
                return true;
            }
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null){
                return true;
            }
            // 跳转登录
            String url = "login";
            response.sendRedirect(url);
            return false;
        }

    }
}
