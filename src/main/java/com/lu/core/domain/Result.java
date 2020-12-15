package com.lu.core.domain;


import com.lu.enums.ExceptionCodes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应消息
 *
 * @author lzkj
 */
@ApiModel(description = "返回信息")
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "响应码")
    protected int code;

    @ApiModelProperty(value = "响应消息")
    protected String msg;

    @ApiModelProperty(value = "响应数据")
    protected T data;

    public Result() {
        super();
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return Result.success(ExceptionCodes.SUCCESS.getMessage());
    }

    /**
     * 返回成功消息
     *
     * @param rows 行数
     * @return 成功消息
     */
    public static Result success(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data) {
        return Result.success(ExceptionCodes.SUCCESS.getMessage(), data);
    }

//    /**
//     * 返回成功消息
//     *
//     * @param msg 返回内容
//     * @return 成功消息
//     */
//    public static Result success(String msg) {
//        return Result.success(msg, null);
//    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Result success(String msg, Object data) {
        return new Result(ExceptionCodes.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error() {
        return Result.error(ExceptionCodes.ERROR.getMessage());
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(String msg) {
        return Result.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Result error(String msg, Object data) {
        return new Result(ExceptionCodes.ERROR.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Result error(ExceptionCodes e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}
