/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.web${subModuleName_};

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import ${packageName}.${moduleName}.entity${subModuleName_}.${ClassName3};
import ${packageName}.${moduleName}.service${subModuleName_}.${ClassName}Service;
import ${packageName}.${moduleName}.service${subModuleName_}.${ClassName3}Service;

/**
 * ${functionName}Controller
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Controller
@RequestMapping(value = "${r"${adminPath}"}/${urlPrefix}")
public class ${ClassName3}Controller extends BaseController {

	@Autowired
	private ${ClassName3}Service ${className3}Service;
	@Autowired
	private ${ClassName}Service ${className}Service;
	
	@ModelAttribute
	public ${ClassName3} get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return ${className3}Service.get(id);
		}else{
			return new ${ClassName3}();
		}
	}
	
	@RequiresPermissions("${permissionPrefix}:view")
	@RequestMapping(value = {"list", ""})
	public String list(${ClassName3} ${className3},Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			${className3}.setCreateBy(user);
		}
		String ${className}Id=request.getParameter("${className}Id");
		model.addAttribute("${className}Id", ${className}Id);
		Page<${ClassName3}> paramPage = new Page<${ClassName3}>(request, response);
        Page<${ClassName3}> page = ${className3}Service.find(paramPage, ${className3},${className}Id); 
        model.addAttribute("page", page);
		return "modules/" + "${viewPrefix}List";
	}

	@RequiresPermissions("${permissionPrefix}:view")
	@RequestMapping(value = "form")
	public String form(${ClassName3} ${className3}, Model model) {
		String ${className}Id=null;
		if(${className3}.get${ClassName}()!=null&&StringUtils.isNotBlank(${className3}.get${ClassName}().getId())){
			${className}Id=${className3}.get${ClassName}().getId();
			${className3}.set${ClassName}(${className}Service.get(${className}Id));
		}
		if(StringUtils.isBlank(${className3}.getId())){//添加给个编码
			List<${ClassName3}> list=${className3}Service.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("${className3}", ${className3});
		return "modules/" + "${viewPrefix}Form";
	}

	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "save")
	public String save(${ClassName3} ${className3}, Model model, RedirectAttributes redirectAttributes) {
		String ${className}Id = ${className3}.get${ClassName}()!=null?${className3}.get${ClassName}().getId():"";
		if (!beanValidator(model, ${className3})){
			return form(${className3}, model);
		}
		${className3}Service.save(${className3});
		addMessage(redirectAttributes, "保存${functionName}'" + ${className3}.getName() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?${className}Id="+${className}Id;
	}
	
	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String ${className}Id =${className3}Service.get(id).get${ClassName}().getId();
		${className3}Service.delete(id);
		addMessage(redirectAttributes, "删除${functionName}成功");
		return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?${className}Id="+(${className}Id!=null?${className}Id:"");
	}
	
	@RequiresPermissions("${permissionPrefix}:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String ${className}Id =${className3}Service.get(ids[0]).get${ClassName}().getId();
		for(String id:ids){
			${className3}Service.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个${functionName}成功");
		return "redirect:"+Global.getAdminPath()+"/${urlPrefix}/?${className}Id="+(${className}Id!=null?${className}Id:"");
	}
	
	@RequiresPermissions("${permissionPrefix}:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(${ClassName3} ${className3}, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String ${className}Id=request.getParameter("${className}Id");
		try {
			String fileName = "${functionName}数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<${ClassName3}> page = ${className3}Service.find(new Page<${ClassName3}>(request, response), ${className3},null); 
    		new ExportExcel("${functionName}数据", ${ClassName3}.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出${functionName}失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/${urlPrefix}/?${className}Id="+${className}Id;
    }

}
