/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.mes.web;

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
import com.thinkgem.jeesite.modules.mes.entity.Inventory;
import com.thinkgem.jeesite.modules.mes.service.InventoryService;

/**
 * 仓库Controller
 * @author LiuBaoJ
 * @version 2016-05-19
 */
@Controller
@RequestMapping(value = "${adminPath}/mes/inventory")
public class InventoryController extends BaseController {

	@Autowired
	private InventoryService inventoryService;
	
	@ModelAttribute
	public Inventory get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return inventoryService.get(id);
		}else{
			return new Inventory();
		}
	}
	
	@RequiresPermissions("mes:inventory:view")
	@RequestMapping(value = {"list", ""})
	public String list(Inventory inventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			inventory.setCreateBy(user);
		}
        Page<Inventory> page = inventoryService.find(new Page<Inventory>(request, response), inventory); 
        model.addAttribute("page", page);
		return "modules/" + "mes/inventoryList";
	}

	@RequiresPermissions("mes:inventory:view")
	@RequestMapping(value = "form")
	public String form(Inventory inventory, Model model) {
		model.addAttribute("inventory", inventory);
		return "modules/" + "mes/inventoryForm";
	}

	@RequiresPermissions("mes:inventory:edit")
	@RequestMapping(value = "save")
	public String save(Inventory inventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, inventory)){
			return form(inventory, model);
		}
		inventoryService.save(inventory);
		addMessage(redirectAttributes, "保存仓库'" + inventory.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/mes/inventory/?repage";
	}
	
	@RequiresPermissions("mes:inventory:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		inventoryService.delete(id);
		addMessage(redirectAttributes, "删除仓库成功");
		return "redirect:"+Global.getAdminPath()+"/mes/inventory/?repage";
	}

}
