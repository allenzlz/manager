package com.nb.manager.sys.controller;

import com.nb.manager.core.entity.AiResult;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysRole;
import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Allen.zheng
 */
@RequestMapping("/system/role/")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 调转权限页面
     *
     * @return
     */
    @GetMapping("main")
    public ModelAndView role() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/rolemgr");
        return mav;
    }

    /**
     * 加载权限列表
     *
     * @return
     */
    @GetMapping("load")
    public AiResult<List<SysRole>> role_load() {
        return new AiResult<>(0, "", roleService.getList(), 1);
    }

    @RequestMapping("del/id/{id}")
    public int delRole(@PathVariable("id") String id) {
        return roleService.delRole(id);
    }

    @PostMapping("userRole")
    public int addUserRoles(@RequestBody String jsonData) {
        return roleService.userRole(jsonData);
    }
}



