package com.lu.enums;

/**
 * 异常类别
 * @author lzkj
 * @date 2020/11/2 16:20
 */
public enum ExceptionType {
    LLQR(1, "来料确认"),

    BL(2, "补料"),

    QRCS(3, "确认测试"),

    QRZT(4, "确认姿态"),

    QRFX(5, "返修完成");

    private final int code;
    private final String info;

    ExceptionType(int code, String info) {
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
