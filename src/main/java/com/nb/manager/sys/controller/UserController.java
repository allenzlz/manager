package com.nb.manager.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nb.manager.core.entity.AiResult;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.entity.SysUserRole;
import com.nb.manager.sys.service.RoleService;
import com.nb.manager.sys.service.UserRoleService;
import com.nb.manager.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author OZY
 * @Date 2019/08/09 20:41
 * @Description
 * @Version V1.0
 **/
@Controller
@RequestMapping("/")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;
    /**
     * 登录页面跳转
     * @return
     */
    @GetMapping("login")
    public String login() {
        return "login.html";
    }



    /**
     * index页跳转
     * @return
     */
    @GetMapping("index")
    public String index() {
        return "index.html";
    }

    /**
     * menu1按钮
     * @return
     */
    @PreAuthorize("hasAuthority('menu1')")
    @GetMapping("menu1")
    @ResponseBody
    public String menu1() {
        return "menu1";
    }

    /**
     * menu2按钮
     * @return
     */
    @PreAuthorize("hasAuthority('menu2')")
    @GetMapping("menu2")
    @ResponseBody
    public String menu2() {
        return "menu2";
    }

    /**
     * menu3按钮
     * @return
     */
    @PreAuthorize("hasAuthority('menu3')")
    @GetMapping("menu3")
    @ResponseBody
    public String menu3() {
        return "menu3";
    }

    /**
     * 用户管理页面跳转
     * @return
     */
    @RequestMapping("/system/usermgr")
    @ResponseBody
    public ModelAndView usermgr() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/user/usermgr");
        return mav;
    }

    /**
     * 添加用户页面跳转
     * @return
     */
    @RequestMapping("/system/addUser")
    @ResponseBody
    public ModelAndView addUser() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/user/addUser");
        return mav;
    }

    /*@RequestMapping("/system/getUserList")
    @ResponseBody
    public AiResult<List<SysUser>> getUserList() {
        System.out.println(new AiResult<>(0, "succ", userService.selectUserList(), userService.count()));
        return new AiResult<>(0, "succ", userService.selectUserList(), userService.count());}*/

    @RequestMapping("/system/getUserList")
    @ResponseBody
    public AiResult<List<SysUser>> getUserList(Integer page,Integer limit) {
        Page p = new Page(page,limit);
        return new AiResult<>(0, "succ", userService.selectUserListPage(p), userService.count());
    }

    @RequestMapping("/system/selectUserListByUserName")
    @ResponseBody
    public AiResult<List<SysUser>> selectUserListByUserName(String userName,Integer page,Integer limit) {
        Page p = new Page(page,limit);
        return new AiResult<>(0, "succ", userService.selectUserListByUserName(p,userName), userService.count());
    }

    @RequestMapping("/system/addSysUser")
    @ResponseBody
    public int add(@RequestBody SysUser sysUser) {
        String userRoleId = sysUser.getUserRole();
        String[] userRoleIds = userRoleId.split(",");
        int r = userService.save(sysUser);
        for(String str : userRoleIds){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(str);
            sysUserRole.setUserId(String.valueOf(sysUser.getId()));
            userRoleService.save(sysUserRole);
        }
        return r;
    }

    @RequestMapping("/system/editSysUser")
    @ResponseBody
    public int edit(@RequestBody SysUser sysUser) {
        String userRoleId = sysUser.getUserRole();
        String[] userRoleIds = userRoleId.split(",");
        int r = userService.update(sysUser);
        userRoleService.delById(sysUser.getId());
        for(String str : userRoleIds){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(str);
            sysUserRole.setUserId(String.valueOf(sysUser.getId()));
            userRoleService.save(sysUserRole);
        }
        return r;
    }

    @RequestMapping("/system/delUser/{id}")
    @ResponseBody
    public int checkExistById(@PathVariable("id") int id) {
        userRoleService.delById(id);
        return userService.delById(id);
    }

    @RequestMapping("system/editUser")
    @ResponseBody
    public ModelAndView queryUserById() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/system/user/editUser");
        return mv;
    }
}
