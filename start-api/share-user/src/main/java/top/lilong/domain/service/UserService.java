package top.lilong.domain.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.lilong.domain.dto.LoginDTO;
import top.lilong.domain.entity.User;
import top.lilong.domain.mapper.UserMapper;
import top.lilong.domain.resp.UserLoginResp;
import top.lilong.exception.BussinessExceptionEnum;
import top.lilong.exception.BussinessExcepton;
import top.lilong.util.JwtUtil;
import top.lilong.util.SnowUtil;

import java.util.Date;
import java.util.Map;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 12:40
 * @注释
 */
@Service
public class UserService {
 @Resource
 private UserMapper userMapper;
 public Long count(){
  return userMapper.selectCount(null);
 }
 public UserLoginResp login(LoginDTO loginDTO){
  User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
  if (user==null)  throw new BussinessExcepton(BussinessExceptionEnum.PHONE_NOT_EXIST);
//   throw  new RuntimeException("手机号不存在");
  if (!user.getPassword().equals(loginDTO.getPassword()))  throw  new BussinessExcepton(BussinessExceptionEnum.PASSWORD_ERROR);
//   throw new RuntimeException("密码错误");
  UserLoginResp userLoginResp = UserLoginResp.builder().user(user).build();
//  String key="InfinityX7";
//  Map<String, Object> map = BeanUtil.beanToMap(userLoginResp);
  String token = JwtUtil.createToken(userLoginResp.getUser().getId(),userLoginResp.getUser().getPhone());
  userLoginResp.setToken(token);
  return userLoginResp;
 }
 public Long register(LoginDTO loginDTO){
  User userDB= userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone,loginDTO.getPhone()));
  if (userDB != null){
   throw  new BussinessExcepton(BussinessExceptionEnum.PHOME_EXIST);
  }
//  if (!userDB.getPassword().equals(loginDTO.getPassword())) throw new BussinessExcepton(BussinessExceptionEnum.PASSWORD_ERROR);
  User saveduser = User.builder()
          .id(SnowUtil.getSnowflakeNextId())
          .phone(loginDTO.getPhone())
          .password(loginDTO.getPassword())
          .nickname("新用户")
          .roles("user")
          .avatarUrl("https://c-ssl.dtstatic.com/uploads/blog/202201/23/20220123222213_2899a.thumb.400_0.jpeg")
          .bonus(100)
          .createTime(new Date())
          .updateTime(new Date())
          .build();
  userMapper.insert(saveduser);
  return saveduser.getId();
 }

 public User findById(Long userId){
  return userMapper.selectById(userId);
 }
}
