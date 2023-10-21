package top.lilong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.lilong.util.JwtUtil;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/8 17:00
 * @注释
 */
@Component
@Slf4j
public class LoginUserFilter implements Ordered, GlobalFilter {
 @Override
 public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
  String path = exchange.getRequest().getURI().getPath();
  if (path.contains("/admin")|| path.contains("/hello")||path.contains("/user-service/user/login")||path.contains("user-service/user/register")
  || path.contains("content-service/share/notice")
  ){
   log.info("不需要登录验证：{}",path);
   return chain
           .filter(exchange);
  }else {
   log.info("需要登录验证: {}",path);
  }
  String token=exchange.getRequest().getHeaders().getFirst("token");
  log.info("会员登录验证开始，token: {}",token);
  if (token == null || token.isEmpty()){
   log.info("token 为空，请求被拦截");
   exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
   return exchange.getResponse().setComplete();
  }

  boolean validate = JwtUtil.validate(token);
  if (validate){
   log.info("token 有效，放行该请求");
   return chain.filter(exchange);
  }else {
   log.warn("token 无效，请求被拦截");
   exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
   return exchange.getResponse().setComplete();
  }
 }

 @Override
 public int getOrder() {
  return 0;
 }
}
