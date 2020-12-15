package com.lu.enums;

/**
 * 删除标志
 * @author lzkj
 * @date 2020/11/2 16:20
 */
public enum DelFlag {
    EXIST(0, "存在"), DELETED(1, "删除");

    private final int code;
    private final String info;

    DelFlag(int code, String info) {
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
