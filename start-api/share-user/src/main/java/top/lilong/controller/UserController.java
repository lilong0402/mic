package top.lilong.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.lilong.domain.dto.LoginDTO;
import top.lilong.domain.entity.User;
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

 @PostMapping("/login")
 public User login(@RequestBody LoginDTO loginDTO){
  return  userService.login(loginDTO);
 }
}
