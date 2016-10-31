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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentServiceProvider;
import com.thinkgem.jeesite.modules.ip.service.EquipmentService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentServiceProviderService;
import com.thinkgem.jeesite.modules.ip.service.ProviderService;

/**
 * 服务商Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentServiceProvider")
public class EquipmentServiceProviderController extends BaseController {

	@Autowired
	private EquipmentServiceProviderService equipmentServiceProviderService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private ProviderService providerService;
	
	@ModelAttribute
	public EquipmentServiceProvider get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentServiceProviderService.get(id);
		}else{
			return new EquipmentServiceProvider();
		}
	}
	
	@RequiresPermissions("ip:equipmentServiceProvider:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentServiceProvider equipmentServiceProvider,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentServiceProvider.setCreateBy(user);
		}
		String equipmentId=request.getParameter("equipmentId");
		model.addAttribute("equipmentId", equipmentId);
		Page<EquipmentServiceProvider> paramPage = new Page<EquipmentServiceProvider>(request, response);
        Page<EquipmentServiceProvider> page = equipmentServiceProviderService.find(paramPage, equipmentServiceProvider,equipmentId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentServiceProviderList";
	}

	@RequiresPermissions("ip:equipmentServiceProvider:view")
	@RequestMapping(value = "form")
	public String form(EquipmentServiceProvider equipmentServiceProvider, Model model) {
		String equipmentId=null;
		if(equipmentServiceProvider.getEquipment()!=null&&StringUtils.isNotBlank(equipmentServiceProvider.getEquipment().getId())){
			equipmentId=equipmentServiceProvider.getEquipment().getId();
			equipmentServiceProvider.setEquipment(equipmentService.get(equipmentId));
		}
		if(StringUtils.isBlank(equipmentServiceProvider.getId())){//添加给个编码
			List<EquipmentServiceProvider> list=equipmentServiceProviderService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentServiceProvider", equipmentServiceProvider);
		return "modules/" + "ip/equipmentServiceProviderForm";
	}

	@RequiresPermissions("ip:equipmentServiceProvider:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentServiceProvider equipmentServiceProvider, Model model, RedirectAttributes redirectAttributes) {
		String equipmentId = equipmentServiceProvider.getEquipment()!=null?equipmentServiceProvider.getEquipment().getId():"";
		if (!beanValidator(model, equipmentServiceProvider)){
			return form(equipmentServiceProvider, model);
		}
		equipmentServiceProviderService.save(equipmentServiceProvider);
		addMessage(redirectAttributes, "保存服务商'" + providerService.get(equipmentServiceProvider.getProvider().getId()).getGysmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentServiceProvider/?equipmentId="+equipmentId;
	}
	
	@RequiresPermissions("ip:equipmentServiceProvider:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String equipmentId =equipmentServiceProviderService.get(id).getEquipment().getId();
		equipmentServiceProviderService.delete(id);
		addMessage(redirectAttributes, "删除服务商成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentServiceProvider/?equipmentId="+(equipmentId!=null?equipmentId:"");
	}
	
	@RequiresPermissions("ip:equipmentServiceProvider:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentId =equipmentServiceProviderService.get(ids[0]).getEquipment().getId();
		for(String id:ids){
			equipmentServiceProviderService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个服务商成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentServiceProvider/?equipmentId="+(equipmentId!=null?equipmentId:"");
	}
	
	@RequiresPermissions("ip:equipmentServiceProvider:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentServiceProvider equipmentServiceProvider, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentId=request.getParameter("equipmentId");
		try {
			String fileName = "服务商数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentServiceProvider> page = equipmentServiceProviderService.find(new Page<EquipmentServiceProvider>(request, response), equipmentServiceProvider,null); 
    		new ExportExcel("服务商数据", EquipmentServiceProvider.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出服务商失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentServiceProvider/?equipmentId="+equipmentId;
    }

}
