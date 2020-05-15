package com.security.demo.service.user.impl;

import com.security.demo.bean.user.PersistentLoginsBo;
import com.security.demo.dao.user.PersistentLoginsDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/5/9 - 11:38
 * @history 2020/5/9 - 11:38 chenglonghy  create.
 */
@Slf4j
@Service
public class PersistentTokenServiceImpl implements PersistentTokenRepository {

    @Resource
    PersistentLoginsDao persistentLoginsDao;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLoginsBo persistentLoginsBo = new PersistentLoginsBo(token);
        try {
            int saveResp = persistentLoginsDao.saveInfo(persistentLoginsBo);
            if (saveResp == 1) {
                log.debug("数据库插入");
            } else if (saveResp == 2) {
                log.debug("数据库更新");
            } else {
                log.error("数据库操作失败");
            }
        } catch (SQLException e) {
            log.error("sql异常");
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        try {
            persistentLoginsDao.updateInfo(series, tokenValue, lastUsed);
            log.debug("数据更新成功");
        } catch (SQLException e) {
            log.error("sql异常");
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        PersistentRememberMeToken token = null;
        try {
            PersistentLoginsBo persistentLoginsBo = persistentLoginsDao.selectInfo(seriesId);
            if (persistentLoginsBo == null) {
                log.debug("数据查询结果为null");
            } else {
                token = persistentLoginsBo.toToken(persistentLoginsBo);
                log.debug("数据查询成功，返回数据:" + token.getUsername());
            }
        } catch (SQLException e) {
            log.error("sql查询异常");
        }
        return token;
    }

    @Override
    public void removeUserTokens(String username) {
        try {
            persistentLoginsDao.removeInfoByUsername(username);
            log.debug("数据删除成功");
        } catch (SQLException e) {
            log.error("sql异常");
        }
    }
}
