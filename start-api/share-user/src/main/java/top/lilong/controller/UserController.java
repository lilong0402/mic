package top.lilong.controller;

import cn.hutool.log.Log;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import top.lilong.domain.dto.LoginDTO;
import top.lilong.domain.entity.User;
import top.lilong.domain.resp.UserLoginResp;
import top.lilong.domain.service.UserService;
import top.lilong.resp.CommonResp;

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
 public CommonResp<Long> count(){
  Long count = userService.count();
  CommonResp<Long> commonResp = new CommonResp<>();
  commonResp.setData(count);
  return commonResp;
 }

 @PostMapping("/login")
 public CommonResp<UserLoginResp> login(@Valid @RequestBody LoginDTO loginDTO){
  UserLoginResp login = userService.login(loginDTO);
  CommonResp<UserLoginResp> commonResp = new CommonResp<>();
  commonResp.setData(login);
  return  commonResp;
 }
 @PostMapping("/register")
 public CommonResp<Long> register(@Valid @RequestBody LoginDTO loginDTO){
  Long id = userService.register(loginDTO);
  CommonResp<Long> commonResp = new CommonResp<>();
  commonResp.setData(id);
  return commonResp;
 }
 @GetMapping("{id}")
 public  CommonResp<User> getUserById(@PathVariable Long id){
  User user = userService.findById(id);
  CommonResp<User> commonResp = new CommonResp<>();
  commonResp.setData(user);
  return commonResp;
 }
}
