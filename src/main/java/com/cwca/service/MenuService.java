package com.cwca.service;

import com.cwca.bean.Menu;
import com.cwca.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sang on 2017/12/28.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    public List<Menu> getMenusByHrId(Integer id) {

        return menuMapper.getMenusByHrId(id);
    }

    public List<Menu> menuTree() {
        return menuMapper.menuTree();
    }


    public List<Integer> getMenusByRid(Integer rid) {
        return menuMapper.getMenusByRid(rid);
    }
}
