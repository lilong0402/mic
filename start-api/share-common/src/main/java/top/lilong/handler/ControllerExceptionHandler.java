package top.lilong.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.lilong.exception.BussinessExcepton;
import top.lilong.resp.CommonResp;
import org.springframework.validation.BindException;
/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 13:25
 * @注释
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
 @ExceptionHandler(value = BindException.class)
 @ResponseBody
 public CommonResp<?> exceptionHandler(BindException e){
  CommonResp<Object> resp = new CommonResp<>();
  log.error("系统异常： {}",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  resp.setSuccess(false);
  resp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  return resp;
 }
}
