package com.nb.manager.sys.controller;

import com.nb.manager.sys.entity.SysUser;
import com.nb.manager.sys.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/main")
    public String data(Model model) {
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = sysUser.getAuthorities();
        model.addAttribute("sysMenuList", mainService.getMenuList(authorities));
        return "main";
    }

    @RequestMapping("/test")
    public String test() {
        return "test1";
    }

}
