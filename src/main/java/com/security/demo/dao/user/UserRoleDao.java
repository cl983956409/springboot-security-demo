package com.security.demo.dao.user;

import com.security.demo.bean.user.Role;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @date 2020/5/7 - 15:15
 * @history 2020/5/7 - 15:15 chenglonghy  create.
 */
public interface UserRoleDao {

    int insertUserRole(@Param("id") int id, @Param("role") String role) throws SQLException;

    List<Role> selectUserRoles(@Param("id") int id) throws SQLException;
}
