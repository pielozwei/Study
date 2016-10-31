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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePosition;
import com.thinkgem.jeesite.modules.ip.service.EmployeePositionService;

/**
 * 琛屾斂鍏氭淳鑱屽姟缁忓巻Controller
 * @author yrd
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeePosition")
public class EmployeePositionController extends BaseController {

	@Autowired
	private EmployeePositionService employeePositionService;
	
	@ModelAttribute
	public EmployeePosition get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeePositionService.get(id);
		}else{
			return new EmployeePosition();
		}
	}
	
	@RequiresPermissions("ip:employeePosition:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeePosition employeePosition, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeePosition.setCreateBy(user);
		}
        Page<EmployeePosition> page = employeePositionService.find(new Page<EmployeePosition>(request, response), employeePosition); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeePositionList";
	}

	@RequiresPermissions("ip:employeePosition:view")
	@RequestMapping(value = "form")
	public String form(EmployeePosition employeePosition, Model model) {
		model.addAttribute("employeePosition", employeePosition);
		return "modules/" + "ip/employeePositionForm";
	}

	@RequiresPermissions("ip:employeePosition:edit")
	@RequestMapping(value = "save")
	public String save(EmployeePosition employeePosition, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeePosition)){
			return form(employeePosition, model);
		}
		employeePositionService.save(employeePosition);
		addMessage(redirectAttributes, "保存行政党派职务'" + employeePosition.getZw() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePosition/?repage";
	}
	
	@RequiresPermissions("ip:employeePosition:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeePositionService.delete(id);
		addMessage(redirectAttributes, "删除行政党派职务成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePosition/?repage";
	}
	
	@RequiresPermissions("ip:employeePosition:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeePositionService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除行政党派职务经历成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePosition/?repage";
	}

}
