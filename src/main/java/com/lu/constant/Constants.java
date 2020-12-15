package com.lu.constant;

/**
 * 通用常量信息
 *
 * @author lzkj
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final int SUCCESS = 1;

    /**
     * 通用失败标识
     */
    public static final int FAIL = 0;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 生成编号redis 前缀
     */
    public static final String NUM_PREFIX = "num:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 令牌自定义传输标识
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 登录token key前缀
     */
    public static final String LOGIN_TOKEN_KEY = "access_token:";

    /**
     * 用户id缓存前缀
     */
    public final static String USER_KEY = "access_user:";

    /**
     * 登录客户端key
     */
    public static final String CLIENT_KEY = "client";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 5;

    /**
     * token快过期时间定义
     */
    public static final Long MILLIS_MINUTE_TEN = 10 * 60 * 1000L;

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * id 主键id
     */
    public static final String ID = "id";

    /**
     * del_flag 删除标志
     */
    public static final String DEL_FLAG = "del_flag";

}
