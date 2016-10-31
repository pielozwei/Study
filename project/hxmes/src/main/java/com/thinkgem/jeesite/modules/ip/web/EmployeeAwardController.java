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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeAward;
import com.thinkgem.jeesite.modules.ip.service.EmployeeAwardService;

/**
 * 获奖情况记录Controller
 * @author zzc
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeeAward")
public class EmployeeAwardController extends BaseController {

	@Autowired
	private EmployeeAwardService employeeAwardService;
	
	@ModelAttribute
	public EmployeeAward get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeeAwardService.get(id);
		}else{
			return new EmployeeAward();
		}
	}
	
	@RequiresPermissions("ip:employeeAward:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeeAward employeeAward, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeeAward.setCreateBy(user);
		}
        Page<EmployeeAward> page = employeeAwardService.find(new Page<EmployeeAward>(request, response), employeeAward); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeeAwardList";
	}

	@RequiresPermissions("ip:employeeAward:view")
	@RequestMapping(value = "form")
	public String form(EmployeeAward employeeAward, Model model) {
		model.addAttribute("employeeAward", employeeAward);
		return "modules/" + "ip/employeeAwardForm";
	}

	@RequiresPermissions("ip:employeeAward:edit")
	@RequestMapping(value = "save")
	public String save(EmployeeAward employeeAward, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeeAward)){
			return form(employeeAward, model);
		}
		employeeAwardService.save(employeeAward);
		addMessage(redirectAttributes, "保存获奖情况记录'" + employeeAward.getHjmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeAward/?repage";
	}
	
	@RequiresPermissions("ip:employeeAward:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeeAwardService.delete(id);
		addMessage(redirectAttributes, "删除获奖情况记录成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeAward/?repage";
	}
	
	@RequiresPermissions("ip:employeeAward:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			employeeAwardService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除获奖情况记录成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeeAward/?repage";
	}

}
