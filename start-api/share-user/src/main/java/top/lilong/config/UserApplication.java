package top.lilong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 10:17
 * @注释
 */
@SpringBootApplication
@ComponentScan("top.lilong")
@Slf4j
public class UserApplication {
 public static void main(String[] args) {
  SpringApplication app=new SpringApplication(UserApplication.class);
//  SpringApplication.run(UserApplication.class);
  Environment environment=app.run(args).getEnvironment();
  log.info("启动成功！！！");
  log.info("测试地址：http://127.0.0.1:{}{}/hello",environment.getProperty("server.port"),environment.getProperty("server.servlet.context-path"));
 }
}
