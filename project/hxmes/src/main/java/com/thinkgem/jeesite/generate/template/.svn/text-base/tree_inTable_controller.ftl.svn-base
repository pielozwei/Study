/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.web<#if subModuleName != "">.${subModuleName}</#if>;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import ${packageName}.${moduleName}.entity<#if subModuleName != "">.${subModuleName}</#if>.${ClassName};
import ${packageName}.${moduleName}.service<#if subModuleName != "">.${subModuleName}</#if>.${ClassName}Service;

/**
 * ${functionName}Controller
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Controller
@RequestMapping(value = "${r"${adminPath}"}/${urlPrefix}")
public class ${ClassName}Controller extends BaseController {

	@Autowired
	private ${ClassName}Service ${className}Service;
	
	@ModelAttribute("${className}")
	public ${ClassName} get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return ${className}Service.get${ClassName}(id);
		}else{
			return new ${ClassName}();
		}
	}
	
	@RequiresPermissions("${permissionPrefix}:view")
	@RequestMapping(value = {"list", ""})
	public String list(${ClassName} ${className}, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<${ClassName}> list = Lists.newArrayList();
		List<${ClassName}> sourcelist = ${className}Service.findAll${ClassName}();
		${ClassName}.sortList(list, sourcelist, "1");
        model.addAttribute("list", list);
		return "modules/${viewPrefix}List";
	}

	@RequiresPermissions("${permissionPrefix}:view")
	@RequestMapping(value = "form")
	public String form(${ClassName} ${className}, Model model) {
		if (${className}.getParent()==null || StringUtils.isNotBlank(${className}.getParent().getId())){
			${className}.setParent(new ${ClassName}("1"));
		}
		${className}.setParent(${className}Service.get${ClassName}(${className}.getParent().getId()));
		model.addAttribute("${className}", ${className});
		return "modules/${viewPrefix}Form";
	}

	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "save")
	public String save(${ClassName} ${className}, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ${className})){
			return form(${className}, model);
		}
		${className}Service.save${ClassName}(${className});
		addMessage(redirectAttributes, "保存${functionName}'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/${viewPrefix}/?repage";
	}
	
	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		if (${ClassName}.isRoot(id)){
			addMessage(redirectAttributes, "删除树节点失败, 不允许删除顶级树节点或编号为空");
		}else{
			${className}Service.delete${ClassName}(id);
			addMessage(redirectAttributes, "删除树节点成功");
		}
		return "redirect:"+Global.getAdminPath()+"/${viewPrefix}/?repage";
	}
	
	@RequestMapping(value = "tree")
	public String tree() {
		return "modules/${viewPrefix}";
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<${ClassName}> list = ${className}Service.findAll${ClassName}();
		for (int i=0; i<list.size(); i++){
			${ClassName} e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent()!=null?e.getParent().getId():0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}	
}
