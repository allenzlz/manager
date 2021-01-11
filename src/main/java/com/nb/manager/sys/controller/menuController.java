package com.nb.manager.sys.controller;

import com.alibaba.fastjson.JSONArray;
import com.nb.manager.core.entity.AiResult;
import com.nb.manager.sys.entity.SysMenu;
import com.nb.manager.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/system/menu/")
public class menuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("list")
    public AiResult<List<SysMenu>> list() {
        return new AiResult<>(0, "succ", menuService.list(), menuService.count());
    }

    @RequestMapping("main")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/menu/list");
        return mav;
    }

    /**
     * 在权限模块使用，初始化加载全部菜单
     */
    @RequestMapping("load")
    public AiResult<JSONArray> load() {
        return new AiResult<JSONArray>(0, "succ", menuService.ListHandLer());
    }

    /**
     * 在权限模块使用，初始化加载全部菜单
     */
    @RequestMapping("load/id/{id}")
    public AiResult<JSONArray> loadByid(@PathVariable("id") int id) {
        return new AiResult<JSONArray>(0, "succ", menuService.ListHandLer(id));
    }

    @PostMapping("add")
    public int add(@RequestBody SysMenu sysMenu) {
        return menuService.save(sysMenu);
    }

    @DeleteMapping("del/id/{id}")
    public int del(@PathVariable("id") int id) {
        return menuService.delById(id);
    }

    @GetMapping("check/id/{id}")
    public int checkExistById(@PathVariable("id") int id) {
        return menuService.checkExistById(id);
    }

    @GetMapping("addMenu/id/{id}")
    public ModelAndView addMenu(@PathVariable("id") String id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pid", id);
        mav.setViewName("/system/menu/addMenu");
        return mav;
    }
}
