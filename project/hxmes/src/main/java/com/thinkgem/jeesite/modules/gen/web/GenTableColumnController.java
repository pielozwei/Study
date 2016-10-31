/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.web;

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
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.gen.service.GenTableColumnService;

/**
 * 代码生成Controller
 * @author LiuBJ
 * @version 2016-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genTableColumn")
public class GenTableColumnController extends BaseController {

	@Autowired
	private GenTableColumnService genTableColumnService;
	
	@ModelAttribute
	public GenTableColumn get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return genTableColumnService.get(id);
		}else{
			return new GenTableColumn();
		}
	}
	
	@RequiresPermissions("gen:genTableColumn:view")
	@RequestMapping(value = {"list", ""})
	public String list(GenTableColumn genTableColumn, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			genTableColumn.setCreateBy(user);
		}
        Page<GenTableColumn> page = genTableColumnService.find(new Page<GenTableColumn>(request, response), genTableColumn); 
        model.addAttribute("page", page);
		return "modules/" + "gen/genTableColumnList";
	}

	@RequiresPermissions("gen:genTableColumn:view")
	@RequestMapping(value = "form")
	public String form(GenTableColumn genTableColumn, Model model) {
		model.addAttribute("genTableColumn", genTableColumn);
		return "modules/" + "gen/genTableColumnForm";
	}

	@RequiresPermissions("gen:genTableColumn:edit")
	@RequestMapping(value = "save")
	public String save(GenTableColumn genTableColumn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, genTableColumn)){
			return form(genTableColumn, model);
		}
		genTableColumnService.save(genTableColumn);
		addMessage(redirectAttributes, "保存代码生成'" + genTableColumn.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/gen/genTableColumn/?repage";
	}
	
	@RequiresPermissions("gen:genTableColumn:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		genTableColumnService.delete(id);
		addMessage(redirectAttributes, "删除代码生成成功");
		return "redirect:"+Global.getAdminPath()+"/gen/genTableColumn/?repage";
	}

}
