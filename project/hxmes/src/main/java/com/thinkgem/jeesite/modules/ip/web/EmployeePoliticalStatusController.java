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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePoliticalStatus;
import com.thinkgem.jeesite.modules.ip.service.EmployeePoliticalStatusService;

/**
 * 政治面貌经历Controller
 * @author ks
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeePoliticalStatus")
public class EmployeePoliticalStatusController extends BaseController {

	@Autowired
	private EmployeePoliticalStatusService employeePoliticalStatusService;
	
	@ModelAttribute
	public EmployeePoliticalStatus get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeePoliticalStatusService.get(id);
		}else{
			return new EmployeePoliticalStatus();
		}
	}
	
	@RequiresPermissions("ip:employeePoliticalStatus:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeePoliticalStatus employeePoliticalStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeePoliticalStatus.setCreateBy(user);
		}
        Page<EmployeePoliticalStatus> page = employeePoliticalStatusService.find(new Page<EmployeePoliticalStatus>(request, response), employeePoliticalStatus); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeePoliticalStatusList";
	}

	@RequiresPermissions("ip:employeePoliticalStatus:view")
	@RequestMapping(value = "form")
	public String form(EmployeePoliticalStatus employeePoliticalStatus, Model model) {
		model.addAttribute("employeePoliticalStatus", employeePoliticalStatus);
		return "modules/" + "ip/employeePoliticalStatusForm";
	}

	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "save")
	public String save(EmployeePoliticalStatus employeePoliticalStatus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeePoliticalStatus)){
			return form(employeePoliticalStatus, model);
		}
		int a = employeePoliticalStatusService.getlist(employeePoliticalStatus);
		if (a==0){
			addMessage(redirectAttributes, "保存个人信息失败，工号重复");

		}
		else{
		employeePoliticalStatusService.save(employeePoliticalStatus);
		addMessage(redirectAttributes, "保存政治面貌经历'" + employeePoliticalStatus.getZzmm() + "'成功");
		}
		return "redirect:"+Global.getAdminPath()+"/ip/employeePoliticalStatus/?repage";
	}
	
	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeePoliticalStatusService.delete(id);
		addMessage(redirectAttributes, "删除政治面貌经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePoliticalStatus/?repage";
	}

	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeePoliticalStatusService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除政治面貌经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePoliticalStatus/?repage";
	}
}
