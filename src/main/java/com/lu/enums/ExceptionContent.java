package com.lu.enums;

/**
 * 异常内容
 * @author lzkj
 * @date 2020/11/2 16:20
 */
public enum ExceptionContent {
    SMSB(1, " 扫码失败 "),

    SLBZ(2, "数量不足"),

    BHG(3, "不合格"),

    YWT(4, "有问题");

    private final int code;
    private final String info;

    ExceptionContent(int code, String info) {
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
