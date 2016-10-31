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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;
import com.thinkgem.jeesite.modules.ip.service.EquipmentCategoryService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;

/**
 * 基本信息Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentModel")
public class EquipmentModelController extends BaseController {

	@Autowired
	private EquipmentModelService equipmentModelService;
	@Autowired
	private EquipmentCategoryService equipmentCategoryService;
	
	@ModelAttribute
	public EquipmentModel get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentModelService.get(id);
		}else{
			return new EquipmentModel();
		}
	}
	
	@RequiresPermissions("ip:equipmentModel:view")
	@RequestMapping(value = "")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="equipmentModel";
		}
        model.addAttribute("module", module);
		return "modules/" + "ip/equipmentModelIndex";
	}
	
	@RequiresPermissions("ip:equipmentModel:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="equipmentModel";
		}
		model.addAttribute("_module", module);
		model.addAttribute("module", get_module(module));
		model.addAttribute("equipmentCategoryList", equipmentCategoryService.getTreeData());
		return "modules/" + "ip/equipmentModelTree";
	}
	
	public String get_module(String module) {
		if(!module.equals("equipmentCategory")){
			return "equipmentModel";
		}else{
			return module;
		}
	}
	
	@RequiresPermissions("ip:equipmentModel:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/" + "ip/equipmentModelNone";
	}
	
	@RequiresPermissions("ip:equipmentModel:view")
	@RequestMapping(value = "list")
	public String list(EquipmentModel equipmentModel,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentModel.setCreateBy(user);
		}
		String nodeId=request.getParameter("nodeId");
		Page<EquipmentModel> paramPage = new Page<EquipmentModel>(request, response);
        Page<EquipmentModel> page = equipmentModelService.find(paramPage, equipmentModel,nodeId); 
        model.addAttribute("page", page);
        model.addAttribute("nodeId", nodeId);
        String _module=request.getParameter("_module");
        if (StringUtils.isEmpty(_module)){
			_module="equipmentModel";
		}
        model.addAttribute("_module", _module);
		return "modules/" + "ip/equipmentModelList";
	}

	@RequiresPermissions("ip:equipmentModel:view")
	@RequestMapping(value = "form")
	public String form(EquipmentModel equipmentModel, String nodeId, Model model) {
		if(StringUtils.isBlank(equipmentModel.getId())){//添加给个编码
			List<EquipmentModel> list=equipmentModelService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentModel", equipmentModel);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/equipmentModelForm";
	}

	@RequiresPermissions("ip:equipmentModel:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentModel equipmentModel, Model model, RedirectAttributes redirectAttributes) {
		String nodeId=equipmentModel.getEquipmentCategory().getId();
		if (!beanValidator(model, equipmentModel)){
			return form(equipmentModel, nodeId, model);
		}
		equipmentModelService.save(equipmentModel);
		addMessage(redirectAttributes, "保存基本信息'" + equipmentModel.getSbggxhmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentModel/list?nodeId="+nodeId;
	}
	

	@RequiresPermissions("ip:equipmentModel:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId=equipmentModelService.get(ids[0]).getEquipmentCategory().getId();
		for(String id:ids){
			equipmentModelService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentModel/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:equipmentModel:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentModel equipmentModel, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String nodeId=request.getParameter("nodeId");
		try {
			String fileName = "基本信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentModel> page = equipmentModelService.find(new Page<EquipmentModel>(request, response), equipmentModel,null); 
    		new ExportExcel("基本信息数据", EquipmentModel.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出基本信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentModel/list?nodeId="+nodeId;
    }
	@ResponseBody
	@RequestMapping("checkSbggxhbm")
	public String checkSbggxhbm(String sbggxhbm, String oldSbggxhbm) {
		if (sbggxhbm != null && oldSbggxhbm.equals(sbggxhbm)) {
			return "true";
		} else if (sbggxhbm != null && equipmentModelService.findBySbggxhbm(sbggxhbm) == null) {
			return "true";
		}
		return "false";
	}
}
