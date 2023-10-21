package top.lilong.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 18:21
 * @注释
 */
@Configuration
public class MyBatisPlusConfig {

 @Bean
 public MybatisPlusInterceptor mybatisPlusInterceptor(){
  MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
  mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
  return mybatisPlusInterceptor;
 }
}
