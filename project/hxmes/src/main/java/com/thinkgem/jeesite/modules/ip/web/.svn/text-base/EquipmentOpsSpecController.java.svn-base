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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentOpsSpec;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentOpsSpecService;

/**
 * 操作规程Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentOpsSpec")
public class EquipmentOpsSpecController extends BaseController {

	@Autowired
	private EquipmentOpsSpecService equipmentOpsSpecService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	
	@ModelAttribute
	public EquipmentOpsSpec get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentOpsSpecService.get(id);
		}else{
			return new EquipmentOpsSpec();
		}
	}
	
	@RequiresPermissions("ip:equipmentOpsSpec:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentOpsSpec equipmentOpsSpec,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentOpsSpec.setCreateBy(user);
		}
		String equipmentModelId=request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentOpsSpec> paramPage = new Page<EquipmentOpsSpec>(request, response);
        Page<EquipmentOpsSpec> page = equipmentOpsSpecService.find(paramPage, equipmentOpsSpec,equipmentModelId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentOpsSpecList";
	}

	@RequiresPermissions("ip:equipmentOpsSpec:view")
	@RequestMapping(value = "form")
	public String form(EquipmentOpsSpec equipmentOpsSpec, Model model) {
		String equipmentModelId=null;
		if(equipmentOpsSpec.getEquipmentModel()!=null&&StringUtils.isNotBlank(equipmentOpsSpec.getEquipmentModel().getId())){
			equipmentModelId=equipmentOpsSpec.getEquipmentModel().getId();
			equipmentOpsSpec.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if(StringUtils.isBlank(equipmentOpsSpec.getId())){//添加给个编码
			List<EquipmentOpsSpec> list=equipmentOpsSpecService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentOpsSpec", equipmentOpsSpec);
		return "modules/" + "ip/equipmentOpsSpecForm";
	}

	@RequiresPermissions("ip:equipmentOpsSpec:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentOpsSpec equipmentOpsSpec, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentOpsSpec.getEquipmentModel()!=null?equipmentOpsSpec.getEquipmentModel().getId():"";
		if (!beanValidator(model, equipmentOpsSpec)){
			return form(equipmentOpsSpec, model);
		}
		equipmentOpsSpecService.save(equipmentOpsSpec);
		addMessage(redirectAttributes, "保存操作规程'" + /*equipmentOpsSpec.getGflb() + */"'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentOpsSpec/?equipmentModelId="+equipmentModelId;
	}
	
	
	@RequiresPermissions("ip:equipmentOpsSpec:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId =equipmentOpsSpecService.get(ids[0]).getEquipmentModel().getId();
		for(String id:ids){
			equipmentOpsSpecService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个操作规程成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentOpsSpec/?equipmentModelId="+(equipmentModelId!=null?equipmentModelId:"");
	}
	
	@RequiresPermissions("ip:equipmentOpsSpec:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentOpsSpec equipmentOpsSpec, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			String fileName = "操作规程数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentOpsSpec> page = equipmentOpsSpecService.find(new Page<EquipmentOpsSpec>(request, response), equipmentOpsSpec,null); 
    		new ExportExcel("操作规程数据", EquipmentOpsSpec.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出操作规程失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentOpsSpec/?equipmentModelId="+equipmentModelId;
    }
	@ResponseBody
	@RequestMapping("checkGcmc")
	public String checkGcmc(String gcmc, String oldGcmc) {
		if (gcmc != null && oldGcmc.equals(gcmc)) {
			return "true";
		} else if (gcmc != null && equipmentOpsSpecService.findByGcmc(gcmc) == null) {
			return "true";
		}
		return "false";
	}
}
