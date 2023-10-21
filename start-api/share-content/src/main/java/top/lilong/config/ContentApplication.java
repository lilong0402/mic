package top.lilong.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:27
 * @注释
 */
@SpringBootApplication
@Component("top.lilong")
@MapperScan("top.lilong.*.mapper")
@Slf4j
public class ContentApplication {
 public static void main(String[] args){
  SpringApplication app = new SpringApplication(ContentApplication.class);
  Environment env = app.run(args).getEnvironment();
  log.info("修改成功！！");
  log.info("测试地址: localhost://127.0.0.1:{}{}/hello",env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
 }

}
