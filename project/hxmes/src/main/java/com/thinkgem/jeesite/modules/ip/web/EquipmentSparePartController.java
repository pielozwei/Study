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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentSparePart;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentSparePartService;
import com.thinkgem.jeesite.modules.ip.service.MaterialService;

/**
 * 备品备件Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentSparePart")
public class EquipmentSparePartController extends BaseController {

	@Autowired
	private EquipmentSparePartService equipmentSparePartService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	@Autowired
	private MaterialService wlbmService;
	@ModelAttribute
	public EquipmentSparePart get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentSparePartService.get(id);
		}else{
			return new EquipmentSparePart();
		}
	}
	
	@RequiresPermissions("ip:equipmentSparePart:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentSparePart equipmentSparePart,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentSparePart.setCreateBy(user);
		}
		String equipmentModelId=request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentSparePart> paramPage = new Page<EquipmentSparePart>(request, response);
        Page<EquipmentSparePart> page = equipmentSparePartService.find(paramPage, equipmentSparePart,equipmentModelId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentSparePartList";
	}

	@RequiresPermissions("ip:equipmentSparePart:view")
	@RequestMapping(value = "form")
	public String form(EquipmentSparePart equipmentSparePart, Model model) {
		String equipmentModelId=null;
		if(equipmentSparePart.getEquipmentModel()!=null&&StringUtils.isNotBlank(equipmentSparePart.getEquipmentModel().getId())){
			equipmentModelId=equipmentSparePart.getEquipmentModel().getId();
			equipmentSparePart.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if(StringUtils.isBlank(equipmentSparePart.getId())){//添加给个编码
			List<EquipmentSparePart> list=equipmentSparePartService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		List<Material> materials =  wlbmService.wlfindAll(); 
        model.addAttribute("listMaterial", materials);
		model.addAttribute("equipmentSparePart", equipmentSparePart);
		return "modules/" + "ip/equipmentSparePartForm";
	}

	@RequiresPermissions("ip:equipmentSparePart:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentSparePart equipmentSparePart, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentSparePart.getEquipmentModel()!=null?equipmentSparePart.getEquipmentModel().getId():"";
		if (!beanValidator(model, equipmentSparePart)){
			return form(equipmentSparePart, model);
		}
		equipmentSparePartService.save(equipmentSparePart);
//		addMessage(redirectAttributes, "保存备品备件'" + equipmentSparePart.getBjmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentSparePart/?equipmentModelId="+equipmentModelId;
	}
	
	@RequiresPermissions("ip:equipmentSparePart:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId =equipmentSparePartService.get(ids[0]).getEquipmentModel().getId();
		for(String id:ids){
			equipmentSparePartService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个备品备件成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentSparePart/?equipmentModelId="+(equipmentModelId!=null?equipmentModelId:"");
	}
	
	@RequiresPermissions("ip:equipmentSparePart:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentSparePart equipmentSparePart, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			String fileName = "备品备件数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentSparePart> page = equipmentSparePartService.find(new Page<EquipmentSparePart>(request, response), equipmentSparePart,null); 
    		new ExportExcel("备品备件数据", EquipmentSparePart.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出备品备件失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentSparePart/?equipmentModelId="+equipmentModelId;
    }

}
