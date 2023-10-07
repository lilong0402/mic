package top.lilong.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lilong.resp.CommonResp;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 13:25
 * @注释
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
 @ExceptionHandler(value = Exception.class)
 @ResponseBody
 public CommonResp<?> exceptionHandler(Exception e){
  CommonResp<Object> resp = new CommonResp<>();
  log.error("系统异常",e);
  resp.setSuccess(false);
  resp.setMessage(e.getMessage());
  return resp;
 }
}
