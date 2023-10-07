package top.lilong.domain.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.lilong.domain.mapper.UserMapper;

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
}
