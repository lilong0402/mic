package top.lilong.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/22 20:05
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShareRequestDTO {
 private Long userId;
 private String author;
 private String title;
 private Boolean isOriginal;
 private Integer price;
 private String downloadUrl;
 private String cover;
 private String summary;
}
