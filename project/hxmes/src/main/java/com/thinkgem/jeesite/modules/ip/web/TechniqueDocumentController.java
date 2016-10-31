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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueDocument;
import com.thinkgem.jeesite.modules.ip.service.TechniqueDocumentService;

/**
 * 人员业务分类管理Controller
 * @author tianjingyi,yangsu
 * @version 2016-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/techniqueDocument")
public class TechniqueDocumentController extends BaseController {

	@Autowired
	private TechniqueDocumentService techniqueDocumentService;
	
	@ModelAttribute
	public TechniqueDocument get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueDocumentService.get(id);
		}else{
			return new TechniqueDocument();
		}
	}
	
	@RequiresPermissions("ip:techniqueDocument:view")
	@RequestMapping(value = {"list", ""})
	public String list(TechniqueDocument techniqueDocument, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			techniqueDocument.setCreateBy(user);
		}
        Page<TechniqueDocument> page = techniqueDocumentService.find(new Page<TechniqueDocument>(request, response), techniqueDocument); 
        model.addAttribute("page", page);
		return "modules/" + "ip/techniqueDocumentList";
	}

	@RequiresPermissions("ip:techniqueDocument:view")
	@RequestMapping(value = "form")
	public String form(TechniqueDocument techniqueDocument, Model model) {
		model.addAttribute("techniqueDocument", techniqueDocument);
		return "modules/" + "ip/techniqueDocumentForm";
	}

	@RequiresPermissions("ip:techniqueDocument:edit")
	@RequestMapping(value = "save")
	public String save(TechniqueDocument techniqueDocument, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, techniqueDocument)){
			return form(techniqueDocument, model);
		}
		techniqueDocumentService.save(techniqueDocument);
		addMessage(redirectAttributes, "保存'" + techniqueDocument.getWjbtmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueDocument/?repage";
	}
	
	@RequiresPermissions("ip:techniqueDocument:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueDocumentService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/ip/techniqueDocument/?repage";
	}

}
