package top.lilong.exception;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/7 13:33
 * @注释
 */
public enum BussinessExceptionEnum {
    PHONE_NOT_EXIST("手机号不存在"),PHOME_EXIST("手机号已被注册"),PASSWORD_ERROR("密码错误");
    private String desc;
    BussinessExceptionEnum(String desc){
        this.desc=desc;
    }
    public void setDesc(String desc){
        this.desc=desc;
    }
    @Override
    public String toString(){
        return "BussinessExceptionEnum{"+"desc='"+desc+'\''+'}';
    }

}
