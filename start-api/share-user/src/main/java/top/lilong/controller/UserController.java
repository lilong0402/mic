package top.lilong.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.lilong.domain.dto.LoginDTO;
import top.lilong.domain.entity.User;
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
 public CommonResp<User> login(@RequestBody LoginDTO loginDTO){
  User login = userService.login(loginDTO);
  CommonResp<User> commonResp = new CommonResp<>();
  commonResp.setData(login);
  return  commonResp;
 }
}
