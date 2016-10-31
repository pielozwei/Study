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
import com.thinkgem.jeesite.modules.ip.entity.ToolTechParam;
import com.thinkgem.jeesite.modules.ip.service.ToolService;
import com.thinkgem.jeesite.modules.ip.service.ToolTechParamService;

/**
 * 关键技术参数Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/toolTechParam")
public class ToolTechParamController extends BaseController {

	@Autowired
	private ToolTechParamService toolTechParamService;
	@Autowired
	private ToolService toolService;
	
	@ModelAttribute
	public ToolTechParam get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return toolTechParamService.get(id);
		}else{
			return new ToolTechParam();
		}
	}
	
	@RequiresPermissions("ip:toolTechParam:view")
	@RequestMapping(value = {"list", ""})
	public String list(ToolTechParam toolTechParam,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			toolTechParam.setCreateBy(user);
		}
		String toolId=request.getParameter("toolId");
		model.addAttribute("toolId", toolId);
		Page<ToolTechParam> paramPage = new Page<ToolTechParam>(request, response);
        Page<ToolTechParam> page = toolTechParamService.find(paramPage, toolTechParam,toolId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/toolTechParamList";
	}

	@RequiresPermissions("ip:toolTechParam:view")
	@RequestMapping(value = "form")
	public String form(ToolTechParam toolTechParam, Model model) {
		String toolId=null;
		if(toolTechParam.getTool()!=null&&StringUtils.isNotBlank(toolTechParam.getTool().getId())){
			toolId=toolTechParam.getTool().getId();
			toolTechParam.setTool(toolService.get(toolId));
		}
		if(StringUtils.isBlank(toolTechParam.getId())){//添加给个编码
			List<ToolTechParam> list=toolTechParamService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("toolTechParam", toolTechParam);
		return "modules/" + "ip/toolTechParamForm";
	}

	@RequiresPermissions("ip:toolTechParam:edit")
	@RequestMapping(value = "save")
	public String save(ToolTechParam toolTechParam, Model model, RedirectAttributes redirectAttributes) {
		String toolId = toolTechParam.getTool()!=null?toolTechParam.getTool().getId():"";
		if (!beanValidator(model, toolTechParam)){
			return form(toolTechParam, model);
		}
		toolTechParamService.save(toolTechParam);
		addMessage(redirectAttributes, "保存关键技术参数'" + toolTechParam.getCsmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/toolTechParam/?toolId="+toolId;
	}
	
	@RequiresPermissions("ip:toolTechParam:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String toolId =toolTechParamService.get(id).getTool().getId();
		toolTechParamService.delete(id);
		addMessage(redirectAttributes, "删除关键技术参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/toolTechParam/?toolId="+(toolId!=null?toolId:"");
	}
	
	@RequiresPermissions("ip:toolTechParam:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String toolId =toolTechParamService.get(ids[0]).getTool().getId();
		for(String id:ids){
			toolTechParamService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个关键技术参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/toolTechParam/?toolId="+(toolId!=null?toolId:"");
	}
	
	@RequiresPermissions("ip:toolTechParam:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ToolTechParam toolTechParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String toolId=request.getParameter("toolId");
		try {
			String fileName = "关键技术参数数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<ToolTechParam> page = toolTechParamService.find(new Page<ToolTechParam>(request, response), toolTechParam,null); 
    		new ExportExcel("关键技术参数数据", ToolTechParam.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出关键技术参数失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/toolTechParam/?toolId="+toolId;
    }
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=toolTechParamService.validation(val1);
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
