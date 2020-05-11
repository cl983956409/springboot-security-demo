package com.security.demo.dao.user;

import com.security.demo.bean.user.PersistentLoginsBo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.Date;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/5/7 - 15:15
 * @history 2020/5/7 - 15:15 chenglonghy  create.
 */
public interface PersistentLoginsDao {

    /**
     * 插入新数据记录
     *
     * @param persistentLoginsBo
     * @return
     * @throws SQLException
     */
    int insertInfo(PersistentLoginsBo persistentLoginsBo) throws SQLException;

    /**
     * 根据series 更新 token和date
     * todo 多参数，要加@param注解
     *
     * @param series
     * @param token
     * @param date
     * @return
     * @throws SQLException
     */
    int updateInfo(@Param("series") String series, @Param("token") String token, @Param("date") Date date) throws SQLException;

    /**
     * 根据 username 删除记录
     *
     * @param username
     * @return
     * @throws SQLException
     */
    int removeInfoByUsername(String username) throws SQLException;

    /**
     * 根据series 查询详情
     *
     * @param series
     * @return
     * @throws SQLException
     */
    PersistentLoginsBo selectInfo(String series) throws SQLException;

    /**
     * todo 知识点
     * 不存在则插入，存在则更新 on duplicate key update
     *
     * @param persistentLoginsBo
     * @return
     * @throws SQLException
     */
    int saveInfo(PersistentLoginsBo persistentLoginsBo) throws SQLException;

}
