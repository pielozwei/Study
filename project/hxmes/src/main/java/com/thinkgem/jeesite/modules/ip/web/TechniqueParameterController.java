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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueParameter;
import com.thinkgem.jeesite.modules.ip.service.TechniqueParameterService;

/**
 * 工艺参数Controller
 * @author zzc
 * @version 2016-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/techniqueParameter")
public class TechniqueParameterController extends BaseController {

	@Autowired
	private TechniqueParameterService techniqueParameterService;
	
	@ModelAttribute
	public TechniqueParameter get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueParameterService.get(id);
		}else{
			return new TechniqueParameter();
		}
	}
	
	@RequiresPermissions("ip:techniqueParameter:view")
	@RequestMapping(value = {"list", ""})
	public String list(TechniqueParameter techniqueParameter, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			techniqueParameter.setCreateBy(user);
		}
        Page<TechniqueParameter> page = techniqueParameterService.find(new Page<TechniqueParameter>(request, response), techniqueParameter); 
        model.addAttribute("page", page);
		return "modules/" + "ip/techniqueParameterList";
	}

	@RequiresPermissions("ip:techniqueParameter:view")
	@RequestMapping(value = "form")
	public String form(TechniqueParameter techniqueParameter, Model model) {
		model.addAttribute("techniqueParameter", techniqueParameter);
		return "modules/" + "ip/techniqueParameterForm";
	}

	@RequiresPermissions("ip:techniqueParameter:edit")
	@RequestMapping(value = "save")
	public String save(TechniqueParameter techniqueParameter, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, techniqueParameter)){
			return form(techniqueParameter, model);
		}
		techniqueParameterService.save(techniqueParameter);
		addMessage(redirectAttributes, "保存工艺参数'" + techniqueParameter.getGycsmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueParameter/?repage";
	}
	
	@RequiresPermissions("ip:techniqueParameter:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueParameterService.delete(id);
		addMessage(redirectAttributes, "删除工艺参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueParameter/?repage";
	}

	@RequiresPermissions("ip:techniqueParameter:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			techniqueParameterService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除工艺参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueParameter/?repage";
	}
}
