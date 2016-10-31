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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueEnergy;
import com.thinkgem.jeesite.modules.ip.service.TechniqueEnergyService;

/**
 * 能源定额Controller
 * @author WuWB
 * @version 2016-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/techniqueEnergy")
public class TechniqueEnergyController extends BaseController {

	@Autowired
	private TechniqueEnergyService techniqueEnergyService;
	
	@ModelAttribute
	public TechniqueEnergy get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueEnergyService.get(id);
		}else{
			return new TechniqueEnergy();
		}
	}
	
	@RequiresPermissions("ip:techniqueEnergy:view")
	@RequestMapping(value = {"list", ""})
	public String list(TechniqueEnergy techniqueEnergy, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			techniqueEnergy.setCreateBy(user);
		}
        Page<TechniqueEnergy> page = techniqueEnergyService.find(new Page<TechniqueEnergy>(request, response), techniqueEnergy); 
        model.addAttribute("page", page);
		return "modules/" + "ip/techniqueEnergyList";
	}

	@RequiresPermissions("ip:techniqueEnergy:view")
	@RequestMapping(value = "form")
	public String form(TechniqueEnergy techniqueEnergy, Model model) {
		model.addAttribute("techniqueEnergy", techniqueEnergy);
		return "modules/" + "ip/techniqueEnergyForm";
	}

	@RequiresPermissions("ip:techniqueEnergy:edit")
	@RequestMapping(value = "save")
	public String save(TechniqueEnergy techniqueEnergy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, techniqueEnergy)){
			return form(techniqueEnergy, model);
		}
		techniqueEnergyService.save(techniqueEnergy);
		addMessage(redirectAttributes, "保存能源定额'" + techniqueEnergy.getGygzzx_id() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueEnergy/?repage";
	}
	
	@RequiresPermissions("ip:techniqueEnergy:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueEnergyService.delete(id);
		addMessage(redirectAttributes, "删除能源定额成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueEnergy/?repage";
	}

	@RequiresPermissions("ip:techniqueEnergy:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			techniqueEnergyService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除能源定额成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueEnergy/?repage";
	}
}
