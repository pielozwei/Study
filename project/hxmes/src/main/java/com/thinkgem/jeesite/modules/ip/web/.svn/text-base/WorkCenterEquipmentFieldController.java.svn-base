/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterEquipmentField;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterEquipmentFieldService;

/**
 * 工作中心设备Controller
 * @author lucl
 * @version 2016-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/gzzx/gzzxsb")
public class WorkCenterEquipmentFieldController extends BaseController {

	@Autowired
	private WorkCenterEquipmentFieldService gzzxsbService;
	
	@ModelAttribute
	public WorkCenterEquipmentField get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return gzzxsbService.get(id);
		}else{
			return new WorkCenterEquipmentField();
		}
	}
	
	@RequiresPermissions("gzzx:gzzxsb:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkCenterEquipmentField gzzxsb, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			gzzxsb.setCreateBy(user);
		}
        Page<WorkCenterEquipmentField> page = gzzxsbService.find(new Page<WorkCenterEquipmentField>(request, response), gzzxsb); 
        model.addAttribute("page", page);
		return "modules/" + "ip/workCenterEquipmentFieldList";
	}

	@RequiresPermissions("gzzx:gzzxsb:view")
	@RequestMapping(value = "form")
	public String form(WorkCenterEquipmentField gzzxsb, Model model) {
		model.addAttribute("gzzxsb", gzzxsb);
		return "modules/" + "ip/workCenterEquipmentFieldForm";
	}

	@RequiresPermissions("gzzx:gzzxsb:edit")
	@RequestMapping(value = "save")
	public String save(WorkCenterEquipmentField gzzxsb, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzzxsb)){
			return form(gzzxsb, model);
		}
		gzzxsbService.save(gzzxsb);
		addMessage(redirectAttributes, "保存工作中心设备'" + gzzxsb.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxsb/?repage";
	}
	
	@RequiresPermissions("gzzx:gzzxsb:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		gzzxsbService.delete(id);
		addMessage(redirectAttributes, "删除工作中心设备成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxsb/?repage";
	}

}
