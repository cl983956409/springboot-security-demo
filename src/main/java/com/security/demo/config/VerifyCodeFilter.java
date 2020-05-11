package com.security.demo.config;

import com.security.demo.enums.RespStatus;
import com.security.demo.util.RedisUtils;
import com.security.demo.util.RespUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/5/8 - 17:12
 * @history 2020/5/8 - 17:12 chenglonghy  create.
 */
@Configuration
public class VerifyCodeFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if ("POST".equalsIgnoreCase(req.getMethod()) && "/login".equals(req.getServletPath())) {
            String reqCode = req.getParameter("verify_code");
            //String sessionCode = (String) req.getSession().getAttribute("verify_code"); // 通过session获取验证码匹配
            String sessionCode = RedisUtils.get("securityDemo_verifyCode");//限制验证码只能使用一次，校验通过后删除缓存
            if (StringUtils.isEmpty(reqCode) || StringUtils.isEmpty(sessionCode) || !reqCode.equalsIgnoreCase(sessionCode)) {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(String.valueOf(RespUtils.resp(RespStatus.LOGIN_IN_ERROR, "验证码错误")));
                out.flush();
                out.close();
            } else {
                RedisUtils.del("securityDemo_verifyCode");
                filterChain.doFilter(req, resp);
            }
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
