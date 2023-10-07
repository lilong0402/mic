package top.lilong.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Aspect;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 13:08
 * @注释
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
 private String phone;
 private String password;
}

