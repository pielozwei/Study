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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.ip.entity.MaterialTechParameter;
import com.thinkgem.jeesite.modules.ip.service.MaterialTechParameterService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 物料技术参数Controller
 * @author ZhangHD
 * @version 2016-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/wljscs")
public class MaterialTechParameteController extends BaseController {

	@Autowired
	private MaterialTechParameterService wljscsService;
	
	@ModelAttribute
	public MaterialTechParameter get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return wljscsService.get(id);
		}else{
			return new MaterialTechParameter();
		}
	}
	
	@RequiresPermissions("wuliao:wljscs:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialTechParameter wljscs, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			wljscs.setCreateBy(user);
		}
		model.addAttribute("wljscs", wljscs);
        Page<MaterialTechParameter> page = wljscsService.find(new Page<MaterialTechParameter>(request, response), wljscs); 
        model.addAttribute("page", page);
		return "modules/" + "ip/materialTechParameterList";
	}

	@RequiresPermissions("wuliao:wljscs:view")
	@RequestMapping(value = "form")
	public String form(MaterialTechParameter wljscs, Model model) {
		model.addAttribute("wljscs", wljscs);
		return "modules/" + "ip/materialTechParameterForm";
	}

	@RequiresPermissions("wuliao:wljscs:edit")
	@RequestMapping(value = "save")
	public String save(MaterialTechParameter wljscs, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, wljscs)){
			return form(wljscs, model);
		}
		wljscsService.save(wljscs);
		String wlbm=wljscs.getWlbm().getId();
		addMessage(redirectAttributes, "保存物料技术参数'" + wljscs.getCsmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/wljscs/?repage&wlbm.id="+wlbm;
	}
	
	@RequiresPermissions("wuliao:wljscs:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String wlbm=wljscsService.get(ids[0]).getWlbm().getId();
		for (String id : ids) {
				wljscsService.delete(id);
			}
		addMessage(redirectAttributes, "删除物料技术参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/wljscs/?repage&wlbm.id="+wlbm;
	}
	@ResponseBody
	@RequestMapping("checkCsdm")
	public String checkCsdm(String csdm, String oldCsdm) {
		if (csdm != null && oldCsdm.equals(csdm)) {
			return "true";
		} else if (csdm != null && wljscsService.findByCsdm(csdm) == null) {
			return "true";
		}
		return "false";
	}

}
