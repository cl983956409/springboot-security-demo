package com.security.demo.service.login;

import com.security.demo.bean.component.RespObject;
import com.security.demo.bean.login.SignInReqBo;
import com.security.demo.bean.user.UserInfo;
import com.security.demo.dao.user.UserDao;
import com.security.demo.dao.user.UserRoleDao;
import com.security.demo.enums.RespStatus;
import com.security.demo.util.RespUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/4/22 - 14:40
 * @history 2020/4/22 - 14:40 chenglonghy  create.
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserDao userDao;
    @Resource
    private UserRoleDao userRoleDao;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public RespObject signIn(SignInReqBo signInReqBo) {
        try {
            //判断用户是否已存在
            UserInfo userInfo = userDao.selectUserInfo(signInReqBo.getUsername());
            if (userInfo != null) {
                return RespUtils.resp(RespStatus.SIGN_IN_ERROR, "用户已存在，请登录");
            }
            //不存在则插入新用户信息
            signInReqBo.setPass(passwordEncoder.encode(signInReqBo.getPass()));
            userDao.insertUserInfo(signInReqBo);
            if (signInReqBo.getId() > 0) {
                int res = userRoleDao.insertUserRole(signInReqBo.getId(), signInReqBo.getRoles());
            }
        } catch (SQLException e) {
            log.error("用户信息插入数据库异常");
            return RespUtils.error("数据库异常");
        }
        return RespUtils.success();
    }
}
