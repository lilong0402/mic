package top.lilong.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/22 19:29
 * @注释
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BonusEventLog {
 private Long id;
 private Long userId;
 private Integer value;
 private String description;
 private String event;
 private Date createTime;

}
