/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentCategory;
import com.thinkgem.jeesite.modules.ip.entity.Provider;
import com.thinkgem.jeesite.modules.ip.service.ProviderService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 供应商基本信息Controller
 * @author ZhangHD
 * @version 2016-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/provider")
public class ProviderController extends BaseController {
	@Autowired
	private ProviderService providerService;

	@ModelAttribute
	public Provider get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return providerService.get(id);
		} else {
			return new Provider();
		}
	}
	@RequiresPermissions("ip:provider:view")
	@RequestMapping(value = { "list", "" })
	public String list(Provider provider, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			provider.setCreateBy(user);
		}
		String module = request.getParameter("module");
		if (StringUtils.isEmpty(module)) {
			module = "provider";
		}
		Page<Provider> page = providerService.find(new Page<Provider>(request, response), provider);
		model.addAttribute("page", page);
		model.addAttribute("module", module);
		return "modules/" + "ip/providerList";
	}
	@RequiresPermissions("ip:provider:view")
	@RequestMapping(value = "form")
	public String form(Provider provider, Model model) {
		model.addAttribute("provider", provider);
		return "modules/" + "ip/providerForm";
	}
	@RequiresPermissions("ip:provider:edit")
	@RequestMapping(value = "save")
	public String save(Provider provider, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, provider)) {
			return form(provider, model);
		}
		providerService.save(provider);
		addMessage(redirectAttributes, "保存供应商基本信息'" + provider.getGysmc() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/ip/provider/?repage";
	}
	@RequiresPermissions("ip:provider:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		for (String id : ids) {
			providerService.delete(id);
		}
		addMessage(redirectAttributes, "删除供应商基本信息成功");
		return "redirect:" + Global.getAdminPath() + "/ip/provider/?repage";
	}
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String module, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Provider> list = providerService.find(new Page<Provider>(), new Provider()).getList();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Provider p : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", p.getId());
			map.put("name", p.getGysmc());
			map.put("module", "provider");
			listMap.add(map);
		}
		return listMap;
	}
	@ResponseBody
	@RequestMapping("checkGysbm")
	public String checkGysbm(String gysbm,String oldGysbm) {
		if (gysbm != null && oldGysbm.equals(gysbm)) {
			return "true";
		}else if (gysbm != null && providerService.findByGysbm(gysbm) == null) {
			return "true";
		}
		return "false";
	}
	@RequiresPermissions("ip:equipmentCategory:edit")
	@RequestMapping(value = "updateState")
	public String updateState(String[] ids, RedirectAttributes redirectAttributes, String state) {
		for (String id : ids) {
			Provider provider = providerService.get(id);
			providerService.updateState(provider, state);
		}
		addMessage(redirectAttributes, ids.length + "个设备分类状态修改成功");
		return "redirect:" + Global.getAdminPath() + "/ip/provider/";
	}
}
