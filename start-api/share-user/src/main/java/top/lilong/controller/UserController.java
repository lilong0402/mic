package top.lilong.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lilong.domain.service.UserService;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 12:41
 * @注释
 */
@RestController
@RequestMapping("/user")
public class UserController {
 @Resource
 private UserService userService;
 @GetMapping("/count")
 public Long count(){
  return userService.count();
 }
}
