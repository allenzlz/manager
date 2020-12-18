package com.nb.manager.sys.service;

import com.nb.manager.sys.entity.SysRole;
import com.nb.manager.sys.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<SysRole> getList() {
        return roleMapper.selectList(null);
    }
}
