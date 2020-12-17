package com.nb.manager.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements UserDetails {
    private String id;
    private String realName;
    private String loginName;
    private String password;
    private String sex;
    private String portrait;//头像
    private String phoneNumber;
    private String email;
    private Date birthday;
    private String departmentId;//部门id
    private String positionId;//职位id
    private String postId;//岗位id
    private String city;//城市
    private int state;//用户状态
    private int loginCount;//登录次数
    private String loginIp;//登录ip
    private String loginTime;//最近一次登录时间
    private String creatorId;//添加此用户的人的id
    private Date createTime;//创建时间
    private String editorId;//更新此用户信息的人的id
    private Date updateTime;//更新用户信息时间
    private String remark;//备注，暂时无用

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
