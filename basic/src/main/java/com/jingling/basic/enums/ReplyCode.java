package com.jingling.basic.enums;

/**
 * <p>向前台反馈信息的编码<p>
 *
 * @Auther: zyy-finalcola
 * @Date: 2018-01-20 22:59
 */
public enum ReplyCode {
    NOT_LOGIN(-1,"您尚未登录或登录时间过长,请重新登录或刷新页面!"),
    OK(0, "OK"),
    WRONG_URL(400,"请求路径错误"),
    WRONG_ROLE(401, "身份错误"),
    REQUEST_FAILED(500, "请求失败，请重试"),
    NULL_ATTR(30,"属性不能为空"),
    ATTR_WRONG(31, "属性填写错误"),
    WRONG_LENGTH(32, "数据长度不符合要求"),
    WRONG_PATTERN(33, "数据格式错误"),
    VAILD_WRONG(100,"验证码错误"),
    CUSTOM(999, "")
    ;

    ReplyCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public ReplyCode setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReplyCode setMessage(String message) {
        this.message = message;
        return this;
    }

}
