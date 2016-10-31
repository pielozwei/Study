/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterWorkTime;
import com.thinkgem.jeesite.modules.ip.entity.WorkShift;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterService;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterWorkTimeService;
import com.thinkgem.jeesite.modules.ip.service.WorkShiftService;

/**
 * 工作中心班次配置Controller
 * @author lucl
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/gzzx/gzzxbc")
public class WorkCenterWorkTimeController extends BaseController {

	@Autowired
	private WorkCenterWorkTimeService gzzxbcService;
	
	@Autowired
	private WorkCenterService gzzxwhService;
	
	@Autowired
	private WorkShiftService workShiftService;
	
	@ModelAttribute
	public WorkCenterWorkTime get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return gzzxbcService.get(id);
		}else{
			return new WorkCenterWorkTime();
		}
	}
	
	@RequiresPermissions("gzzx:gzzxbc:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkCenterWorkTime gzzxbc, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			gzzxbc.setCreateBy(user);
		}
        Page<WorkCenterWorkTime> page = gzzxbcService.find(new Page<WorkCenterWorkTime>(request, response), gzzxbc); 
        model.addAttribute("page", page);
        model.addAttribute("gzzxbc", gzzxbc);
		return "modules/" + "ip/workCenterWorkTimeList";
	}

	@RequiresPermissions("gzzx:gzzxbc:view")
	@RequestMapping(value = "form")
	public String form(WorkCenterWorkTime gzzxbc, Model model) {
		if(null!=gzzxbc&&gzzxbc.getGzbzId()!=null&&gzzxbc.getGzbzId().getId()!=null&&!gzzxbc.getGzbzId().getId().equals("")){
			WorkShift workShift = workShiftService.get(gzzxbc.getGzbzId().getId());
			model.addAttribute("workShift", workShift);
		}
		model.addAttribute("gzzxbc", gzzxbc);
		return "modules/" + "ip/workCenterWorkTimeForm";
	}

	@RequiresPermissions("gzzx:gzzxbc:edit")
	@RequestMapping(value = "save")
	public String save(WorkCenterWorkTime gzzxbc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzzxbc)){
			return form(gzzxbc, model);
		}
		if((null!=gzzxbc&&(null!=gzzxbc.getXssx()&&gzzxbc.getXssx().equals("")))||(null!=gzzxbc&&null==gzzxbc.getXssx())){
			gzzxbc.setXssx(100);
		}
		gzzxbcService.save(gzzxbc);
		addMessage(redirectAttributes, "保存工作中心班次配置成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxbc?gzbzId="+gzzxbc.getGzbzId().getId();
	}
	
	@RequiresPermissions("gzzx:gzzxbc:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		gzzxbcService.delete(id);
		addMessage(redirectAttributes, "删除工作中心班次配置成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxbc/?repage";
	}
	
	/**
	 * 方法功能：弹出工作中心班次配置主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "start")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxbc";
		}
		model.addAttribute("module", module);
		return "modules/ip/workCenterWorkTimeIndex";
	}
	/**
	 * 方法功能：生成仓库树
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxbc";
		}
		model.addAttribute("module", module);
		model.addAttribute("gzzxList", gzzxwhService.findTree(true, null));
		List<WorkCenter> list=gzzxwhService.findTree(true, null);
		return "modules/ip/workCenterTree";
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
			gzzxbcService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个班次信息！");
		return "redirect:"+Global.getAdminPath()+"/cangku/ck/none";
	}

}
