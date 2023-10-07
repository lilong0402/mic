package top.lilong.model;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/9/4 15:16
 * @注释
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
 private  String name;
 private Book book;
}
