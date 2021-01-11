package com.nb.manager.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源菜单
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu implements Serializable {
    private int id;
    private int pid;//父节点Id
    private String name;
    private int type;//菜单类型
    private String icon;
    private String url;
    private String permission;//权限
    private int isShow;//是否展示  1-展示   0-不展示
    private int sort;//展示顺序
    private Date createTime;
    private Date modifyTime;
    private int delFlag;//假删除
    private String createUser;

}
