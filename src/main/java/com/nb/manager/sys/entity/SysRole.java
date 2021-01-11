package com.nb.manager.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色
 */
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole implements GrantedAuthority, Serializable {
    private int id;
    private String roleName;
    private Date createTime;
    private Date modifyTime;
    private int delFlag;
    private String remark;

    @Override
    public String getAuthority() {
        return null;
    }
}
