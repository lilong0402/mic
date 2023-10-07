package top.lilong.domain.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.lilong.domain.dto.LoginDTO;
import top.lilong.domain.entity.User;
import top.lilong.domain.mapper.UserMapper;
import top.lilong.exception.BussinessExceptionEnum;
import top.lilong.exception.BussinessExcepton;

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
 public User login(LoginDTO loginDTO){
  User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
  if (user==null)  throw new BussinessExcepton(BussinessExceptionEnum.PHONE_NOT_EXIST);
//   throw  new RuntimeException("手机号不存在");
  if (!user.getPassword().equals(loginDTO.getPassword()))  throw  new BussinessExcepton(BussinessExceptionEnum.PASSWORD_ERROR);
//   throw new RuntimeException("密码错误");
  return user;
 }
}
