package com.lu.type;

/**
 * Copyright © 2020 绿舟科技. All rights reserved.
 *
 * @Project: admin
 * @ClassName: DelFlagEnum
 * @Package: com.lu.type
 * @author: Amos
 * @Description: 删除标志的枚举
 * @date: 2020/10/30 14:32
 * @Version: V1.0
 */
public enum DelFlagEnum {
    /**
     * 是否删除—— 是
     */
    TRUE("2", "是"),
    /**
     * 是否删除——否
     */
    FALSE("0", "否");
    private String code;
    private String msg;

    DelFlagEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static DelFlagEnum code(String code) {
        DelFlagEnum[] arr = DelFlagEnum.values();
        for (DelFlagEnum dfe : arr) {
            if (dfe.code.equalsIgnoreCase(code)) {
                return dfe;
            }
        }
        return null;
    }

    public String code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
