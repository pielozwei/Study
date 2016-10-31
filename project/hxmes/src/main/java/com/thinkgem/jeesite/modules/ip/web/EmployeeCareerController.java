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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeCareer;
import com.thinkgem.jeesite.modules.ip.service.EmployeeCareerService;

/**
 * 工作经历Controller
 * @author cml
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeeCareer")
public class EmployeeCareerController extends BaseController {

	@Autowired
	private EmployeeCareerService employeeCareerService;
	
	@ModelAttribute
	public EmployeeCareer get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeCareerService.get(id);
		}else{
			return new EmployeeCareer();
		}
	}
	
	@RequiresPermissions("ip:employeeCareer:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeeCareer employeeCareer, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeeCareer.setCreateBy(user);
		}
        Page<EmployeeCareer> page = employeeCareerService.find(new Page<EmployeeCareer>(request, response), employeeCareer); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeeCareerList";
	}

	@RequiresPermissions("ip:employeeCareer:view")
	@RequestMapping(value = "form")
	public String form(EmployeeCareer employeeCareer, Model model) {
		model.addAttribute("employeeCareer", employeeCareer);
		return "modules/" + "ip/employeeCareerForm";
	}

	@RequiresPermissions("ip:employeeCareer:edit")
	@RequestMapping(value = "save")
	public String save(EmployeeCareer employeeCareer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeeCareer)){
			return form(employeeCareer, model);
		}
		employeeCareerService.save(employeeCareer);
		addMessage(redirectAttributes, "保存工作经历'" + employeeCareer.getZw() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeCareer/?repage";
	}
	
	@RequiresPermissions("ip:employeeCareer:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeCareerService.delete(id);
		addMessage(redirectAttributes, "删除工作经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeCareer/?repage";
	}
	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeeCareerService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除政治面貌经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePoliticalStatus/?repage";
	}

}
