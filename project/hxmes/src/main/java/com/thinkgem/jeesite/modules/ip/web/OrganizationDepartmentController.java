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
import com.thinkgem.jeesite.modules.ip.entity.OrganizationDepartment;
import com.thinkgem.jeesite.modules.ip.service.OrganizationDepartmentService;
import com.thinkgem.jeesite.modules.ip.service.OrganizationService;

/**
 * 组织机构部门Controller
 * @author xht
 * @version 2016-09-07
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/organizationDepartment")
public class OrganizationDepartmentController extends BaseController {

	@Autowired
	private OrganizationDepartmentService organizationDepartmentService;
	
	@Autowired
	private OrganizationService organizationService;
	
	@ModelAttribute
	public OrganizationDepartment get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return organizationDepartmentService.get(id);
		}else{
			return new OrganizationDepartment();
		}
	}
	
	@RequiresPermissions("ip:organizationDepartment:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrganizationDepartment organizationDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			organizationDepartment.setCreateBy(user);
		}
        Page<OrganizationDepartment> page = organizationDepartmentService.find(new Page<OrganizationDepartment>(request, response), organizationDepartment); 
        model.addAttribute("page", page);
		return "modules/" + "ip/organizationDepartmentList";
	}

	@RequiresPermissions("ip:organizationDepartment:view")
	@RequestMapping(value = "form")
	public String form(OrganizationDepartment organizationDepartment, Model model) {
		model.addAttribute("organizationDepartment", organizationDepartment);
		model.addAttribute("DeparmentList", organizationDepartmentService.findAll());
		model.addAttribute("SjjgList",organizationService.findAll());
		return "modules/" + "ip/organizationDepartmentForm";
	}

	@RequiresPermissions("ip:organizationDepartment:edit")
	@RequestMapping(value = "save")
	public String save(OrganizationDepartment organizationDepartment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, organizationDepartment)){
			return form(organizationDepartment, model);
		}
		organizationDepartmentService.save(organizationDepartment);
		addMessage(redirectAttributes, "保存组织机构部门'" + organizationDepartment.getBmmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/organizationDepartment";
	}
	
	@RequiresPermissions("ip:organizationDepartment:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		organizationDepartmentService.delete(id);
		addMessage(redirectAttributes, "删除组织机构部门成功");
		return "redirect:"+Global.getAdminPath()+"/ip/organizationDepartment";
	}

}
