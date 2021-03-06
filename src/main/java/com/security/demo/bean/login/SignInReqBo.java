package com.security.demo.bean.login;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/4/26 - 15:09
 * @history 2020/4/26 - 15:09 chenglonghy  create.
 */
@Data
public class SignInReqBo {

    private int id;
    @NotBlank(message = "name 不能为null")
    @NotNull(message = "name 不能为空")
    private String username;
    @NotBlank(message = "password 不能为null")
    @NotNull(message = "password 不能为空")
    private String pass;
    private String roles;
}
