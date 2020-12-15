package com.lu.enums;

import com.lu.utils.DateUtils;
import lombok.Getter;

/**
 * 生成编号类型枚举
 * 前缀不能重复
 * @author jwb
 */
@Getter
public enum NumType {

    // 成品编号：Redis key有效期一天
    PRODUCT_ORDER("P", DateUtils.YYYYMMDD, 4, 0, 24*3600L),
    ;


    /**
     * 编号前缀
     * 为空时填""
     */
    private String prefix;

    /**
     * 时间格式表达式
     * 例如：yyyyMMdd
     */
    private String datePattern;

    /**
     * 自增序号长度
     */
    private int serialLength;

    /**
     * 随机数长度
     */
    private int randomLength;

    /**
     * redis 过期时间,单位秒（-1为不过期）
     */
    private long expireTime;

    NumType(String prefix, String datePattern, int serialLength, int randomLength, long expireTime) {
        this.prefix = prefix;
        this.datePattern = datePattern;
        this.serialLength = serialLength;
        this.randomLength = randomLength;
        this.expireTime = expireTime;
    }

}
