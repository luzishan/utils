package com.lu.utils;

import com.lu.enums.NumType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 编号生成工具类
 * @author jwb
 */
public class NumGenerateUtil {

    /**
     * 生成前缀+随机数字 的 编号
     * @param prefix 前缀
     * @param randomLength 随机数长度
     * @return
     */
    public static String generateNum(String prefix, int randomLength) {
        return prefix + RandomUtils.generateString(randomLength, RandomUtils.NUM_TYPE);
    }

    /**
     * 生成前缀+随机数字/字符 的 编号
     * @param prefix 前缀
     * @param randomLength 随机数长度
     * @return
     */
    public static String generateNumStr(String prefix, int randomLength) {
        return prefix + RandomUtils.generateString(randomLength, RandomUtils.RANDOM_TYPE);
    }

    /**
     * 根据Redis自增生产有序编号
     * @param redisUtils
     * @param numTypeEnum
     * @return
     */
    public static String generateNum(RedisUtils redisUtils, NumType numTypeEnum) {
        return generateNum(redisUtils, numTypeEnum.getPrefix(), numTypeEnum.getDatePattern(),
                numTypeEnum.getSerialLength(), numTypeEnum.getRandomLength(), numTypeEnum.getExpireTime());
    }

    /**
     * 生成前缀+年月日+随机数 的 编号
     * @param prefix 前缀
     * @param datePattern 时间格式
     * @param randomLength 随机数长度
     * @return
     */
    public static String generateNum(String prefix, String  datePattern, int randomLength) {
        return generateNum(null, prefix, datePattern, 0, randomLength, 0);
    }

    /**
     * 生成前缀+年月日+序号 的 编号
     * @param redisUtils
     * @param prefix 前缀
     * @param datePattern 时间格式
     * @param serialLength 序号长度
     * @param expireTime redis 自增序列号过期时间
     * @return
     */
    public static String generateNum(RedisUtils redisUtils, String prefix, String  datePattern, int serialLength, long expireTime) {
        return generateNum(redisUtils, prefix, datePattern, serialLength, 0, expireTime);
    }

    /**
     * 利用Redis自增实现编号有序
     * 生成前缀+年月日+序号+随机数 的 编号
     * @param prefix 前缀
     * @param datePattern 时间格式
     * @param serialLength 自增序列号长度
     * @param randomLength 随机数长度
     * @param expireTime redis 自增序列号过期时间
     * @return
     */
    public static String generateNum(RedisUtils redisUtils, String prefix, String  datePattern, int serialLength, int randomLength, long expireTime) {
        StringBuffer sb = new StringBuffer();

        sb.append(prefix);
        if (StringUtils.isNotBlank(datePattern)) {
            sb.append(DateUtils.parseDateToStr(datePattern, new Date()));
        }
        if (serialLength > 0) {
            // 用前缀+ 日期作为 key，到了过期时间会自动更新重新开始计数
            Long serialNum = redisUtils.getIncr(sb.toString(), expireTime);
            // 需要补0的长度=流水号长度 -自增计数长度
            int length = serialLength - String.valueOf(serialNum).length();
            // 序号补零
            for (int i = 0; i < length; i++) {
                sb.append("0");
            }
            sb.append(serialNum);
        }
        if (randomLength > 0) {
            sb.append(RandomUtils.generateString(randomLength, RandomUtils.NUM_TYPE));
        }

        return sb.toString();
    }
}
