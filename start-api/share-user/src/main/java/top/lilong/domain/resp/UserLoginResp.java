package top.lilong.domain.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.lilong.domain.entity.User;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/8 16:31
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResp {
 private User user;
 private String token;
}
