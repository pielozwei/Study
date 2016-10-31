/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentBOMDetail;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;
import com.thinkgem.jeesite.modules.ip.service.EquipmentBOMSheetService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentBOMDetailService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;

/**
 * 设备BOM明细Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentBOMDetail")
public class EquipmentBOMDetailController extends BaseController {
	@Autowired
	private EquipmentBOMDetailService equipmentBOMDetailService;
	@Autowired
	private EquipmentBOMSheetService equipmentBOMSheetService;
	@Autowired
	private EquipmentModelService equipmentModelService;


	@ModelAttribute
	public EquipmentBOMDetail get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return equipmentBOMDetailService.get(id);
		} else {
			return new EquipmentBOMDetail();
		}
	}


	// @RequiresPermissions("ip:equipmentBOMDetail:view")
	@RequestMapping(value = { "list", "" })
	public String list(EquipmentBOMDetail equipmentBOMDetail, Map<String, Object> displayColumnsMap,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()) {
			equipmentBOMDetail.setCreateBy(user);
		}
		String equipmentBOMSheetId = request.getParameter("equipmentBOMSheetId");
		model.addAttribute("equipmentBOMSheetId", equipmentBOMSheetId);
		Page<EquipmentBOMDetail> paramPage = new Page<EquipmentBOMDetail>(request, response);
		Page<EquipmentBOMDetail> page = equipmentBOMDetailService.find(paramPage, equipmentBOMDetail,
				equipmentBOMSheetId);
		model.addAttribute("page", page);
		return "modules/" + "ip/equipmentBOMDetailList";
	}


	// @RequiresPermissions("ip:equipmentBOMDetail:view")
	@RequestMapping(value = "form")
	public String form(EquipmentBOMDetail equipmentBOMDetail, Model model, String sjbommxid,HttpServletRequest request, HttpServletResponse response) {
		String equipmentBOMSheetId = null;
		if (equipmentBOMDetail.getEquipmentBOMSheet() != null
				&& StringUtils.isNotBlank(equipmentBOMDetail.getEquipmentBOMSheet().getId())) {
			equipmentBOMSheetId = equipmentBOMDetail.getEquipmentBOMSheet().getId();
			equipmentBOMDetail.setEquipmentBOMSheet(equipmentBOMSheetService.get(equipmentBOMSheetId));
		}
		if (StringUtils.isBlank(equipmentBOMDetail.getId()) && StringUtils.isBlank(sjbommxid)) {// 添加给个编码
			model.addAttribute("sjbm", "0000");
		} else if (!StringUtils.isBlank(equipmentBOMDetail.getId())) {
			model.addAttribute("sjbm", equipmentBOMDetail.getSjbommxid());
		} else {
			model.addAttribute("sjbm", sjbommxid);
		}
		model.addAttribute("sjbommxid", sjbommxid);
		Page<EquipmentModel> paramPage = new Page<EquipmentModel>(request, response);
		Page<EquipmentModel> page = equipmentModelService.find(paramPage, new EquipmentModel(),
				null);
		model.addAttribute("page", page);
		model.addAttribute("equipmentBOMDetail", equipmentBOMDetail);
		return "modules/" + "ip/equipmentBOMDetailForm";
	}


	// @RequiresPermissions("ip:equipmentBOMDetail:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentBOMDetail equipmentBOMDetail, Model model, RedirectAttributes redirectAttributes) {
		String equipmentBOMSheetId = equipmentBOMDetail.getEquipmentBOMSheet() != null ? equipmentBOMDetail
				.getEquipmentBOMSheet()
				.getId() : "";
		if (!beanValidator(model, equipmentBOMDetail)) {
			return form(equipmentBOMDetail, model, "",null,null);
		}
		equipmentBOMDetailService.save(equipmentBOMDetail);
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentBOMDetail/?equipmentBOMSheetId="
				+ equipmentBOMSheetId;
	}


	/* 该节点是否有子节点 */
	public boolean hasChildNode(String nodeId) {
		boolean b = false;
		if (equipmentBOMDetailService.findSun(nodeId).size() > 0)
			b = true;
		return b;
	}


	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentBOMSheetId = equipmentBOMDetailService.get(ids[0]).getEquipmentBOMSheet().getId();
		for (String id : ids) {
			equipmentBOMDetailService.delete(id);
		}
		addMessage(redirectAttributes, "删除" + ids.length + "个设备BOM明细成功");
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentBOMDetail/?equipmentBOMSheetId="
				+ (equipmentBOMSheetId != null ? equipmentBOMSheetId : "");
	}


	// @RequiresPermissions("ip:equipmentBOMDetail:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(EquipmentBOMDetail equipmentBOMDetail, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentBOMSheetId = request.getParameter("equipmentBOMSheetId");
		try {
			String fileName = "设备BOM明细数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			Page<EquipmentBOMDetail> page = equipmentBOMDetailService.find(new Page<EquipmentBOMDetail>(request,
					response), equipmentBOMDetail, null);
			new ExportExcel("设备BOM明细数据", EquipmentBOMDetail.class)
					.setDataList(page.getList())
					.write(response, fileName)
					.dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出设备BOM明细失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentBOMDetail/?equipmentBOMSheetId="
				+ equipmentBOMSheetId;
	}
	/*
	 * @RequiresUser
	 * @ResponseBody
	 * @RequestMapping(value = "treeData") public List<Map<String, Object>> treeData(String module,
	 * HttpServletResponse response) { response.setContentType("application/json; charset=UTF-8");
	 * return equipmentBOMDetailService.getTreeData(); }
	 */
}
