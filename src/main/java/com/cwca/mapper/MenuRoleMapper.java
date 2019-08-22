package com.cwca.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by sang on 2018/1/2.
 */
public interface MenuRoleMapper {
    int deleteMenuByRid(@Param("rid") Integer rid);

    int addMenu(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
