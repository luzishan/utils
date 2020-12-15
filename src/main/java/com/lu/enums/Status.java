package com.lu.enums;

/**
 * 状态
 *
 * @author lzkj
 */
public enum Status {
    OK(1, "正常/启用"), DISABLE(0, "异常/禁用");

    private final int code;
    private final String info;

    Status(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
