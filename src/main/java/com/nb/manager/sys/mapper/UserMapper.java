package com.nb.manager.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<SysUser> {

    @Select("select PERMISSION from SYS_MENU where id in\n" +
            "    (select MENU_ID from SYS_ROLE_MENU where ROLE_ID in\n" +
            "        (select id from SYS_ROLE  where id in\n" +
            "            (select ROLE_ID from SYS_USER_ROLE where USER_ID in\n" +
            "                (select id from SYS_USER where LOGIN_NAME = #{userId}))))")
    public List<String> getRolesByUsername(String username);
}
