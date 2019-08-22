package com.cwca.mapper;

import com.cwca.bean.Menu;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
public interface MenuMapper {
    List<Menu> getAllMenu();

    List<Menu> getMenusByHrId(Integer hrId);

    List<Menu> menuTree();

    List<Integer> getMenusByRid(Integer rid);
}
