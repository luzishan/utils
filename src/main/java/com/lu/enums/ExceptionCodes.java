package com.lu.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 异常码枚举
 *
 * @author jwb
 */
public enum ExceptionCodes {

    ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PARAM_ERROR(3, "参数异常"),
    CHECK_ERROR(4, "自定义校验异常"),
    DATA_EXIST(6, "数据已存在"),
    DATA_NOT_EXIST(7, "数据不存在"),

    // 100-999 系统错误码
    SYSTEM_ERROR(100, "系统模块异常"),
    USER_NOT_EXIST(101, "账号不存在"),
    USER_INVALID(102, "账号被禁用"),
    USER_DELETED(103, "账号被删除"),
    PASSWORD_ERROR(104, "密码错误"),
    USER_EXIST_ERROR(106, "账号已存在"),

    AUDITED(180, "数据已审核"),

    CODE_NULL(197, "验证码为空"),
    CODE_EXPIRE(198, "验证码失效"),
    CODE_ERROR(199, "验证码错误"),

    UNAUTHORIZED(401, "认证失败（无效token/登录过期/账号在别处登录）"),
    FORBIDDEN(403, "没有分配权限，请联系管理员"),
    SERVICE_NOT_FOUND(404, "服务未找到或路径不存在"),

    // 1000-1999 mes模块错误码


    ;

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回码说明
     */
    private String message;

    ExceptionCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过code获取对应的ExceptionCode
     *
     * @param code 错误码
     */
    public static ExceptionCodes getExceptionCodes(int code) {
        if (code == 0) {
            throw new NullPointerException("响应编码为空");
        }

        for (ExceptionCodes e : ExceptionCodes.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("未能找到匹配的ExceptionCodes:" + code);
    }

    public static List getAllCodes() {
        List<Map> list = new ArrayList<>();
        for (ExceptionCodes commonCode : ExceptionCodes.values()) {
            Map map = new HashMap();
            map.put("code", commonCode.getCode());
            map.put("message", commonCode.getMessage());
            list.add(map);
        }
        return list;
    }

    /**
     * 获取响应编码
     *
     * @return
     */
    public int getCode() {
        return this.code;
    }

    /**
     * 获取编码对应消息
     *
     * @return
     */
    public String getMessage() {
        return this.message;
    }

}