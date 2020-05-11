package com.security.demo.bean.user;

import lombok.Data;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/5/8 - 15:24
 * @history 2020/5/8 - 15:24 chenglonghy  create.
 */
@Data
public class PersistentLoginsBo {
    private int id;
    private String username;
    private String series;
    private String token;
    private Date lastUsedTime;
    public PersistentLoginsBo(){

    }

    /**
     * PersistentRememberMeToken 转 PersistentLoginsBo
     *
     * @param token
     */
    public PersistentLoginsBo(PersistentRememberMeToken token) {
        this.username = token.getUsername();
        this.series = token.getSeries();
        this.token = token.getTokenValue();
        this.lastUsedTime = token.getDate();
    }

    /**
     * PersistentLoginsBo 转 PersistentRememberMeToken
     *
     * @param persistentLoginsBo
     * @return
     */
    public PersistentRememberMeToken toToken(PersistentLoginsBo persistentLoginsBo) {
        String username = persistentLoginsBo.getUsername();
        String series = persistentLoginsBo.getSeries();
        String token = persistentLoginsBo.getToken();
        Date lastUsedTime = persistentLoginsBo.getLastUsedTime();
        PersistentRememberMeToken persistentRememberMeToken = new PersistentRememberMeToken(username, series, token, lastUsedTime);
        return persistentRememberMeToken;
    }
}
