package top.lilong.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.lilong.interceptor.Loginterceptor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:19
 * @注释
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Resource
    private Loginterceptor loginterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginterceptor);
    }
}
