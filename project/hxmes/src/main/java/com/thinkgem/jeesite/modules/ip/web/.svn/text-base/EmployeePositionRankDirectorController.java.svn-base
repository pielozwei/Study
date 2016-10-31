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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePositionRankDirector;
import com.thinkgem.jeesite.modules.ip.service.EmployeePositionRankDirectorService;

/**
 * 人员业务类别Controller
 * @author yrd
 * @version 2016-09-01
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/employeePositionRankDirector")
public class EmployeePositionRankDirectorController extends BaseController {

	@Autowired
	private EmployeePositionRankDirectorService employeePositionRankDirectorService;
	
	@ModelAttribute
	public EmployeePositionRankDirector get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return employeePositionRankDirectorService.get(id);
		}else{
			return new EmployeePositionRankDirector();
		}
	}
	
	@RequiresPermissions("ip:employeePositionRankDirector:view")
	@RequestMapping(value = {"list", ""})
	public String list(EmployeePositionRankDirector employeePositionRankDirector, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			employeePositionRankDirector.setCreateBy(user);
		}
        Page<EmployeePositionRankDirector> page = employeePositionRankDirectorService.find(new Page<EmployeePositionRankDirector>(request, response), employeePositionRankDirector); 
        model.addAttribute("page", page);
		return "modules/" + "ip/employeePositionRankDirectorList";
	}

	@RequiresPermissions("ip:employeePositionRankDirector:view")
	@RequestMapping(value = "form")
	public String form(EmployeePositionRankDirector employeePositionRankDirector, Model model) {
		model.addAttribute("employeePositionRankDirector", employeePositionRankDirector);
		return "modules/" + "ip/employeePositionRankDirectorForm";
	}

	@RequiresPermissions("ip:employeePositionRankDirector:edit")
	@RequestMapping(value = "save")
	public String save(EmployeePositionRankDirector employeePositionRankDirector, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, employeePositionRankDirector)){
			return form(employeePositionRankDirector, model);
		}
		employeePositionRankDirectorService.save(employeePositionRankDirector);
		addMessage(redirectAttributes, "保存人员业务类别'" + employeePositionRankDirector.getId() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePositionRankDirector/?repage";
	}
	
	@RequiresPermissions("ip:employeePositionRankDirector:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		employeePositionRankDirectorService.delete(id);
		addMessage(redirectAttributes, "删除人员业务类别成功");
		return "redirect:"+Global.getAdminPath()+"/ip/employeePositionRankDirector/?repage";
	}

}
