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
import com.thinkgem.jeesite.modules.ip.entity.WorkShift;
import com.thinkgem.jeesite.modules.ip.service.WorkShiftService;

/**
 * 工作班制Controller
 * @author lucl
 * @version 2016-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/workShift")
public class WorkShiftController extends BaseController {

	@Autowired
	private WorkShiftService workShiftService;
	
	@ModelAttribute
	public WorkShift get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return workShiftService.get(id);
		}else{
			return new WorkShift();
		}
	}
	
	@RequiresPermissions("ip:workShift:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkShift workShift, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			workShift.setCreateBy(user);
		}
        Page<WorkShift> page = workShiftService.find(new Page<WorkShift>(request, response), workShift); 
        model.addAttribute("page", page);
		return "modules/" + "ip/workShiftList";
	}

	@RequiresPermissions("ip:workShift:view")
	@RequestMapping(value = "form")
	public String form(WorkShift workShift, Model model) {
		model.addAttribute("workShift", workShift);
		return "modules/" + "ip/workShiftForm";
	}

	@RequiresPermissions("ip:workShift:edit")
	@RequestMapping(value = "save")
	public String save(WorkShift workShift, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, workShift)){
			return form(workShift, model);
		}
		if((null!=workShift&&(null!=workShift.getXssx()&&workShift.getXssx().equals("")))||(null!=workShift&&null==workShift.getXssx())){
			workShift.setXssx(100);
		}
		workShiftService.save(workShift);
		addMessage(redirectAttributes, "保存工作班制'" + workShift.getBzmc()+ "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/workShift/?repage";
	}
	
	@RequiresPermissions("ip:workShift:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		workShiftService.delete(id);
		addMessage(redirectAttributes, "删除工作班制成功");
		return "redirect:"+Global.getAdminPath()+"/ip/workShift/?repage";
	}
	/**
	 * 批量删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		int j=0;
		for(String id :ids){
			workShiftService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个工作班制！");
		return "redirect:"+Global.getAdminPath()+"/ip/workShift/list";
	}

}
