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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueInput;
import com.thinkgem.jeesite.modules.ip.service.TechniqueInputService;

/**
 * 投入物料Controller
 * @author yrd
 * @version 2016-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/techniqueInput")
public class TechniqueInputController extends BaseController {

	@Autowired
	private TechniqueInputService techniqueInputService;
	
	@ModelAttribute
	public TechniqueInput get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueInputService.get(id);
		}else{
			return new TechniqueInput();
		}
	}
	
	@RequiresPermissions("ip:techniqueInput:view")
	@RequestMapping(value = {"list", ""})
	public String list(TechniqueInput techniqueInput, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			techniqueInput.setCreateBy(user);
		}
        Page<TechniqueInput> page = techniqueInputService.find(new Page<TechniqueInput>(request, response), techniqueInput); 
        model.addAttribute("page", page);
		return "modules/" + "ip/techniqueInputList";
	}

	@RequiresPermissions("ip:techniqueInput:view")
	@RequestMapping(value = "form")
	public String form(TechniqueInput techniqueInput, Model model) {
		model.addAttribute("techniqueInput", techniqueInput);
		return "modules/" + "ip/techniqueInputForm";
	}

	@RequiresPermissions("ip:techniqueInput:edit")
	@RequestMapping(value = "save")
	public String save(TechniqueInput techniqueInput, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, techniqueInput)){
			return form(techniqueInput, model);
		}
		techniqueInputService.save(techniqueInput);
		addMessage(redirectAttributes, "保存投入物料'" + techniqueInput.getWl_id() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueInput/?repage";
	}
	
	@RequiresPermissions("ip:techniqueInput:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueInputService.delete(id);
		addMessage(redirectAttributes, "删除投入物料成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueInput/?repage";
	}

	@RequiresPermissions("ip:techniqueInput:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			techniqueInputService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除投入物料成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueInput/?repage";
	}
}
