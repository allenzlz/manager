package com.nb.manager.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色，权限菜单映射
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
public class SysRoleMenu {
    private String roleId;
    private int menuId;
}
