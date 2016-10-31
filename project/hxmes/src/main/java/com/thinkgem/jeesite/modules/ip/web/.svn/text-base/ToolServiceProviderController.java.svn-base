/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

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
import com.thinkgem.jeesite.modules.ip.entity.ToolServiceProvider;
import com.thinkgem.jeesite.modules.ip.service.ProviderService;
import com.thinkgem.jeesite.modules.ip.service.ToolService;
import com.thinkgem.jeesite.modules.ip.service.ToolServiceProviderService;

/**
 * 服务商Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/toolServiceProvider")
public class ToolServiceProviderController extends BaseController {

	@Autowired
	private ToolServiceProviderService toolServiceProviderService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private ProviderService providerService;
	
	
	@ModelAttribute
	public ToolServiceProvider get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return toolServiceProviderService.get(id);
		}else{
			return new ToolServiceProvider();
		}
	}
	
	@RequiresPermissions("ip:toolServiceProvider:view")
	@RequestMapping(value = {"list", ""})
	public String list(ToolServiceProvider toolServiceProvider,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			toolServiceProvider.setCreateBy(user);
		}
		String toolId=request.getParameter("toolId");
		model.addAttribute("toolId", toolId);
		Page<ToolServiceProvider> paramPage = new Page<ToolServiceProvider>(request, response);
        Page<ToolServiceProvider> page = toolServiceProviderService.find(paramPage, toolServiceProvider,toolId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/toolServiceProviderList";
	}

	@RequiresPermissions("ip:toolServiceProvider:view")
	@RequestMapping(value = "form")
	public String form(ToolServiceProvider toolServiceProvider, Model model) {
		String toolId=null;
		if(toolServiceProvider.getTool()!=null&&StringUtils.isNotBlank(toolServiceProvider.getTool().getId())){
			toolId=toolServiceProvider.getTool().getId();
			toolServiceProvider.setTool(toolService.get(toolId));
		}
		if(StringUtils.isBlank(toolServiceProvider.getId())){//添加给个编码
			List<ToolServiceProvider> list=toolServiceProviderService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("toolServiceProvider", toolServiceProvider);
		return "modules/" + "ip/toolServiceProviderForm";
	}

	@RequiresPermissions("ip:toolServiceProvider:edit")
	@RequestMapping(value = "save")
	public String save(ToolServiceProvider toolServiceProvider, Model model, RedirectAttributes redirectAttributes) {
		String toolId = toolServiceProvider.getTool()!=null?toolServiceProvider.getTool().getId():"";
		if (!beanValidator(model, toolServiceProvider)){
			return form(toolServiceProvider, model);
		}
		toolServiceProviderService.save(toolServiceProvider);
		addMessage(redirectAttributes, "保存服务商'" + providerService.get(toolServiceProvider.getProvider().getId()).getGysmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/toolServiceProvider/?toolId="+toolId;
	}
	
	@RequiresPermissions("ip:toolServiceProvider:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String toolId =toolServiceProviderService.get(id).getTool().getId();
		toolServiceProviderService.delete(id);
		addMessage(redirectAttributes, "删除服务商成功");
		return "redirect:"+Global.getAdminPath()+"/ip/toolServiceProvider/?toolId="+(toolId!=null?toolId:"");
	}
	
	@RequiresPermissions("ip:toolServiceProvider:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String toolId =toolServiceProviderService.get(ids[0]).getTool().getId();
		for(String id:ids){
			toolServiceProviderService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个服务商成功");
		return "redirect:"+Global.getAdminPath()+"/ip/toolServiceProvider/?toolId="+(toolId!=null?toolId:"");
	}
	
	@RequiresPermissions("ip:toolServiceProvider:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ToolServiceProvider toolServiceProvider, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String toolId=request.getParameter("toolId");
		try {
			String fileName = "服务商数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<ToolServiceProvider> page = toolServiceProviderService.find(new Page<ToolServiceProvider>(request, response), toolServiceProvider,null); 
    		new ExportExcel("服务商数据", ToolServiceProvider.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出服务商失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/toolServiceProvider/?toolId="+toolId;
    }

}
