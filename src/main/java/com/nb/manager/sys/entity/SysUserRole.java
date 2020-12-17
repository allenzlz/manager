package com.nb.manager.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户和角色映射表,一个用户可能会有多个角色
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
public class SysUserRole {
    private String userId;
    private String roleId;
}
