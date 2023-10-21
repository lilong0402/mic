package top.lilong.interceptor;

import cn.hutool.core.util.RandomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:16
 * @注释
 */
@Component
public class Loginterceptor implements HandlerInterceptor {

 @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  MDC.put("LOG_ID",System.currentTimeMillis()+ RandomUtil.randomString(3));
  return true;
 }
}
