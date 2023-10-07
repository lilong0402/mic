package top.lilong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 10:17
 * @注释
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "hello World!";
    }
}
