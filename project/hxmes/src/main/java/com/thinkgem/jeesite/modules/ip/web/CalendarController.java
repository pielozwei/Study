/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.text.ParseException;

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
import com.thinkgem.jeesite.modules.ip.entity.Calendar;
import com.thinkgem.jeesite.modules.ip.service.CalendarService;

/**
 * 工厂日历方案Controller
 * @author lzq
 * @version 2016-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/calendar")
public class CalendarController extends BaseController {

	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public Calendar get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return calendarService.get(id);
		}else{
			return new Calendar();
		}
	}
	
	@RequiresPermissions("ip:calendar:view")
	@RequestMapping(value = {"list", ""})
	public String list(Calendar calendar, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			calendar.setCreateBy(user);
		}
        Page<Calendar> page = calendarService.find(new Page<Calendar>(request, response), calendar); 
        model.addAttribute("page", page);
		return "modules/" + "ip/calendarList";
	}

	@RequiresPermissions("ip:calendar:view")
	@RequestMapping(value = "form")
	public String form(Calendar calendar, Model model) {
		model.addAttribute("calendar", calendar);
		return "modules/" + "ip/calendarForm";
	}

	@RequiresPermissions("ip:calendar:edit")
	@RequestMapping(value = "save")
	public String save(Calendar calendar, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		if (!beanValidator(model, calendar)){
			return form(calendar, model);
		}
		calendarService.save(calendar);
		addMessage(redirectAttributes, "保存工厂日历方案'" + calendar.getFamc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendar/?repage";
	}
	
	@RequiresPermissions("ip:calendar:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		calendarService.delete(id);
		addMessage(redirectAttributes, "删除工厂日历方案成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendar/?repage";
	}
	
	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			calendarService.delete(id[i]);
		}
		addMessage(redirectAttributes, "删除工厂日历方案成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendar/?repage";
	}

}
