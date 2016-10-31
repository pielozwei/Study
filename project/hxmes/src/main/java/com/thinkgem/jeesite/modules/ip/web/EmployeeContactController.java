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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeContact;
import com.thinkgem.jeesite.modules.ip.service.EmployeeContactService;

/**
 * 个人信息Controller
 * @author cml
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeeContact")
public class EmployeeContactController extends BaseController {

	@Autowired
	private EmployeeContactService employeeContactService;
	
	@ModelAttribute
	public EmployeeContact get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeContactService.get(id);
		}else{
			return new EmployeeContact();
		}
	}
	
	@RequiresPermissions("ip:employeeContact:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeeContact employeeContact, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeeContact.setCreateBy(user);
		}
        Page<EmployeeContact> page = employeeContactService.find(new Page<EmployeeContact>(request, response), employeeContact); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeeContactList";
	}

	@RequiresPermissions("ip:employeeContact:view")
	@RequestMapping(value = "form")
	public String form(EmployeeContact employeeContact, Model model) {
		model.addAttribute("employeeContact", employeeContact);
		return "modules/" + "ip/employeeContactForm";
	}

	@RequiresPermissions("ip:employeeContact:edit")
	@RequestMapping(value = "save")
	public String save(EmployeeContact employeeContact, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeeContact)){
			return form(employeeContact, model);
		}
		employeeContactService.save(employeeContact);
		addMessage(redirectAttributes, "保存个人信息'" + employeeContact.getEmployee().getXm() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeContact/?repage";
	}
	
	@RequiresPermissions("ip:employeeContact:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeContactService.delete(id);
		addMessage(redirectAttributes, "删除个人信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeContact/?repage";
	}

}
