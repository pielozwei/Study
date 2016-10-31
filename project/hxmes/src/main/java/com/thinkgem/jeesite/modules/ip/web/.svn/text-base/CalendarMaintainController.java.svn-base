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
import com.thinkgem.jeesite.modules.ip.entity.CalendarMaintain;
import com.thinkgem.jeesite.modules.ip.service.CalendarMaintainService;
import com.thinkgem.jeesite.modules.ip.service.CalendarService;

/**
 * 工厂日历维护Controller
 * @author yrd
 * @version 2016-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/calendarMaintain")
public class CalendarMaintainController extends BaseController {

	@Autowired
	private CalendarMaintainService calendarMaintainService;
	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public CalendarMaintain get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return calendarMaintainService.get(id);
		}else{
			return new CalendarMaintain();
		}
	}
	
	@RequiresPermissions("ip:calendarMaintain:view")
	@RequestMapping(value = {"list", ""})
	public String list(CalendarMaintain calendarMaintain, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			calendarMaintain.setCreateBy(user);
		}
        Page<CalendarMaintain> page = calendarMaintainService.find(new Page<CalendarMaintain>(request, response), calendarMaintain); 
        model.addAttribute("page", page);
        model.addAttribute("FAList",calendarService.findAll());
		return "modules/" + "ip/calendarMaintainList";
	}

	@RequiresPermissions("ip:calendarMaintain:view")
	@RequestMapping(value = "form")
	public String form(CalendarMaintain calendarMaintain, Model model) {
		model.addAttribute("calendarMaintain", calendarMaintain);
		return "modules/" + "ip/calendarMaintainForm";
	}

	@RequiresPermissions("ip:calendarMaintain:edit")
	@RequestMapping(value = "save")
	public String save(CalendarMaintain calendarMaintain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, calendarMaintain)){
			return form(calendarMaintain, model);
		}
		calendarMaintainService.save(calendarMaintain);
		addMessage(redirectAttributes, "保存工厂日历维护'" + calendarMaintain.getFaly() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarMaintain/?repage";
	}
	
	@RequiresPermissions("ip:calendarMaintain:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		calendarMaintainService.delete(id);
		addMessage(redirectAttributes, "删除工厂日历维护成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarMaintain/?repage";
	}
	
	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			calendarMaintainService.delete(id[i]);
		}
		addMessage(redirectAttributes, "删除工厂日历维护成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarMaintain/?repage";
	}
	
	@RequiresPermissions("ip:calendarMaintain:edit")
	@RequestMapping(value = "saveall")
	public String saveall(CalendarMaintain calendarMaintain, Model model, RedirectAttributes redirectAttributes) {
		
		calendarMaintainService.saveall(calendarMaintain.getWorkcenter().getId(),calendarMaintain.faly);
		addMessage(redirectAttributes, "生成工厂日历'" + calendarMaintain.getFaly() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarMaintain/?repage";
	}

}
