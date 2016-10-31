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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentBOMSheet;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentBOMSheetService;

/**
 * 设备BOMController
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentBOMSheet")
public class EquipmentBOMSheetController extends BaseController {

	@Autowired
	private EquipmentBOMSheetService equipmentBOMSheetService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	
	@ModelAttribute
	public EquipmentBOMSheet get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentBOMSheetService.get(id);
		}else{
			return new EquipmentBOMSheet();
		}
	}
	
	@RequiresPermissions("ip:equipmentBOMSheet:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentBOMSheet equipmentBOMSheet,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentBOMSheet.setCreateBy(user);
		}
		String equipmentModelId=request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentBOMSheet> paramPage = new Page<EquipmentBOMSheet>(request, response);
        Page<EquipmentBOMSheet> page = equipmentBOMSheetService.find(paramPage, equipmentBOMSheet,equipmentModelId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentBOMSheetList";
	}

	@RequiresPermissions("ip:equipmentBOMSheet:view")
	@RequestMapping(value = "form")
	public String form(EquipmentBOMSheet equipmentBOMSheet, Model model) {
		String equipmentModelId=null;
		if(equipmentBOMSheet.getEquipmentModel()!=null&&StringUtils.isNotBlank(equipmentBOMSheet.getEquipmentModel().getId())){
			equipmentModelId=equipmentBOMSheet.getEquipmentModel().getId();
			equipmentBOMSheet.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if(StringUtils.isBlank(equipmentBOMSheet.getId())){//添加给个编码
			List<EquipmentBOMSheet> list=equipmentBOMSheetService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentBOMSheet", equipmentBOMSheet);
		return "modules/" + "ip/equipmentBOMSheetForm";
	}

	@RequiresPermissions("ip:equipmentBOMSheet:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentBOMSheet equipmentBOMSheet, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentBOMSheet.getEquipmentModel()!=null?equipmentBOMSheet.getEquipmentModel().getId():"";
		if (!beanValidator(model, equipmentBOMSheet)){
			return form(equipmentBOMSheet, model);
		}
		equipmentBOMSheetService.save(equipmentBOMSheet);
		addMessage(redirectAttributes, "保存设备BOM'" + equipmentBOMSheet.getSbbomdbh() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentBOMSheet/?equipmentModelId="+equipmentModelId;
	}
	
	@RequiresPermissions("ip:equipmentBOMSheet:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId =equipmentBOMSheetService.get(ids[0]).getEquipmentModel().getId();
		for(String id:ids){
			equipmentBOMSheetService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个设备BOM成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentBOMSheet/?equipmentModelId="+(equipmentModelId!=null?equipmentModelId:"");
	}
	
	@RequiresPermissions("ip:equipmentBOMSheet:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentBOMSheet equipmentBOMSheet, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			String fileName = "设备BOM数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentBOMSheet> page = equipmentBOMSheetService.find(new Page<EquipmentBOMSheet>(request, response), equipmentBOMSheet,null); 
    		new ExportExcel("设备BOM数据", EquipmentBOMSheet.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出设备BOM失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentBOMSheet/?equipmentModelId="+equipmentModelId;
    }
	@ResponseBody
	@RequestMapping("checkSbbomdbh")
	public String checkSbbomdbh(String sbbomdbh, String oldSbbomdbh) {
		if (sbbomdbh != null && oldSbbomdbh.equals(sbbomdbh)) {
			return "true";
		} else if (sbbomdbh != null && equipmentBOMSheetService.findBySbbomdbh(sbbomdbh) == null) {
			return "true";
		}
		return "false";
	}

}
