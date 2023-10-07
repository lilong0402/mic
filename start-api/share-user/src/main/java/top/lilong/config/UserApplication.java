package top.lilong.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 10:17
 * @注释
 */
@SpringBootApplication
@ComponentScan("top.lilong")
public class UserApplication {
 public static void main(String[] args) {
  SpringApplication.run(UserApplication.class);
 }
}
