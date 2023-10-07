package top.lilong.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/9/4 14:41
 * @注释
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

 private Integer id;
 private  String name;
 private  String author;
 private String code;

}
