/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.ip.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.service.MaterialCategoryService;

/**
 * 内容管理Controller
 * 
 * @author ThinkGem
 * @version 2013-4-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ip")
public class materialIndexController extends BaseController {

	@Autowired
	private MaterialCategoryService wllbService;

	@RequiresPermissions("wuliao:view")
	@RequestMapping(value = "")
	public String index(Model model, String module) {
		if (StringUtils.isEmpty(module)) {
			module = "wlbm";
		}
		model.addAttribute("module", module);
		return "modules/ip/materialIndex";
	}

	@RequiresPermissions("wuliao:view")
	@RequestMapping(value = "tree")
	public String tree(Model model, String module) {
		if (StringUtils.isEmpty(module)) {
			module = "wlbm";
		}
		model.addAttribute("_module", module);
		model.addAttribute("module",get_module(module));
		model.addAttribute("wllbList", wllbService.findByUser(true, null));
		return "modules/ip/materialTree";
	}

	@RequiresPermissions("wuliao:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/ip/materialNone";
	}
	
	public static String get_module(String module){
		if(!module.equals("wllb")){
			return "wlbm";
		}else{
			return module;
		}
	}
}
