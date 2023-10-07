package top.lilong.exception;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 13:36
 * @注释
 */
public class BussinessExcepton extends RuntimeException{
 private BussinessExceptionEnum e;
 public BussinessExcepton(BussinessExceptionEnum e){
  this.e=e;
 }
 public void setE(BussinessExceptionEnum e){
  this.e=e;
 }
}
