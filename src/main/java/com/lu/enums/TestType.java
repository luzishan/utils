package com.lu.enums;

/**
 * 测试类型
 * @author lzkj
 * @date 2020/11/2 16:20
 */
public enum TestType {
    ZTQR(1, "姿态确认"),

    ZZBBCS(2, "直阻变比测试"),

    NYCS(3, "耐压测试"),

    LDCS(4, "雷电测试"),

    DMCS(5, "打磨检测");

    private final int code;
    private final String info;

    TestType(int code, String info) {
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
