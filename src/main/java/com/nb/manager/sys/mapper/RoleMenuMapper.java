package com.nb.manager.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysRole;
import com.nb.manager.sys.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
