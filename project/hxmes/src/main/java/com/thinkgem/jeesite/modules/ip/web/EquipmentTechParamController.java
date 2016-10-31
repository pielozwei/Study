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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentTechParam;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentTechParamService;

/**
 * 技术参数Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentTechParam")
public class EquipmentTechParamController extends BaseController {

	@Autowired
	private EquipmentTechParamService equipmentTechParamService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	
	@ModelAttribute
	public EquipmentTechParam get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentTechParamService.get(id);
		}else{
			return new EquipmentTechParam();
		}
	}
	
	@RequiresPermissions("ip:equipmentTechParam:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentTechParam equipmentTechParam,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentTechParam.setCreateBy(user);
		}
		String equipmentModelId=request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentTechParam> paramPage = new Page<EquipmentTechParam>(request, response);
        Page<EquipmentTechParam> page = equipmentTechParamService.find(paramPage, equipmentTechParam,equipmentModelId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentTechParamList";
	}

	@RequiresPermissions("ip:equipmentTechParam:view")
	@RequestMapping(value = "form")
	public String form(EquipmentTechParam equipmentTechParam, Model model) {
		String equipmentModelId=null;
		if(equipmentTechParam.getEquipmentModel()!=null&&StringUtils.isNotBlank(equipmentTechParam.getEquipmentModel().getId())){
			equipmentModelId=equipmentTechParam.getEquipmentModel().getId();
			equipmentTechParam.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if(StringUtils.isBlank(equipmentTechParam.getId())){//添加给个编码
			List<EquipmentTechParam> list=equipmentTechParamService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentTechParam", equipmentTechParam);
		return "modules/" + "ip/equipmentTechParamForm";
	}

	@RequiresPermissions("ip:equipmentTechParam:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentTechParam equipmentTechParam, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentTechParam.getEquipmentModel()!=null?equipmentTechParam.getEquipmentModel().getId():"";
		if (!beanValidator(model, equipmentTechParam)){
			return form(equipmentTechParam, model);
		}
		equipmentTechParamService.save(equipmentTechParam);
		addMessage(redirectAttributes, "保存技术参数'" + equipmentTechParam.getCsmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentTechParam/?equipmentModelId="+equipmentModelId;
	}
	
	
	@RequiresPermissions("ip:equipmentTechParam:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId =equipmentTechParamService.get(ids[0]).getEquipmentModel().getId();
		for(String id:ids){
			equipmentTechParamService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个技术参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentTechParam/?equipmentModelId="+(equipmentModelId!=null?equipmentModelId:"");
	}
	
	@RequiresPermissions("ip:equipmentTechParam:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentTechParam equipmentTechParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			String fileName = "技术参数数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentTechParam> page = equipmentTechParamService.find(new Page<EquipmentTechParam>(request, response), equipmentTechParam,null); 
    		new ExportExcel("技术参数数据", EquipmentTechParam.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出技术参数失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentTechParam/?equipmentModelId="+equipmentModelId;
    }
	@ResponseBody
	@RequestMapping("checkCsbm")
	public String checkCsbm(String csbm,String oldCsbm){
		if (csbm != null && oldCsbm.equals(csbm)) {
			return "true";
		}else if (csbm != null && equipmentTechParamService.findByCsbm(csbm) == null) {
			return "true";
		}
		return "false";
	}
}
