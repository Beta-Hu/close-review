package com.beta.closereview.enums;

public enum StatusEnum {
    OK(0, "ok"),
    // login status
    USER_NOT_FOUND(0x100001, "user not found"),
    EMAIL_OR_PASSWORD_INCORRECT(0x100002, "mail or password is incorrect");

    final Integer code;
    final String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
