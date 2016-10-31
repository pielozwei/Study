/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.OrganizationPositionRank;
import com.thinkgem.jeesite.modules.ip.service.OrganizationPositionRankService;

/**
 * 人员业务分类管理Controller
 * @author Iris
 * @version 2016-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/organizationPositionRank")
public class OrganizationPositionRankController extends BaseController {

	@Autowired
	private OrganizationPositionRankService organizationPositionRankService;
	
	@ModelAttribute
	public OrganizationPositionRank get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return organizationPositionRankService.get(id);
		}else{
			return new OrganizationPositionRank();
		}
	}
	
	@RequiresPermissions("ip:organizationPositionRank:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrganizationPositionRank organizationPositionRank, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			organizationPositionRank.setCreateBy(user);
		}
        Page<OrganizationPositionRank> page = organizationPositionRankService.find(new Page<OrganizationPositionRank>(request, response), organizationPositionRank); 
        model.addAttribute("page", page);
		return "modules/" + "ip/organizationPositionRankList";
	}

	@RequiresPermissions("ip:organizationPositionRank:view")
	@RequestMapping(value = "form")
	public String form(OrganizationPositionRank organizationPositionRank, Model model) {
		model.addAttribute("organizationPositionRank", organizationPositionRank);
		return "modules/" + "ip/organizationPositionRankForm";
	}

	@RequiresPermissions("ip:organizationPositionRank:edit")
	@RequestMapping(value = "save")
	public String save(OrganizationPositionRank organizationPositionRank, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, organizationPositionRank)){
			return form(organizationPositionRank, model);
		}
		organizationPositionRankService.save(organizationPositionRank);
		addMessage(redirectAttributes, "保存人员业务分类管理'" + organizationPositionRank.getZZJG_ID() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/organizationPositionRank/?repage";
	}
	
	@RequiresPermissions("ip:organizationPositionRank:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		organizationPositionRankService.delete(id);
		addMessage(redirectAttributes, "删除人员业务分类管理成功");
		return "redirect:"+Global.getAdminPath()+"/ip/organizationPositionRank/?repage";
	}
	@RequiresUser
	@ResponseBody
	@RequestMapping("treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<OrganizationPositionRank> list = organizationPositionRankService.findAll();
		for (int i=0; i<list.size(); i++){
			OrganizationPositionRank e = list.get(i);
		
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("name", e.getRYYWLBMC());
			mapList.add(map);
		}
		
		return mapList;
	}

}
