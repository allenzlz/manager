package com.nb.manager.sys.controller;

import com.nb.manager.core.entity.AiResult;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.entity.SysRole;
import com.nb.manager.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Allen.zheng
 */
@RestController
@RequestMapping("/system/")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 调转权限页面
     *
     * @return
     */
    @GetMapping("rolemgr")
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
    @GetMapping("role/load")
    public AiResult<List<SysRole>> role_load() {
        return new AiResult<>(0, "", roleService.getList(),1);
    }

}
