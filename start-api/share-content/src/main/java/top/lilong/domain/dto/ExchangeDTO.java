package top.lilong.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/22 20:02
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeDTO {
 private Long userId;
 private Long shareId;
}

