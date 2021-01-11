package com.nb.manager.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysRoleMenu;
import com.nb.manager.sys.mapper.MenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nb.manager.sys.mapper.RoleMapper;
import com.nb.manager.sys.mapper.RoleMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

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

    public JSONArray ListHandLer() {
        JSONArray jsonA = new JSONArray();
        try {
            List<SysMenu> list = menuMapper.selectList(null);
            if (null != list && list.size() > 0) {
                for (SysMenu sysMenu : list) {
                    JSONObject json0 = new JSONObject();
                    if (sysMenu.getPid() == -1) {
                        json0.put("id", sysMenu.getId());
                        json0.put("title", sysMenu.getName());
                        JSONArray children = new JSONArray();
                        for (SysMenu sysMenu2 : list) {
                            if (sysMenu2.getPid() != -1 && sysMenu.getId() == sysMenu2.getPid()) {
                                JSONObject json1 = new JSONObject();
                                json1.put("id", sysMenu2.getId());
                                json1.put("title", sysMenu2.getName());
                                children.add(json1);
                            }
                        }
                        json0.put("children", children);
                        jsonA.add(json0);
                    }
                }
            }
            return jsonA;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray ListHandLer(int id) {
        JSONArray jsonA = new JSONArray();
        try {
            //拿到此权限对应的菜单
            QueryWrapper<SysRoleMenu> qw = new QueryWrapper<>();
            qw.eq("ROLE_ID", id);
            List<SysRoleMenu> sysRoleMenus = roleMenuMapper.selectList(qw);
            //根据权限对应的菜单id，设置菜单是选中状态
            List<SysMenu> list = menuMapper.selectList(null);
            if (null != list && list.size() > 0) {
                for (SysMenu sysMenu : list) {
                    JSONObject json0 = new JSONObject();
                    if (sysMenu.getPid() == -1) {
                        json0.put("id", sysMenu.getId());
                        json0.put("title", sysMenu.getName());
                        json0.put("checked", ischecked(sysRoleMenus, sysMenu.getId()));
                        JSONArray children = new JSONArray();
                        for (SysMenu sysMenu2 : list) {
                            if (sysMenu2.getPid() != -1 && sysMenu.getId() == sysMenu2.getPid()) {
                                JSONObject json1 = new JSONObject();
                                json1.put("id", sysMenu2.getId());
                                json1.put("title", sysMenu2.getName());
                                json1.put("checked", ischecked(sysRoleMenus, sysMenu2.getId()));
                                children.add(json1);
                            }
                        }
                        json0.put("children", children);
                        jsonA.add(json0);
                    }
                }
            }
            return jsonA;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean ischecked(List<SysRoleMenu> list, int menuId) {
        for (SysRoleMenu sysRoleMenu : list) {
            if (sysRoleMenu.getMenuId() == menuId) {
                return true;
            }
        }
        return false;
    }
}
