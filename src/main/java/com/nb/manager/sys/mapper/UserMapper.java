package com.nb.manager.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert  into sys_user (REAL_NAME,LOGIN_NAME,PASSWORD,SEX,PORTRAIT,PHONE_NUMBER,EMAIL,BIRTHDAY," +
            "DEPARTMENT_ID,POSITION_ID,POST_ID,CITY,STATE,LOGIN_COUNT,LOGIN_IP,LOGIN_TIME," +
            "CREATOR_ID,CREATE_TIME,EDITOR_ID,UPDATE_TIME,REMARK) values (#{realName},#{loginName},#{password},#{sex}" +
            ",#{portrait},#{phoneNumber},#{email},#{birthday},#{departmentId},#{positionId},#{postId},#{city},#{state}," +
            "#{loginCount},#{loginIp},#{loginTime},#{creatorId},#{createTime},#{editorId},#{updateTime},#{remark})")
    int insertSysUser(SysUser sysUser);

    @Select("select su.*,GROUP_CONCAT(sr.ID) user_role,GROUP_CONCAT(sr.ROLE_NAME) user_role_name from sys_user su,sys_role sr,sys_user_role sur\n" +
            "where su.ID = sur.USER_ID\n" +
            "and sr.ID = sur.ROLE_ID\n" +
            "group by su.ID")
    public List<SysUser> selectUserList(Page page);

    @Select("select su.*,GROUP_CONCAT(sr.ID) user_role,GROUP_CONCAT(sr.ROLE_NAME) user_role_name from sys_user su,sys_role sr,sys_user_role sur\n" +
            "where su.ID = sur.USER_ID\n" +
            "and sr.ID = sur.ROLE_ID\n" +
            "and su.REAL_NAME = #{userName}\n" +
            "group by su.ID")
    public List<SysUser> selectUserListByUserName(Page page,String userName);

}
