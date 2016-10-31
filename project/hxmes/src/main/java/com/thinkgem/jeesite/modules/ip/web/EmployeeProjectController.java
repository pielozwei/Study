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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeProject;
import com.thinkgem.jeesite.modules.ip.service.EmployeeProjectService;

/**
 * 科研项目记录Controller
 * @author ls
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeeProject")
public class EmployeeProjectController extends BaseController {

	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	@ModelAttribute
	public EmployeeProject get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeProjectService.get(id);
		}else{
			return new EmployeeProject();
		}
	}
	
	@RequiresPermissions("ip:employeeProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeeProject employeeProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeeProject.setCreateBy(user);
		}
        Page<EmployeeProject> page = employeeProjectService.find(new Page<EmployeeProject>(request, response), employeeProject); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeeProjectList";
	}

	@RequiresPermissions("ip:employeeProject:view")
	@RequestMapping(value = "form")
	public String form(EmployeeProject employeeProject, Model model) {
		model.addAttribute("employeeProject", employeeProject);
		return "modules/" + "ip/employeeProjectForm";
	}

	@RequiresPermissions("ip:employeeProject:edit")
	@RequestMapping(value = "save")
	public String save(EmployeeProject employeeProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeeProject)){
			return form(employeeProject, model);
		}
		employeeProjectService.save(employeeProject);
		addMessage(redirectAttributes, "保存科研项目记录'" + employeeProject.getKyxmmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeProject/?repage";
	}
	
	@RequiresPermissions("ip:employeeProject:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeProjectService.delete(id);
		addMessage(redirectAttributes, "删除科研项目记录成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeProject/?repage";
	}
	
	@RequiresPermissions("ip:employeeProject:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeeProjectService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除科研项目记录成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeProject/?repage";
	}
}
