package com.asama.onlinequiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.asama.onlinequiz.interceptor.LecturerInterceptor;
import com.asama.onlinequiz.interceptor.ManagerInterceptor;
import com.asama.onlinequiz.interceptor.StudentInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    StudentInterceptor studentInterceptor;
    
    @Autowired
    LecturerInterceptor lecturerInterceptor;
    
    @Autowired
    ManagerInterceptor managerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(studentInterceptor).addPathPatterns("/dotest/**", "/mytest/**");
        registry.addInterceptor(lecturerInterceptor).addPathPatterns("/lecturer/**");
        registry.addInterceptor(managerInterceptor).addPathPatterns("/manager/**");
    }
   
}
