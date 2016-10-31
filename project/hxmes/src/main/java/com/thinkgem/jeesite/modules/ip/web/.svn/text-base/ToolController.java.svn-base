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
import com.thinkgem.jeesite.modules.ip.entity.Tool;
import com.thinkgem.jeesite.modules.ip.service.ToolCategoryService;
import com.thinkgem.jeesite.modules.ip.service.ToolService;

/**
 * 基本信息Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/tool")
public class ToolController extends BaseController {

	@Autowired
	private ToolService toolService;
	@Autowired
	private ToolCategoryService toolCategoryService;
	
	@ModelAttribute
	public Tool get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return toolService.get(id);
		}else{
			return new Tool();
		}
	}
	
	@RequiresPermissions("ip:tool:view")
	@RequestMapping(value = "")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="tool";
		}
        model.addAttribute("module", module);
		return "modules/" + "ip/toolIndex";
	}
	
	@RequiresPermissions("ip:tool:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="tool";
		}
		model.addAttribute("_module", module);
		model.addAttribute("module", get_module(module));
		model.addAttribute("toolCategoryList", toolCategoryService.getTreeData());
		return "modules/" + "ip/toolTree";
	}
	
	public String get_module(String module) {
		if(!module.equals("toolCategory")){
			return "tool";
		}else{
			return module;
		}
	}
	
	@RequiresPermissions("ip:tool:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/" + "ip/toolNone";
	}
	
	@RequiresPermissions("ip:tool:view")
	@RequestMapping(value = "list")
	public String list(Tool tool,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			tool.setCreateBy(user);
		}
		String nodeId=request.getParameter("nodeId");
		Page<Tool> paramPage = new Page<Tool>(request, response);
        Page<Tool> page = toolService.find(paramPage, tool,nodeId); 
        model.addAttribute("page", page);
        model.addAttribute("nodeId", nodeId);
        String _module=request.getParameter("_module");
        if (StringUtils.isEmpty(_module)){
			_module="tool";
		}
        model.addAttribute("_module", _module);
		return "modules/" + "ip/toolList";
	}

	@RequiresPermissions("ip:tool:view")
	@RequestMapping(value = "form")
	public String form(Tool tool, String nodeId, Model model) {
		if(StringUtils.isBlank(tool.getId())){//添加给个编码
			List<Tool> list=toolService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("tool", tool);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/toolForm";
	}

	@RequiresPermissions("ip:tool:edit")
	@RequestMapping(value = "save")
	public String save(Tool tool, Model model, RedirectAttributes redirectAttributes) {
		String nodeId=tool.getGzqjlbId().getId();
		if (!beanValidator(model, tool)){
			return form(tool, nodeId, model);
		}
		toolService.save(tool);
		addMessage(redirectAttributes, "保存基本信息'" + tool.getGzqjmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/tool/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:tool:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String nodeId=toolService.get(id).getGzqjlbId().getId();
		toolService.delete(id);
		addMessage(redirectAttributes, "删除基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/tool/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:tool:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId=toolService.get(ids[0]).getGzqjlbId().getId();
		for(String id:ids){
			toolService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/tool/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:tool:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Tool tool, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String nodeId=request.getParameter("nodeId");
		try {
			String fileName = "基本信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<Tool> page = toolService.find(new Page<Tool>(request, response), tool,null); 
    		new ExportExcel("基本信息数据", Tool.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出基本信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/tool/list?nodeId="+nodeId;
    }
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=toolService.validation(val1);
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
