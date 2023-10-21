package top.lilong.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:45
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MidUserShare {
 private Long id;
 private Long shareId;
 private Long userId;
}
