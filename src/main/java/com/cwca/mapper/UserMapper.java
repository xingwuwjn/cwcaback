package com.cwca.mapper;

import com.cwca.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户dao
 */
public interface UserMapper {
    User loadUserByUsername(String username);

    User dbUser(@Param("username") String username, @Param("password") String password);


    int hrReg(@Param("username") String username, @Param("password") String password);

    List<User> getHrsByKeywords(@Param("keywords") String keywords);

    int updateHr(User user);

    int deleteRoleByHrId(Integer hrId);

    int addRolesForHr(@Param("hrId") Integer hrId, @Param("rids") Integer[] rids);

    User getHrById(Integer hrId);

    int deleteHr(Integer hrId);

    List<User> getAllHr(@Param("currentId") Integer currentId);

    List<User> getAllHr();
}
