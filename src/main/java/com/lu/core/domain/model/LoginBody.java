package com.lu.core.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录对象
 *
 * @author lzkj
 */
@Data
@ApiModel(description = "登录对象")
public class LoginBody {

    /**
     * 客户端
     */
    @NotBlank(message = "客户端不能为空")
    @ApiModelProperty(value = "客户端", required = true)
    private String client;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty(value = "账号", required = true)
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String code;

    /**
     * 唯一标识
     */
    @NotBlank(message = "uuid不能为空")
    @ApiModelProperty(value = "验证码的uuid", required = true)
    private String uuid = "";

}
