package com.nb.manager.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.entity.SysUserRole;
import com.nb.manager.sys.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<SysUserRole> getList() {
        return userRoleMapper.selectList(null);
    }

    public int save(SysUserRole sysUserRole) {
        int result = 0;
        try {
            if (null != sysUserRole) {
                //log.debug(sysUser.toString());
                result = userRoleMapper.insert(sysUserRole);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public int delById(String id) {
        int result = 0;
        try {
            result = userRoleMapper.deleteById(id);
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", id);
            userRoleMapper.delete(queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
