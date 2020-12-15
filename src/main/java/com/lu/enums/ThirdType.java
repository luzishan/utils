package com.lu.enums;

/**
 * 物料使用记录 第三张表的类型
 * @author wangpan
 * @date 2020/11/20 11:39
 */
public enum ThirdType {
    //1：绕线，2：浇注，3：组装
    WRAP(1,"绕线"), POUR(2,"浇注"), ASSEMBLE(3,"组装");
    private final int code;
    private final String info;

    ThirdType(int code, String info) {
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
