package com.security.demo.controller;

import com.security.demo.bean.component.RespObject;
import com.security.demo.bean.login.SignInReqBo;
import com.security.demo.service.login.LoginService;
import com.security.demo.util.RedisUtils;
import com.security.demo.util.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/4/17 - 17:52
 * @history 2020/4/17 - 17:52 chenglonghy  create.
 */
@Slf4j
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 用户注册接口
     */
    @PostMapping(value = "/signIn")
    public RespObject signIn(@RequestBody SignInReqBo signInReqBo) {
        log.debug("用户注册");
        return loginService.signIn(signInReqBo);
    }

    @GetMapping(value = "/getVercode")
    public void getVercode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setDateHeader("Expires", 0L);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");

        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = req.getSession();
        session.setAttribute("verify_code", text);
        //设置验证码30秒内有效
        session.setMaxInactiveInterval(30);
        VerifyCode.output(image, resp.getOutputStream());
        String id = req.getRequestedSessionId();
        RedisUtils.set("securityDemo_verifyCode", text, 30);
    }
}
