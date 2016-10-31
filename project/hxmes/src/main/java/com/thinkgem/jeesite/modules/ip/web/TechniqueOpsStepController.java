/**
s * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueOpsStep;
import com.thinkgem.jeesite.modules.ip.service.TechniqueOpsStepService;

/**
 * 规程要求Controller
 * @author WuWB
 * @version 2016-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/techniqueOpsStep")
public class TechniqueOpsStepController extends BaseController {

	@Autowired
	private TechniqueOpsStepService techniqueOpsStepService;
	
	@ModelAttribute
	public TechniqueOpsStep get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueOpsStepService.get(id);
		}else{
			return new TechniqueOpsStep();
		}
	}
	
	@RequiresPermissions("ip:techniqueOpsStep:view")
	@RequestMapping(value = {"list", ""})
	public String list(TechniqueOpsStep techniqueOpsStep, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			techniqueOpsStep.setCreateBy(user);
		}
        Page<TechniqueOpsStep> page = techniqueOpsStepService.find(new Page<TechniqueOpsStep>(request, response), techniqueOpsStep); 
        model.addAttribute("page", page);
		return "modules/" + "ip/techniqueOpsStepList";
	}

	@RequiresPermissions("ip:techniqueOpsStep:view")
	@RequestMapping(value = "form")
	public String form(TechniqueOpsStep techniqueOpsStep, Model model) {
		model.addAttribute("techniqueOpsStep", techniqueOpsStep);
		return "modules/" + "ip/techniqueOpsStepForm";
	}

	@RequiresPermissions("ip:techniqueOpsStep:edit")
	@RequestMapping(value = "save")
	public String save(TechniqueOpsStep techniqueOpsStep, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, techniqueOpsStep)){
			return form(techniqueOpsStep, model);
		}
		techniqueOpsStepService.save(techniqueOpsStep);
		addMessage(redirectAttributes, "保存规程要求'" + techniqueOpsStep.getGcmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueOpsStep/?repage";
	}
	
	@RequiresPermissions("ip:techniqueOpsStep:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueOpsStepService.delete(id);
		addMessage(redirectAttributes, "删除规程要求成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueOpsStep/?repage";
	}

	@RequiresPermissions("ip:techniqueOpsStep:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			techniqueOpsStepService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除规程要求成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueOpsStep/?repage";
	}
}
