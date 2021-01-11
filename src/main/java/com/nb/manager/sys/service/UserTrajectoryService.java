package com.nb.manager.sys.service;

import com.nb.manager.sys.entity.UserTrajectory;
import com.nb.manager.sys.mapper.UserTrajectoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
