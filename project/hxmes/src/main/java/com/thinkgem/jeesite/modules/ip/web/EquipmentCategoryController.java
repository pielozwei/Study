/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentCategory;
import com.thinkgem.jeesite.modules.ip.service.EquipmentCategoryService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 设备分类Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentCategory")
public class EquipmentCategoryController extends BaseController {
	@Autowired
	private EquipmentCategoryService equipmentCategoryService;


	@ModelAttribute
	public EquipmentCategory get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return equipmentCategoryService.get(id);
		} else {
			return new EquipmentCategory();
		}
	}


	@RequiresPermissions("ip:equipmentCategory:view")
	@RequestMapping(value = { "list", "" })
	public String list(EquipmentCategory equipmentCategory, Map<String, Object> displayColumnsMap,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			equipmentCategory.setCreateBy(user);
		}
		String nodeId = request.getParameter("nodeId");
		Page<EquipmentCategory> paramPage = new Page<EquipmentCategory>(request, response);
		Page<EquipmentCategory> page = equipmentCategoryService.find(paramPage, equipmentCategory, nodeId);
		model.addAttribute("page", page);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/equipmentCategoryList";
	}


	@RequiresPermissions("ip:equipmentCategory:view")
	@RequestMapping(value = "form")
	public String form(EquipmentCategory equipmentCategory, String nodeId, Model model) {
		if (StringUtils.isBlank(equipmentCategory.getId())) {// 添加给个编码
			List<EquipmentCategory> list = equipmentCategoryService.findAll();
			model.addAttribute("bm", list.size() + 1);
		}
		model.addAttribute("equipmentCategory", equipmentCategory);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/equipmentCategoryForm";
	}


	@RequiresPermissions("ip:equipmentCategory:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentCategory equipmentCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, equipmentCategory)) {
			return form(equipmentCategory, "1", model);
		}
		if (StringUtils.isNotBlank(equipmentCategory.getId()) && hasChildNode(equipmentCategory.getId())) {
			addMessage(redirectAttributes, "保存设备分类'" + equipmentCategory.getSblbmc() + "'失败：该节点有子节点不能修改！");
		}
		if (StringUtils.isNotBlank(equipmentCategory.getId())
				&& equipmentCategory.getId().equals(equipmentCategory.getParent().getId())) {// 父节点为自己
			addMessage(redirectAttributes, "保存设备分类'" + equipmentCategory.getSblbmc() + "'失败：不能选择自己作为父节点！");
		} else {
			equipmentCategoryService.save(equipmentCategory);
			addMessage(redirectAttributes, "保存设备分类'" + equipmentCategory.getSblbmc() + "'成功");
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentModel/none";
	}


	/* 该节点是否有子节点 */
	public boolean hasChildNode(String nodeId) {
		boolean b = false;
		if (equipmentCategoryService.findSun(nodeId).size() > 0)
			b = true;
		return b;
	}


	/* 该节点是否有信息关联 */
	public boolean hasInfo(String nodeId) {
		boolean b = false;
		if (equipmentCategoryService.findInfo(nodeId).size() > 0)
			b = true;
		return b;
	}


	@RequiresPermissions("ip:equipmentCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId = equipmentCategoryService.get(ids[0]).getParent().getId();
		for (String id : ids) {
			if (hasChildNode(id)) {
				addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
				return "redirect:" + Global.getAdminPath() + "/ip/equipmentCategory/?nodeId=" + nodeId;
			}
			// if (hasInfo(id)) {
			// addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
			// return "redirect:" + Global.getAdminPath() + "/ip/equipmentCategory/?nodeId=" +
			// nodeId;
			// }
			equipmentCategoryService.delete(id);
		}
		addMessage(redirectAttributes, "删除" + ids.length + "个设备分类成功");
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentModel/none";
	}


	@RequiresPermissions("ip:equipmentCategory:edit")
	@RequestMapping(value = "updateState")
	public String updateState(String[] ids, RedirectAttributes redirectAttributes, String state) {
		for (String id : ids) {
			EquipmentCategory equipmentCategory = equipmentCategoryService.get(id);
			equipmentCategoryService.updateState(equipmentCategory, state);
		}
		addMessage(redirectAttributes, ids.length + "个设备分类状态修改成功");
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentCategory/";
	}


	@RequiresPermissions("ip:equipmentCategory:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(EquipmentCategory equipmentCategory, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "设备分类数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<EquipmentCategory> page = equipmentCategoryService.find(
					new Page<EquipmentCategory>(request, response), equipmentCategory, null);
			new ExportExcel("设备分类数据", EquipmentCategory.class)
					.setDataList(page.getList())
					.write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出设备分类失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentCategory/";
	}


	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String module, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		return equipmentCategoryService.getTreeData();
	}


	@ResponseBody
	@RequestMapping("checkSblbbm")
	public String checkSblbbm(String sblbbm, String oldSblbbm) {
		if (sblbbm != null && oldSblbbm.equals(sblbbm)) {
			return "true";
		} else if (sblbbm != null && equipmentCategoryService.findBySblbbm(sblbbm) == null) {
			return "true";
		}
		return "false";
	}
}
