package com.nb.manager.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("LOGIN_NAME", username);
        SysUser user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户!");
        }
        return user;
    }

    /**
     * 用户权限集合
     */
    public List<String> getRolesByUsername(String username) {
        List<String> list = new ArrayList<>();
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("LOGIN_NAME", username);
        SysUser user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户!");
        } else {
            list = userMapper.getRolesByUsername(username);
        }
        return list;
    }

    public int count() {
        int count = 0;
        try {
            count = userMapper.selectCount(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return count;
        }
    }

    public List<SysUser> list() {
        List<SysUser> list = null;
        try {
            list = userMapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public List<SysUser> selectUserListPage(Page page) {
        List<SysUser> list = null;
        try {
            list = userMapper.selectUserList(page);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public List<SysUser> selectUserListByUserName(Page page,String userName) {
        List<SysUser> list = null;
        try {
            list = userMapper.selectUserListByUserName(page,userName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public IPage<SysUser> selectUserList(Page page) {
        IPage<SysUser> list = null;
        try {
            list = userMapper.selectPage(page,null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public int save(SysUser sysUser) {
        int result = 0;
        try {
            if (null != sysUser) {
                //log.debug(sysUser.toString());
                result = userMapper.insertSysUser(sysUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int update(SysUser sysUser) {
        int result = 0;
        try {
            if (null != sysUser) {
                //log.debug(sysUser.toString());
                result = userMapper.updateById(sysUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int delById(int id) {
        int result = 0;
        try {
            result = userMapper.deleteById(id);
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            userMapper.delete(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
