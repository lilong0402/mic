package top.lilong.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/22 19:56
 * @注释
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddBonusMsgDTO {

 /**
  * 为谁加积分
  */

 private Long userId;

 /**
  * 加多少分
  */
 private Integer bonus;

 /**
  * 描述信息
  */
 private String description;

 /**
  * 积分事件
  */
 private String event;
}

