/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkgem.jeesite.modules.ip.common.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMDetail;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMSheet;
import com.thinkgem.jeesite.modules.ip.entity.Provider;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.MaterialService;
import com.thinkgem.jeesite.modules.ip.service.ProductBOMSheetService;
import com.thinkgem.jeesite.modules.ip.service.ProviderService;

/**
 * 集成公共接口
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping("${adminPath}/Integrate")
public class IntegratedDevelopmentController extends BaseController {
	@Autowired
	private EquipmentModelService equipmentModelService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private ProductBOMSheetService productBOMSheetService;


	/**************** 弹窗列表接口 start *******************/
	/**
	 * 获取设备规格型号
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("getEquipmentModel")
	public String getEquipmentModel(HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<EquipmentModel> paramPage = getParamPage(new EquipmentModel(), request, response, model);
		paramPage = equipmentModelService.find(paramPage, new EquipmentModel(), null);
		model.addAttribute("page", paramPage);
		getUrlParm(request, model);
		return "modules/sys/taglistselect";
	}


	/**
	 * 获取物料编码基本信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("getMaterial")
	public String getMaterial(HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Material> paramPage = getParamPage(new Material(), request, response, model);
		paramPage = materialService.find(paramPage, new Material());
		model.addAttribute("page", paramPage);
		getUrlParm(request, model);
		return "modules/sys/taglistselect";
	}
	/**
	 * 获取物料编码基本信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("getProvider")
	public String getProvider(HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Provider> paramPage = getParamPage(new Provider(), request, response, model);
		paramPage=providerService.find(paramPage, new Provider());
		model.addAttribute("page", paramPage);
		getUrlParm(request, model);
		return "modules/sys/taglistselect";
	}

	/**
	 * 获取弹窗url参数 放入model
	 * @Title: getUrlParm
	 * @Description: TODO
	 * @param request
	 * @param model
	 */
	public void getUrlParm(HttpServletRequest request, Model model) {
		try {
			model.addAttribute("itemNames", new String(request.getParameter("itemNames").getBytes("iso-8859-1"),
					"utf-8")); // 列表显示名称
			model.addAttribute("itemCodes", request.getParameter("itemCodes")); // 列表字段编码
			model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
			model.addAttribute("url", request.getParameter("url")); // 指定默认选中的ID
			model.addAttribute("pageSize", request.getParameter("pageSize")); // 指定默认选中的ID
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 创建paramPage 设置pageSize
	 * @param t
	 * @param request
	 * @param response
	 * @param model
	 * @return paramPage
	 */
	public <T> Page<T> getParamPage(T t, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<T> paramPage = new Page<T>(request, response);
		String pageSize = request.getParameter("pageSize"); // 列表字段编码
		if (StringUtils.isNotBlank(pageSize)) {
			paramPage.setPageSize(Integer.parseInt(pageSize));
		}
		return paramPage;
	}
	/**************** 弹窗列表接口 end *******************/
	/**************** 弹窗树接口 start *******************/
	/**************** 弹窗树接口 end *******************/
}
