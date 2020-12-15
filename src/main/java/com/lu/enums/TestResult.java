package com.lu.enums;

/**
 * 测试结果
 * @author lzkj
 * @date 2020/11/2 16:20
 */
public enum TestResult {
    BTG(0, "不通过"),

    TG(1, "通过");


    private final int code;
    private final String info;

    TestResult(int code, String info) {
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
