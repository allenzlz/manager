package com.nb.manager.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class MainService {
    @Autowired
    private MenuMapper menuMapper;

    public List<SysMenu> getMenuList(Collection<? extends GrantedAuthority> authorities) {
        if (null != authorities && authorities.size() > 0) {
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                index++;
                GrantedAuthority next = iterator.next();
                String authority = next.getAuthority();
                if (index == authorities.size()) {
                    queryWrapper.eq("PERMISSION", authority);
                } else {
                    queryWrapper.eq("PERMISSION", authority).or();
                }

            }
            queryWrapper.orderByAsc("sort");
            return menuMapper.selectList(queryWrapper);
        } else {
            return null;
        }

    }
}
