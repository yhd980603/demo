package com.example.demo.exception;

/**
 * 自定义异常类
 */
public class XdException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常消息

    public XdException(){
        super();
    }

    public XdException(int code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
