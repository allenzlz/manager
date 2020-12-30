package com.nb.manager.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.entity.UserTrajectory;
import com.nb.manager.sys.mapper.UserMapper;
import com.nb.manager.sys.mapper.UserTrajectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrajectoryService {

    @Autowired
    private UserTrajectoryMapper userTrajectoryMapper;


    public List<UserTrajectory> list() {
        List<UserTrajectory> list = null;
        try {
            list = userTrajectoryMapper.selectList(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public int save(UserTrajectory userTrajectory) {
        int result = 0;
        try {
            if (null != userTrajectory) {
                //log.debug(sysUser.toString());
                result = userTrajectoryMapper.insert(userTrajectory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


}
