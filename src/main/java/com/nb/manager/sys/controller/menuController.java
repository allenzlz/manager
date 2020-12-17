package com.nb.manager.sys.controller;

import com.nb.manager.core.entity.AiResult;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class menuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/system/list")
    public AiResult<List<SysMenu>> list() {
        return new AiResult<>(0, "succ", menuService.list(), menuService.count());
    }

    @PostMapping("/system/add")
    public int add(@RequestBody SysMenu sysMenu) {
        return menuService.save(sysMenu);
    }

    @DeleteMapping("/system/del/id/{id}")
    public int del(@PathVariable("id") int id) {
        return menuService.delById(id);
    }

    @GetMapping("/system/check/id/{id}")
    public int checkExistById(@PathVariable("id") int id) {
        return menuService.checkExistById(id);
    }

    @GetMapping("/system/menu")
    public ModelAndView menu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/menu");
        return mav;
    }

    @GetMapping("/system/addMenu/id/{id}")
    public ModelAndView addMenu(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pid", id);
        mav.setViewName("/system/addMenu");
        return mav;
    }
}
