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
import com.thinkgem.jeesite.modules.ip.entity.CalendarDetail;
import com.thinkgem.jeesite.modules.ip.service.CalendarDetailService;
import com.thinkgem.jeesite.modules.ip.service.CalendarService;

/**
 * 工厂日历明细Controller
 * @author lzq
 * @version 2016-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/calendarDetail")
public class CalendarDetailController extends BaseController {

	@Autowired
	private CalendarDetailService calendarDetailService;
	@Autowired
	private CalendarService calendarService;
	
	@ModelAttribute
	public CalendarDetail get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return calendarDetailService.get(id);
		}else{
			return new CalendarDetail();
		}
	}
	
	@RequiresPermissions("ip:calendarDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(CalendarDetail calendarDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			calendarDetail.setCreateBy(user);
		}
        Page<CalendarDetail> page = calendarDetailService.find(new Page<CalendarDetail>(request, response), calendarDetail); 
        model.addAttribute("page", page);
		return "modules/" + "ip/calendarDetailList";
	}

	@RequiresPermissions("ip:calendarDetail:view")
	@RequestMapping(value = "form")
	public String form(CalendarDetail calendarDetail, Model model) {
		model.addAttribute("calendarDetail", calendarDetail);
		model.addAttribute("faList", calendarService.findAll());
		return "modules/" + "ip/calendarDetailForm";
	}
	
	@RequiresPermissions("ip:calendarDetail:view")
	@RequestMapping(value = "editform")
	public String editform(CalendarDetail calendarDetail, Model model) {
		model.addAttribute("calendarDetail", calendarDetail);
		model.addAttribute("faList", calendarService.findAll());
		return "modules/" + "ip/calendarDetailEditForm";
	}

	@RequiresPermissions("ip:calendarDetail:edit")
	@RequestMapping(value = "save")
	public String save(CalendarDetail calendarDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, calendarDetail)){
			return form(calendarDetail, model);
		}
		calendarDetailService.save(calendarDetail);
		addMessage(redirectAttributes, "保存工厂日历明细'" + calendarDetail.getGcrlfa_id() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarDetail/?gcrlfa_id="+calendarDetail.getGcrlfa_id();
	}
	
	@RequiresPermissions("ip:calendarDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		calendarDetailService.delete(id);
		addMessage(redirectAttributes, "删除工厂日历明细成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarDetail/?repage";
	}
	
	@RequiresPermissions("ip:employeePoliticalStatus:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			calendarDetailService.delete(id[i]);
		}
		addMessage(redirectAttributes, "删除工厂日历明细成功");
		return "redirect:"+Global.getAdminPath()+"/ip/calendarDetail/?repage";
	}

}
