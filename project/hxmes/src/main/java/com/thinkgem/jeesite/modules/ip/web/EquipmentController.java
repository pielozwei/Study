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
import com.thinkgem.jeesite.modules.ip.entity.Equipment;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;
import com.thinkgem.jeesite.modules.ip.service.EquipmentFieldService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentService;

/**
 * 基本信息Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipment")
public class EquipmentController extends BaseController {

	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private EquipmentFieldService equipmentFieldService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	
	@ModelAttribute
	public Equipment get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentService.get(id);
		}else{
			return new Equipment();
		}
	}
	
	@RequiresPermissions("ip:equipment:view")
	@RequestMapping(value = "")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="equipment";
		}
        model.addAttribute("module", module);
		return "modules/" + "ip/equipmentIndex";
	}
	
	@RequiresPermissions("ip:equipment:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="equipment";
		}
		model.addAttribute("_module", module);
		model.addAttribute("module", get_module(module));
		model.addAttribute("equipmentFieldList", equipmentFieldService.getTreeData());
		return "modules/" + "ip/equipmentTree";
	}
	
	public String get_module(String module) {
		if(!module.equals("equipmentField")){
			return "equipment";
		}else{
			return module;
		}
	}
	
	@RequiresPermissions("ip:equipment:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/" + "ip/equipmentNone";
	}
	
	@RequiresPermissions("ip:equipment:view")
	@RequestMapping(value = "list")
	public String list(Equipment equipment,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipment.setCreateBy(user);
		}
		String nodeId=request.getParameter("nodeId");
		Page<Equipment> paramPage = new Page<Equipment>(request, response);
        Page<Equipment> page = equipmentService.find(paramPage, equipment,nodeId); 
        model.addAttribute("page", page);
        model.addAttribute("nodeId", nodeId);
        String _module=request.getParameter("_module");
        if (StringUtils.isEmpty(_module)){
			_module="equipment";
		}
        model.addAttribute("_module", _module);
		return "modules/" + "ip/equipmentList";
	}

	@RequiresPermissions("ip:equipment:view")
	@RequestMapping(value = "form")
	public String form(Equipment equipment, String nodeId, Model model) {
		if(StringUtils.isBlank(equipment.getId())){//添加给个编码
			List<Equipment> list=equipmentService.findAll();
			model.addAttribute("bm", list.size()+1);
		} 
		List<EquipmentModel> listSbggxh = equipmentModelService.findAll(); 
        model.addAttribute("listSbggxh", listSbggxh);
		model.addAttribute("equipment", equipment);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/equipmentForm";
	}

	@RequiresPermissions("ip:equipment:edit")
	@RequestMapping(value = "save")
	public String save(Equipment equipment, Model model, RedirectAttributes redirectAttributes) {
		String nodeId=equipment.getEquipmentField().getId();
		if (!beanValidator(model, equipment)){
			return form(equipment, nodeId, model);
		}
		if((null!=equipment&&(null!=equipment.getXssx()&&equipment.getXssx().equals("")))||(null!=equipment&&null==equipment.getXssx())){
			equipment.setXssx(100);
		}
		equipmentService.save(equipment);
		addMessage(redirectAttributes, "保存基本信息'" + equipmentModelService.get(equipment.getEquipmentModel().getId()).getSbggxhmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipment/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:equipment:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String nodeId=equipmentService.get(id).getEquipmentField().getId();
		equipmentService.delete(id);
		addMessage(redirectAttributes, "删除基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipment/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:equipment:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId=equipmentService.get(ids[0]).getEquipmentField().getId();
		for(String id:ids){
			equipmentService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipment/list?nodeId="+nodeId;
	}
	
	@RequiresPermissions("ip:equipment:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(Equipment equipment, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String nodeId=request.getParameter("nodeId");
		try {
			//String fileName = "基本信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		//Page<Equipment> page = equipmentService.find(new Page<Equipment>(request, response), equipment,null); 
    		//new ExportExcel("基本信息数据", Equipment.class).setDataList(page.getList()).write(response, fileName).dispose();
			String title = "设备基本信息数据";
			String fileName = "设备基本信息数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
			String[] headers = {"id","编码"};
			String columns = "id,sbbm";
			String orderBy = "sbbm";
			int start = 0;
			int end = 10;
			List<Object[]> dataList = equipmentService.findByColumn(columns,orderBy,start,end);
			ExportExcel.export(title, fileName, headers, dataList, response);
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出基本信息失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipment/list?nodeId="+nodeId;
    }
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=equipmentService.validation(val1);
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
