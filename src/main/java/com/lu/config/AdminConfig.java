package com.lu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author lzkj
 */
@Component
@ConfigurationProperties(prefix = "lzkj")
public class AdminConfig {
    /**
     * 上传路径
     */
    private static String profile;
    /**
     * 前端使用的文件路径
     */
    private static String preProfile;
    /**
     * 文件上传保存的服务器ip和端口
     */
    private static String profileIpAndPort;
    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 版权年份
     */
    private String copyrightYear;

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        AdminConfig.profile = profile;
    }

    public static String getPreProfile() {
        return preProfile;
    }

    public void setPreProfile(String preProfile) {
        AdminConfig.preProfile = preProfile;
    }

    public static String getProfileIpAndPort() {
        return profileIpAndPort;
    }

    public void setProfileIpAndPort(String profileIpAndPort) {
        AdminConfig.profileIpAndPort = profileIpAndPort;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        AdminConfig.addressEnabled = addressEnabled;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath() {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath() {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath() {
        return getProfile() + "/upload";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }
}
