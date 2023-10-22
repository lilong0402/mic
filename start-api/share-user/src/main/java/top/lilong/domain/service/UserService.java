package top.lilong.domain.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.lilong.domain.dto.LoginDTO;
import top.lilong.domain.dto.UserAddBonusMsgDTO;
import top.lilong.domain.entity.BonusEventLog;
import top.lilong.domain.entity.User;
import top.lilong.domain.mapper.BonusEventLogMapper;
import top.lilong.domain.mapper.UserMapper;
import top.lilong.domain.resp.UserLoginResp;
import top.lilong.exception.BussinessExceptionEnum;
import top.lilong.exception.BussinessExcepton;
import top.lilong.util.JwtUtil;
import top.lilong.util.SnowUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 12:40
 * @注释
 */
@Service
@Slf4j
public class UserService {


 @Resource
 private UserMapper userMapper;

 @Resource
 private BonusEventLogMapper bonusEventMapper;

 public void updateBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
  System.out.println(userAddBonusMsgDTO);
  //1.为用户修改积分
  Long userId = userAddBonusMsgDTO.getUserId();
  Integer bonus = userAddBonusMsgDTO.getBonus();
  User user = userMapper.selectById(userId);
  user.setBonus(user.getBonus() + bonus);
  userMapper.update(user, new QueryWrapper<User>().lambda().eq(User::getId, userId));

  //记录日志到bonus_event_log表中
  bonusEventMapper.insert(
          BonusEventLog.builder()
                  .userId(userId)
                  .value(bonus)
                  .description(userAddBonusMsgDTO.getDescription())
                  .event(userAddBonusMsgDTO.getEvent())
                  .createTime(new Date())
                  .build()
  );
  log.info("积分加载完毕....");
 }

 public Long count() {
  return userMapper.selectCount(null);
 }

 public UserLoginResp login(LoginDTO loginDTO) {
  //根据手机号查询用户
  User userDB = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
  //没有找到
  if (userDB == null) {
   throw new BussinessExcepton(BussinessExceptionEnum.PHONE_NOT_EXIST);
  }
  //密码错误
  if (!userDB.getPhone().equals(loginDTO.getPhone())) {
   throw new BussinessExcepton(BussinessExceptionEnum.PASSWORD_ERROR);
  }
  //都正确,返回
  UserLoginResp userLoginResp = UserLoginResp.builder()
          .user(userDB)
          .build();
//        String key = "xuelong";
//        Map<String,Object> map = BeanUtil.beanToMap(userLoginResp);
//        String token = JWTUtil.createToken(map, key.getBytes());
  String token = JwtUtil.createToken(userLoginResp.getUser().getId(), userLoginResp.getUser().getPhone());
  userLoginResp.setToken(token);
  return userLoginResp;
 }

 public Long register(LoginDTO loginDTO) {
  //根据手机号查询用户
  User userDB = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getPhone, loginDTO.getPhone()));
  //找到了，手机号已被注册
  if (userDB != null) {
   throw new BussinessExcepton(BussinessExceptionEnum.PHONE_NOT_EXIST);
  }
  //没有注册
  User saveUser = User.builder()
          //使用雪花算法生成id
          .id(SnowUtil.getSnowflakeNextId())
          .phone(loginDTO.getPhone())
          .password(loginDTO.getPassword())
          .nickname("新用户")
          .roles("user")
          .avatarUrl("https://c.53326.com/d/file/lan20191114/2wy1jboyf2f.jpg")
          .bonus(100)
          .createTime(new Date())
          .updateTime(new Date())
          .build();
  userMapper.insert(saveUser);
  return saveUser.getId();
 }


 public User findById(Long userId) {
  return userMapper.selectById(userId);
 }


 public List<BonusEventLog> logByUserId(Long userId) {
  LambdaQueryWrapper<BonusEventLog> wrapper = new LambdaQueryWrapper<>();
  wrapper.orderByDesc(BonusEventLog::getCreateTime);
  wrapper.eq(BonusEventLog::getUserId, userId);
  List<BonusEventLog> logs = bonusEventMapper.selectList(wrapper);
  return logs;
 }
}