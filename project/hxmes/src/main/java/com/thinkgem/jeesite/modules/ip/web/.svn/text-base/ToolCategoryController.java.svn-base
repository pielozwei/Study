/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
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
import com.thinkgem.jeesite.modules.ip.entity.ToolCategory;
import com.thinkgem.jeesite.modules.ip.service.ToolCategoryService;

/**
 * 工器具分类Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/toolCategory")
public class ToolCategoryController extends BaseController {

	@Autowired
	private ToolCategoryService toolCategoryService;
	
	@ModelAttribute
	public ToolCategory get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return toolCategoryService.get(id);
		}else{
			return new ToolCategory();
		}
	}
	
	@RequiresPermissions("ip:toolCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(ToolCategory toolCategory,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			toolCategory.setCreateBy(user);
		}
		String nodeId=request.getParameter("nodeId");
		Page<ToolCategory> paramPage = new Page<ToolCategory>(request, response);
        Page<ToolCategory> page = toolCategoryService.find(paramPage, toolCategory, nodeId); 
        model.addAttribute("page", page);
        model.addAttribute("nodeId", nodeId);
        System.out.println("hdgydgh ");
		return "modules/" + "ip/toolCategoryList";
	}

	@RequiresPermissions("ip:toolCategory:view")
	@RequestMapping(value = "form")
	public String form(ToolCategory toolCategory, String nodeId, Model model) {
		if(StringUtils.isBlank(toolCategory.getId())){//添加给个编码
			List<ToolCategory> list=toolCategoryService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("toolCategory", toolCategory);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/toolCategoryForm";
	}

	@RequiresPermissions("ip:toolCategory:edit")
	@RequestMapping(value = "save")
	public String save(ToolCategory toolCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, toolCategory)){
			return form(toolCategory, "1", model);
		}
		if(StringUtils.isNotBlank(toolCategory.getId()) 
				&& hasChildNode(toolCategory.getId())){
			addMessage(redirectAttributes, "保存工器具分类'" + toolCategory.getLbmc() + "'失败：该节点有子节点不能修改！");
		}
		if(StringUtils.isNotBlank(toolCategory.getId()) 
				&& toolCategory.getId().equals(toolCategory.getParent().getId())){//父节点为自己
			addMessage(redirectAttributes, "保存工器具分类'" + toolCategory.getLbmc() + "'失败：不能选择自己作为父节点！");
		}else{
			toolCategoryService.save(toolCategory);
			addMessage(redirectAttributes, "保存工器具分类'" + toolCategory.getLbmc() + "'成功");
		}
		return "redirect:"+Global.getAdminPath()+"/ip/tool/none";
	}
	/*该节点是否有子节点*/
	public boolean hasChildNode(String nodeId){
		boolean b=false;
		if(toolCategoryService.findSun(nodeId).size()>0)
			b=true;
		
		return b;
	}
	
	/*该节点是否有信息关联*/
	public boolean hasInfo(String nodeId){
		boolean b=false;
		if(toolCategoryService.findInfo(nodeId).size()>0)
			b=true;
		
		return b;
	}
	@RequiresPermissions("ip:toolCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String nodeId = toolCategoryService.get(id).getParent().getId();
		if(hasChildNode(id)){
			addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
			return "redirect:"+Global.getAdminPath()+"/ip/toolCategory/?nodeId="+nodeId;
		}
		if(hasInfo(id)){
			addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
			return "redirect:"+Global.getAdminPath()+"/ip/toolCategory/?nodeId="+nodeId;
		}
		toolCategoryService.delete(id);
		addMessage(redirectAttributes, "删除工器具分类成功");
		return "redirect:"+Global.getAdminPath()+"/ip/tool/none";
	}
	
	@RequiresPermissions("ip:toolCategory:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId = toolCategoryService.get(ids[0]).getParent().getId();
		for(String id:ids){
			if(hasChildNode(id)){
				addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
				return "redirect:"+Global.getAdminPath()+"/ip/toolCategory/?nodeId="+nodeId;
			}
			if(hasInfo(id)){
				addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
				return "redirect:"+Global.getAdminPath()+"/ip/toolCategory/?nodeId="+nodeId;
			}
			toolCategoryService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个工器具分类成功");
		return "redirect:"+Global.getAdminPath()+"/ip/tool/none";
	}
	
	@RequiresPermissions("ip:toolCategory:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ToolCategory toolCategory, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "工器具分类数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<ToolCategory> page = toolCategoryService.find(new Page<ToolCategory>(request, response), toolCategory,null); 
    		new ExportExcel("工器具分类数据", ToolCategory.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出工器具分类失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/toolCategory/";
    }
    
    @RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String,Object>> treeData(String module, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		return toolCategoryService.getTreeData();
	}
    /**
	 * 批量禁用启用
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "updateStateList")
	public String updateStateList(String[] ids,String state, RedirectAttributes redirectAttributes) {
		int j=0;
		String sjjdId=toolCategoryService.get(ids[0]).getParent().getId();
		for(String id :ids){
			toolCategoryService.updateStateList(id,state);
			j++;
		}
		if(null!=state&&state.equals("1")){
			addMessage(redirectAttributes, "禁用"+j+"个工装器具分类！");
		}else{
			addMessage(redirectAttributes, "启用"+j+"个工装器具分类！");
		}
		return "redirect:"+Global.getAdminPath()+"/ip/toolCategory/list?nodeId="+sjjdId;
	}
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=toolCategoryService.validation(val1);
		JSONObject map=new JSONObject();
		if(!flag){
			map.put("msg", "编码重复");
			map.put("flag", 1);
		}else{
			map.put("msg", "");
			map.put("flag", 0);
		}
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(map.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
