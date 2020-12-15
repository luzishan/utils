package com.lu.core.domain;

import com.lu.enums.ExceptionCodes;
import com.lu.utils.StringUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author lzkj
 */
public class ResultMap extends HashMap<String, Object> {
    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";
    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";
    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 ResultMap 对象，使其表示一个空消息。
     */
    public ResultMap() {
    }

    /**
     * 初始化一个新创建的 ResultMap 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResultMap(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ResultMap 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResultMap(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResultMap success() {
        return ResultMap.success(ExceptionCodes.SUCCESS.getMessage());
    }

    /**
     * 返回成功消息
     * @param rows 行数
     * @return 成功消息
     */
    public static ResultMap success(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回成功消息
     * @param b true or false
     * @return 成功消息
     */
    public static ResultMap success(boolean b) {
        return b ? success() : error();
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResultMap success(Object data) {
        return ResultMap.success(ExceptionCodes.SUCCESS.getMessage(), data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResultMap success(String msg) {
        return ResultMap.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResultMap success(String msg, Object data) {
        return new ResultMap(ExceptionCodes.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResultMap error() {
        return ResultMap.error(ExceptionCodes.ERROR.getMessage());
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultMap error(String msg) {
        return ResultMap.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResultMap error(String msg, Object data) {
        return new ResultMap(ExceptionCodes.ERROR.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static ResultMap error(int code, String msg) {
        return new ResultMap(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResultMap error(ExceptionCodes e) {
        return ResultMap.error(e.getCode(), e.getMessage());
    }
}
