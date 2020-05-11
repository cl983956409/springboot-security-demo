package com.security.demo.service.login;

import com.security.demo.bean.component.RespObject;
import com.security.demo.bean.login.SignInReqBo;

/**
 * @author chenglonghy
 * @date 2020/4/22
 * <p>
 * 功能说明:
 */
public interface LoginService {

    RespObject signIn(SignInReqBo signInReqBo);
}
