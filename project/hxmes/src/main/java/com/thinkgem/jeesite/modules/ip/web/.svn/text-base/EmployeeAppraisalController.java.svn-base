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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeAppraisal;
import com.thinkgem.jeesite.modules.ip.service.EmployeeAppraisalService;

/**
 * 组织考核经历Controller
 * @author WuWB
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeeAppraisal")
public class EmployeeAppraisalController extends BaseController {

	@Autowired
	private EmployeeAppraisalService employeeAppraisalService;
	
	@ModelAttribute
	public EmployeeAppraisal get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeAppraisalService.get(id);
		}else{
			return new EmployeeAppraisal();
		}
	}
	
	@RequiresPermissions("ip:employeeAppraisal:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeeAppraisal employeeAppraisal, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeeAppraisal.setCreateBy(user);
		}
        Page<EmployeeAppraisal> page = employeeAppraisalService.find(new Page<EmployeeAppraisal>(request, response), employeeAppraisal); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeeAppraisalList";
	}

	@RequiresPermissions("ip:employeeAppraisal:view")
	@RequestMapping(value = "form")
	public String form(EmployeeAppraisal employeeAppraisal, Model model) {
		model.addAttribute("employeeAppraisal", employeeAppraisal);
		return "modules/" + "ip/employeeAppraisalForm";
	}

	@RequiresPermissions("ip:employeeAppraisal:edit")
	@RequestMapping(value = "save")
	public String save(EmployeeAppraisal employeeAppraisal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeeAppraisal)){
			return form(employeeAppraisal, model);
		}
		employeeAppraisalService.save(employeeAppraisal);
		addMessage(redirectAttributes, "保存组织考核经历'" + employeeAppraisal.getId() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeAppraisal/?repage";
	}
	
	@RequiresPermissions("ip:employeeAppraisal:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeAppraisalService.delete(id);
		addMessage(redirectAttributes, "删除组织考核经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeAppraisal/?repage";
	}
	
	@RequiresPermissions("ip:employeeAppraisal:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeeAppraisalService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除组织考核经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeAppraisal/?repage";
	}

}
