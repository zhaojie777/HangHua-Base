package com.beetle.hanghuasso.util;



public enum ResultEnum {
    SUCCESS(0, "成功"),
    ERR_UNKNOWN(-1, "未知错误"),
    ERR_INVALID_PASSWORD(-2, "帐号或密码错误"),
    ERR_INVALID_ACCOUNT(-3, "帐号或密码错误"),
    ERR_ADD_URSR_FAIL(-4, "用户已存在"),
    ERR_INVALID_SESSION(-5, "登录失效"),
    ERR_CAN_NOT_FIND_DATA(-6, "找不到数据"),
    ERR_CAN_NOT_FIND_USER(-7, "找不到数据"),
    ERR_CAN_NOT_DEL_SELF(-8, "不能删除自己"),
    ERR_NO_PERMISSION(-9, "无权进行操作"),
    ERR_USER_DISABLED(-10, "用户已被禁用"),
    ERR_WRONG_OLD_PWD(-11, "旧密码不正确")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() { return this.code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMsg() { return this.msg; };
    public void setMsg(String msg) { this.msg = msg; }
}
