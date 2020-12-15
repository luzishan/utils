package com.lu.exception;

import com.lu.enums.ExceptionCodes;
import com.lu.utils.StringUtils;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义基础异常
 *
 * @author lzkj
 */
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    public BaseException(String module, int code, Object[] args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }


    public BaseException(String message) {
        this(null, ExceptionCodes.ERROR.getCode(), null, message);
    }

    public BaseException(int code, String message) {
        this(null, code, null, message);
    }

    public BaseException(ExceptionCodes e) {
        this(null, e.getCode(), null, e.getMessage());
    }

    /**
     * 判断对象是否是非空，如果是非空，则抛出异常说明
     *
     * @param object 需要校验的对象
     * @param code   异常参数编码
     * @param msg    抛出的异常说明
     */
    public static void throwException(Object object, int code, String msg) {
        if (StringUtils.isNull(object)) {
            throw new BaseException(code, msg);
        }
        if (object instanceof String) {
            if (StringUtils.isEmpty(object + "")) {
                throw new BaseException(code, msg);
            }
        } else if (object instanceof Collection) {
            if (CollectionUtils.isEmpty((Collection) object)) {
                throw new BaseException(code, msg);
            }
        } else if (object instanceof Map) {
            Map map = (Map) object;
            if (map == null || map.size() == 0) {
                throw new BaseException(code, msg);
            }
        }
    }

    /**
     * 判断对象是否异常，如果是非空，则抛出异常说明
     *
     * @param object 需要校验的对象
     * @param msg    抛出的异常说明
     */
    public static void throwException(Object object, String msg) {
        throwException(object, ExceptionCodes.ERROR.getCode(), msg);
    }


}
