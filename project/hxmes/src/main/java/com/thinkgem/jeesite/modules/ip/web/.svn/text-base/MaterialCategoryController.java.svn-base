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
import com.thinkgem.jeesite.modules.ip.entity.MaterialCategory;
import com.thinkgem.jeesite.modules.ip.service.MaterialCategoryService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 物料编码信息Controller
 * @author ZhangHD
 * @version 2016-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/wllb")
public class MaterialCategoryController extends BaseController {
	@Autowired
	private MaterialCategoryService wllbService;


	@ModelAttribute
	public MaterialCategory get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return wllbService.get(id);
		} else {
			return new MaterialCategory();
		}
	}


	@RequiresPermissions("wuliao:wllb:view")
	@RequestMapping(value = { "list", "" })
	public String list(MaterialCategory wllb, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			wllb.setCreateBy(user);
		}
		String nodeId = request.getParameter("nodeId");
		Page<MaterialCategory> page = wllbService.findSuns(new Page<MaterialCategory>(request, response), wllb, nodeId);
		model.addAttribute("page", page);
		model.addAttribute("wllb", wllb);
		return "modules/" + "ip/materialCategoryList";
	}


	@RequiresPermissions("wuliao:wllb:view")
	@RequestMapping(value = "form")
	public String form(MaterialCategory wllb, String pId, String type, Model model) {
		model.addAttribute("wllb", wllb);
		model.addAttribute("pId", pId);
		model.addAttribute("type", type);
		return "modules/" + "ip/materialCategoryForm";
	}


	@RequiresPermissions("wuliao:wllb:edit")
	@RequestMapping(value = "save")
	public String save(MaterialCategory wllb, String type, Model model, RedirectAttributes redirectAttributes) {
		wllbService.save(wllb);
		String nodeId = wllb.getParent().getId();
		addMessage(redirectAttributes, "保存物料编码信息'" + wllb.getWllbmc() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/ip/wllb/?repage&nodeId=" + nodeId;
	}


	@RequiresPermissions("wuliao:wllb:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId = wllbService.get(ids[0]).getParent().getId();
		for (String id : ids) {
			try {
				wllbService.delete(id);
			} catch (Exception e) {
				String str = e.getCause().getCause().getMessage();
				String[] msg = str.split(":");
				if (msg[0].equals("ORA-02292")) {
					addMessage(redirectAttributes, "有子记录不能删除，请先删除子记录");
					return "redirect:" + Global.getAdminPath() + "/ip/wllb/?repage&nodeId=" + nodeId;
				}
			}
		}
		addMessage(redirectAttributes, "删除物料编码信息成功");
		return "redirect:" + Global.getAdminPath() + "/ip/wllb/?repage&nodeId=" + nodeId;
	}


	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String module, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<MaterialCategory> list = wllbService.findByUser(true, null);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (MaterialCategory wllb : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", wllb.getId());
			MaterialCategory p_wllb = wllb.getParent();
			map.put("pId", p_wllb == null ? '0' : p_wllb.getId());
			map.put("name", wllb.getWllbmc());
			map.put("module", "wllb");
			listMap.add(map);
		}
		return listMap;
	}


	@ResponseBody
	@RequestMapping("checkWllbbm")
	public String checkWllbbm(String wllbbm, String oldWllbbm) {
		if (wllbbm != null && oldWllbbm.equals(wllbbm)) {
			return "true";
		} else if (wllbbm != null && wllbService.findByWllb(wllbbm) == null) {
			System.out.println("123");
			return "true";
		}
		return "false";
	}


	@RequiresPermissions("wuliao:wllb:edit")
	@RequestMapping(value = "updateState")
	public String updateState(String[] ids, RedirectAttributes redirectAttributes, String state) {
		for (String id : ids) {
			MaterialCategory materialCategory = wllbService.get(id);
			wllbService.updateState(materialCategory, state);
		}
		addMessage(redirectAttributes, ids.length + "个物料类别状态修改成功");
		return "redirect:" + Global.getAdminPath() + "/ip/wllb/";
	}


	/* 该节点是否有子节点 */
	public boolean hasChildNode(String nodeId) {
		boolean b = false;
		if (wllbService.findSun(nodeId).size() > 0)
			b = true;
		return b;
	}
}
