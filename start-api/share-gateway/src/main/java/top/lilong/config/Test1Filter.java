//package top.lilong.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @version 1.0
// * @Author 李龙
// * @Date 2023/10/8 16:54
// * @注释
// */
//@Component
//@Slf4j
//public class Test1Filter implements GlobalFilter, Ordered {
//
// @Override
// public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//  log.info("Test1Filter");
//  return chain.filter(exchange);
// }
//
// @Override
// public int getOrder() {
//  return 0;
// }
//}
