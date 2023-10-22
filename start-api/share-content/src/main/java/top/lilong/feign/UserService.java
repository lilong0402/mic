package top.lilong.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.lilong.resp.CommonResp;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/22 19:15
 * @注释
 */
@FeignClient(value = "user-service",path = "/user")
public interface UserService {
// @GetMapping("/{id}")
// CommonResp<User> getUser(@PathVariable Long id);
}
