/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.web${subModuleName_};

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import ${packageName}.${moduleName}.entity${subModuleName_}.${ClassName};
import ${packageName}.${moduleName}.service${subModuleName_}.${ClassName}Service;

/**
 * ${functionName}Controller
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Controller
@RequestMapping(value = "${r"${adminPath}"}/${urlPrefix}")
public class ${ClassName}Controller extends BaseController {

	@Autowired
	private ${ClassName}Service ${className}Service;
	
	@ModelAttribute
	public ${ClassName} get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return ${className}Service.get(id);
		}else{
			return new ${ClassName}();
		}
	}
	
	@RequiresPermissions("${permissionPrefix}:view")
	@RequestMapping(value = {"list", ""})
	public String list(${ClassName} ${className},Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			${className}.setCreateBy(user);
		}
		String nodeId=request.getParameter("nodeId");
		Page<${ClassName}> paramPage = new Page<${ClassName}>(request, response);
        Page<${ClassName}> page = ${className}Service.find(paramPage, ${className}, nodeId); 
        model.addAttribute("page", page);
        model.addAttribute("nodeId", nodeId);
		return "modules/" + "${viewPrefix}List";
	}

	@RequiresPermissions("${permissionPrefix}:view")
	@RequestMapping(value = "form")
	public String form(${ClassName} ${className}, String nodeId, Model model) {
		if(StringUtils.isBlank(${className}.getId())){//添加给个编码
			List<${ClassName}> list=${className}Service.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("${className}", ${className});
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "${viewPrefix}Form";
	}

	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "save")
	public String save(${ClassName} ${className}, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ${className})){
			return form(${className}, "1", model);
		}
		${className}Service.save(${className});
		addMessage(redirectAttributes, "保存${functionName}'" + ${className}.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/none";
	}
	/*该节点是否有子节点*/
	public boolean hasChildNode(String nodeId){
		boolean b=false;
		if(${className}Service.findSun(nodeId).size()>0)
			b=true;
		
		return b;
	}
	
	/*该节点是否有信息关联*/
	public boolean hasInfo(String nodeId){
		boolean b=false;
		if(${className}Service.findInfo(nodeId).size()>0)
			b=true;
		
		return b;
	}
	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String nodeId = ${className}Service.get(id).getParent().getId();
		if(hasChildNode(id)){
			addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
			return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?nodeId="+nodeId;
		}
		if(hasInfo(id)){
			addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
			return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?nodeId="+nodeId;
		}
		${className}Service.delete(id);
		addMessage(redirectAttributes, "删除${functionName}成功");
		return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/none";
	}
	
	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId = ${className}Service.get(ids[0]).getParent().getId();
		for(String id:ids){
			if(hasChildNode(id)){
				addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
				return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?nodeId="+nodeId;
			}
			if(hasInfo(id)){
				addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
				return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?nodeId="+nodeId;
			}
			${className}Service.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个${functionName}成功");
		return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/none";
	}
	
	@RequiresPermissions("${permissionPrefix}:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(${ClassName} ${className}, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "${functionName}数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<${ClassName}> page = ${className}Service.find(new Page<${ClassName}>(request, response), ${className},null); 
    		new ExportExcel("${functionName}数据", ${ClassName}.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出${functionName}失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/${urlPrefix}/";
    }
    
    @RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String,Object>> treeData(String module, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		return ${className}Service.getTreeData();
	}

}
