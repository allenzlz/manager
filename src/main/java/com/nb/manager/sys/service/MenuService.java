package com.nb.manager.sys.service;

import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.mapper.MenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public int save(SysMenu sysMenu) {
        int result = 0;
        try {
            if (null != sysMenu) {
                log.debug(sysMenu.toString());
                int id = sysMenu.getId();
                SysMenu sysM = menuMapper.selectById(id);
                if (null == sysM) {
                    result = menuMapper.insert(sysMenu);
                } else {
                    result = menuMapper.updateById(sysMenu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public List<SysMenu> list() {
        List<SysMenu> list = null;
        try {
            list = menuMapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public int count() {
        int count = 0;
        try {
            count = menuMapper.selectCount(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }

    public int delById(int id) {
        int result = 0;
        try {
            result = menuMapper.deleteById(id);
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", id);
            menuMapper.delete(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int checkExistById(int id) {
        int result = 0;
        try {
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", id);
            List<SysMenu> sysMenus = menuMapper.selectList(queryWrapper);
            result = sysMenus.size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
