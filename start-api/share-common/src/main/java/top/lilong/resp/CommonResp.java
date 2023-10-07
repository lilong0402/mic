package top.lilong.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 13:20
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResp<T> {
 private Boolean success = true;
 private String message;
 private T data;
}
