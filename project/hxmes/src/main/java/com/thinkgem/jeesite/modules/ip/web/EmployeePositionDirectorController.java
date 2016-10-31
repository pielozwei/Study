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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePositionDirector;
import com.thinkgem.jeesite.modules.ip.service.EmployeePositionDirectorService;
import com.thinkgem.jeesite.modules.ip.service.EmployeeService;
import com.thinkgem.jeesite.modules.ip.service.OrganizationPositionRankService;
import com.thinkgem.jeesite.modules.ip.service.OrganizationPositionService;

/**
 * 人员岗位设置Controller
 * @author yrd
 * @version 2016-09-01
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeePositionDirector")
public class EmployeePositionDirectorController extends BaseController {

	@Autowired
	private EmployeePositionDirectorService employeePositionDirectorService;
	@Autowired
	private EmployeeService employeeservice;
	@Autowired
	private OrganizationPositionService organizationpositionservice;
	@Autowired
	private OrganizationPositionRankService organizationpositionrankservice;
	
	@ModelAttribute
	public EmployeePositionDirector get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeePositionDirectorService.get(id);
		}else{
			return new EmployeePositionDirector();
		}
	}
	
	@RequiresPermissions("ip:employeePositionDirector:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeePositionDirector employeePositionDirector, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeePositionDirector.setCreateBy(user);
		}
        Page<EmployeePositionDirector> page = employeePositionDirectorService.find(new Page<EmployeePositionDirector>(request, response), employeePositionDirector); 
        model.addAttribute("organizationpositionlist", organizationpositionservice.findAll());
        model.addAttribute("page", page);
		return "modules/" + "ip/employeePositionDirectorList";
	}

	@RequiresPermissions("ip:employeePositionDirector:view")
	@RequestMapping(value = "form")
	public String form(EmployeePositionDirector employeePositionDirector, Model model) {
		model.addAttribute("organizationpositionlist", organizationpositionservice.findAll());
		model.addAttribute("ryywlist",organizationpositionrankservice.findAll());
		model.addAttribute("employeelist", employeeservice.findAll());
		model.addAttribute("employeePositionDirector", employeePositionDirector);
		return "modules/" + "ip/employeePositionDirectorForm";
	}

	@RequiresPermissions("ip:employeePositionDirector:edit")
	@RequestMapping(value = "save")
	public String save(EmployeePositionDirector employeePositionDirector, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeePositionDirector)){
			return form(employeePositionDirector, model);
		}
		
		String zgid[] = employeePositionDirector.getEmployee().getId().split(",");
		String gwid = employeePositionDirector.getOrganizationposition().getId();
		//1、先删除同样存在的用户的岗位
//		for(int j=0;j<zgid.length;j++){
//			employeePositionDirectorService.deletezgBygwid(zgid[j],gwid);
//		}
		//2、给岗位批量添加用户
		for(int i=0;i<zgid.length;i++){
			EmployeePositionDirector ED = new EmployeePositionDirector();
			ED.setEmployee(employeeservice.get(zgid[i]));
			ED.setOrganizationposition(organizationpositionservice.get(gwid));
			employeePositionDirectorService.save(ED);
		}
		addMessage(redirectAttributes, "保存人员岗位设置成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePositionDirector/?repage";
	}
	
	@RequiresPermissions("ip:employeePositionDirector:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeePositionDirectorService.delete(id);
		addMessage(redirectAttributes, "删除人员岗位设置成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePositionDirector/?repage";
	}

}
