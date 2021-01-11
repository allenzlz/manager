package com.nb.manager.sys.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nb.manager.sys.entity.SysRole;
import com.nb.manager.sys.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<SysRole> getList() {
        QueryWrapper<SysRole> qw = new QueryWrapper<>();
        qw.eq("del_flag", 1);
        return roleMapper.selectList(qw);
    }

    public int saveRole(SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        sysRole.setModifyTime(new Date());
        sysRole.setDelFlag(1);
        return roleMapper.insert(sysRole);
    }

    public int delRole(int id) {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole.setDelFlag(0);
        return roleMapper.updateById(sysRole);
    }

    /**
     * jsonData中包含权限和角色名称
     *
     * @param jsonData
     * @return
     */
    public int userRole(String jsonData) {
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        JSONArray tree = jsonObject.getJSONArray("tree");
        for (int i = 0; i < tree.size(); i++) {
            JSONObject jsonObject1 = tree.getJSONObject(i);
            String id = jsonObject1.getString("id");
            jsonObject1.getJSONArray("");
        }
        return 1;
    }

}
