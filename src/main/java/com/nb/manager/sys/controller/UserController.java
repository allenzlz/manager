package com.nb.manager.sys.controller;

import com.nb.manager.core.entity.AiResult;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

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
        mav.setViewName("system/usermgr");
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
        mav.setViewName("system/addUser");
        return mav;
    }

    @RequestMapping("/system/getUserList")
    @ResponseBody
    public AiResult<List<SysUser>> getUserList() {
        System.out.println(new AiResult<>(0, "succ", userService.list(), userService.count()));
        return new AiResult<>(0, "succ", userService.list(), userService.count());}

    @RequestMapping("/system/addSysUser")
    @ResponseBody
    public int add(@RequestBody SysUser sysUser) {
        return userService.save(sysUser);
    }
}
