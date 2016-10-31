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
import com.thinkgem.jeesite.modules.ip.entity.OrganizationPosition;
import com.thinkgem.jeesite.modules.ip.service.OrganizationPositionRankService;
import com.thinkgem.jeesite.modules.ip.service.OrganizationPositionService;

/**
 * 岗位信息管理Controller
 * @author Iris
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/organizationPosition")
public class OrganizationPositionController extends BaseController {

	@Autowired
	private OrganizationPositionService organizationPositionService;
	
	@Autowired
	private OrganizationPositionRankService organizationPositionRankService;
	
	@ModelAttribute
	public OrganizationPosition get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return organizationPositionService.get(id);
		}else{
			return new OrganizationPosition();
		}
	}
	
	@RequiresPermissions("ip:organizationPosition:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrganizationPosition organizationPosition, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			organizationPosition.setCreateBy(user);
		}
        Page<OrganizationPosition> page = organizationPositionService.find(new Page<OrganizationPosition>(request, response), organizationPosition); 
        model.addAttribute("page", page);
        model.addAttribute("PRlist", organizationPositionRankService.findAll());
		return "modules/" + "ip/organizationPositionList";
	}

	@RequiresPermissions("ip:organizationPosition:view")
	@RequestMapping(value = "form")
	public String form(OrganizationPosition organizationPosition, Model model) {
		model.addAttribute("PRlist", organizationPositionRankService.findAll());
		model.addAttribute("organizationPosition", organizationPosition);
		return "modules/" + "ip/organizationPositionForm";
	}

	@RequiresPermissions("ip:organizationPosition:edit")
	@RequestMapping(value = "save")
	public String save(OrganizationPosition organizationPosition, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, organizationPosition)){
			return form(organizationPosition, model);
		}
		organizationPositionService.save(organizationPosition);
		addMessage(redirectAttributes, "保存岗位信息管理'" + organizationPosition.getGwmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/organizationPosition/?repage";
	}
	
	@RequiresPermissions("ip:organizationPosition:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		organizationPositionService.delete(id);
		addMessage(redirectAttributes, "删除岗位信息管理成功");
		return "redirect:"+Global.getAdminPath()+"/ip/organizationPosition/?repage";
	}

}
