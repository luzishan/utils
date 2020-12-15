package com.lu.annotation;

import com.lu.enums.BusinessType;
import com.lu.enums.OperatorType;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author lzkj
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否打印请求
     */
    boolean print() default true;

    /**
     * 是否保存到数据库
     */
    boolean save() default false;

    /**
     * 是否保存请求的参数
     */
    public boolean saveParam() default false;
}
