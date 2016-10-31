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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueStore;
import com.thinkgem.jeesite.modules.ip.service.TechniqueStoreService;

/**
 * 存储白名单Controller
 * @author ks
 * @version 2016-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/techniqueStore")
public class TechniqueStoreController extends BaseController {

	@Autowired
	private TechniqueStoreService techniqueStoreService;
	
	@ModelAttribute
	public TechniqueStore get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueStoreService.get(id);
		}else{
			return new TechniqueStore();
		}
	}
	
	@RequiresPermissions("ip:techniqueStore:view")
	@RequestMapping(value = {"list", ""})
	public String list(TechniqueStore techniqueStore, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			techniqueStore.setCreateBy(user);
		}
        Page<TechniqueStore> page = techniqueStoreService.find(new Page<TechniqueStore>(request, response), techniqueStore); 
        model.addAttribute("page", page);
		return "modules/" + "ip/techniqueStoreList";
	}

	@RequiresPermissions("ip:techniqueStore:view")
	@RequestMapping(value = "form")
	public String form(TechniqueStore techniqueStore, Model model) {
		model.addAttribute("techniqueStore", techniqueStore);
		return "modules/" + "ip/techniqueStoreForm";
	}

	@RequiresPermissions("ip:techniqueStore:edit")
	@RequestMapping(value = "save")
	public String save(TechniqueStore techniqueStore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, techniqueStore)){
			return form(techniqueStore, model);
		}
		techniqueStoreService.save(techniqueStore);
		addMessage(redirectAttributes, "保存存储白名单'" + techniqueStore.getWl_id() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueStore/?repage";
	}
	
	@RequiresPermissions("ip:techniqueStore:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueStoreService.delete(id);
		addMessage(redirectAttributes, "删除存储白名单成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueStore/?repage";
	}

	@RequiresPermissions("ip:techniqueStore:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			techniqueStoreService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除存储白名单成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueStore/?repage";
	}
}
