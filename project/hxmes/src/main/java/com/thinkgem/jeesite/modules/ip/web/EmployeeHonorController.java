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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeHonor;
import com.thinkgem.jeesite.modules.ip.service.EmployeeHonorService;

/**
 * 荣誉称号记录Controller
 * @author lzq
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeeHonor")
public class EmployeeHonorController extends BaseController {

	@Autowired
	private EmployeeHonorService employeeHonorService;
	
	@ModelAttribute
	public EmployeeHonor get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeHonorService.get(id);
		}else{
			return new EmployeeHonor();
		}
	}
	
	@RequiresPermissions("ip:employeeHonor:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeeHonor employeeHonor, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeeHonor.setCreateBy(user);
		}
        Page<EmployeeHonor> page = employeeHonorService.find(new Page<EmployeeHonor>(request, response), employeeHonor); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeeHonorList";
	}

	@RequiresPermissions("ip:employeeHonor:view")
	@RequestMapping(value = "form")
	public String form(EmployeeHonor employeeHonor, Model model) {
		model.addAttribute("employeeHonor", employeeHonor);
		return "modules/" + "ip/employeeHonorForm";
	}

	@RequiresPermissions("ip:employeeHonor:edit")
	@RequestMapping(value = "save")
	public String save(EmployeeHonor employeeHonor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeeHonor)){
			return form(employeeHonor, model);
		}
		employeeHonorService.save(employeeHonor);
		addMessage(redirectAttributes, "保存荣誉称号记录'" + employeeHonor.getMc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeHonor/?repage";
	}
	
	@RequiresPermissions("ip:employeeHonor:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeHonorService.delete(id);
		addMessage(redirectAttributes, "删除荣誉称号记录成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeHonor/?repage";
	}
	
	@RequiresPermissions("ip:employeeHonor:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeeHonorService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除荣誉称号记录成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeHonor/?repage";
	}

}
