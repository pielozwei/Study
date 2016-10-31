/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
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
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentField;
import com.thinkgem.jeesite.modules.ip.service.EquipmentFieldService;

/**
 * 设备层次Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentField")
public class EquipmentFieldController extends BaseController {

	@Autowired
	private EquipmentFieldService equipmentFieldService;
	
	@ModelAttribute
	public EquipmentField get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentFieldService.get(id);
		}else{
			return new EquipmentField();
		}
	}
	
	@RequiresPermissions("ip:equipmentField:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentField equipmentField,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentField.setCreateBy(user);
		}
		String nodeId=request.getParameter("nodeId");
		Page<EquipmentField> paramPage = new Page<EquipmentField>(request, response);
        Page<EquipmentField> page = equipmentFieldService.find(paramPage, equipmentField, nodeId); 
        model.addAttribute("page", page);
        model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/equipmentFieldList";
	}

	@RequiresPermissions("ip:equipmentField:view")
	@RequestMapping(value = "form")
	public String form(EquipmentField equipmentField, String nodeId, Model model) {
		if(StringUtils.isBlank(equipmentField.getId())){//添加给个编码
			List<EquipmentField> list=equipmentFieldService.findAll();
			List<Dict> dict=DictUtils.getDictList("d_sbcclx");
			model.addAttribute("bm", list.size()+1);
			model.addAttribute("dict", dict);
		}
		List<Dict> dict=DictUtils.getDictList("d_sbcclx");
		model.addAttribute("dict", dict);
		model.addAttribute("equipmentField", equipmentField);
		model.addAttribute("nodeId", nodeId);
		return "modules/" + "ip/equipmentFieldForm";
	}

	@RequiresPermissions("ip:equipmentField:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentField equipmentField, Model model, RedirectAttributes redirectAttributes) {
		if(null!=equipmentField&&equipmentField.getXssx()==null){
			equipmentField.setXssx(500);
		}
		if (!beanValidator(model, equipmentField)){
			return form(equipmentField, "1", model);
		}
		if(StringUtils.isNotBlank(equipmentField.getId()) 
				&& hasChildNode(equipmentField.getId())){
			addMessage(redirectAttributes, "保存设备层次'" + equipmentField.getCcmc() + "'失败：该节点有子节点不能修改！");
		}
		if(StringUtils.isNotBlank(equipmentField.getId()) 
				&& equipmentField.getId().equals(equipmentField.getParent().getId())){//父节点为自己
			addMessage(redirectAttributes, "保存设备层次'" + equipmentField.getCcmc() +"'失败：不能选择自己作为父节点！");
		}else{
			if((null!=equipmentField&&(null!=equipmentField.getXssx()&&equipmentField.getXssx().equals("")))||(null!=equipmentField&&null==equipmentField.getXssx())){
				equipmentField.setXssx(100);
			}
			equipmentFieldService.save(equipmentField);
			addMessage(redirectAttributes, "保存设备层次'" + equipmentField.getCcmc() + "'成功");
		}
		return "redirect:"+Global.getAdminPath()+"/ip/equipment/none";
	}
	/*该节点是否有子节点*/
	public boolean hasChildNode(String nodeId){
		boolean b=false;
		if(equipmentFieldService.findSun(nodeId).size()>0)
			b=true;
		
		return b;
	}
	
	/*该节点是否有信息关联*/
	public boolean hasInfo(String nodeId){
		boolean b=false;
		if(equipmentFieldService.findInfo(nodeId).size()>0)
			b=true;
		
		return b;
	}
	@RequiresPermissions("ip:equipmentField:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String nodeId = equipmentFieldService.get(id).getParent().getId();
		if(hasChildNode(id)){
			addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
			return "redirect:"+Global.getAdminPath()+"/ip/equipmentField/?nodeId="+nodeId;
		}
		if(hasInfo(id)){
			addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
			return "redirect:"+Global.getAdminPath()+"/ip/equipmentField/?nodeId="+nodeId;
		}
		equipmentFieldService.delete(id);
		addMessage(redirectAttributes, "删除设备层次成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipment/none";
	}
	
	@RequiresPermissions("ip:equipmentField:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String nodeId = equipmentFieldService.get(ids[0]).getParent().getId();
		for(String id:ids){
			if(hasChildNode(id)){
				addMessage(redirectAttributes, "有子节点不能删除，请先删除子节点");
				return "redirect:"+Global.getAdminPath()+"/ip/equipmentField/?nodeId="+nodeId;
			}
			if(hasInfo(id)){
				addMessage(redirectAttributes, "有信息关联不能删除，请先删除关联的信息");
				return "redirect:"+Global.getAdminPath()+"/ip/equipmentField/?nodeId="+nodeId;
			}
			equipmentFieldService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个设备层次成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipment/none";
	}
	
	@RequiresPermissions("ip:equipmentField:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentField equipmentField, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "设备层次数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentField> page = equipmentFieldService.find(new Page<EquipmentField>(request, response), equipmentField,null); 
    		new ExportExcel("设备层次数据", EquipmentField.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出设备层次失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentField/";
    }
    
    @RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String,Object>> treeData(String module, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		return equipmentFieldService.getTreeData();
	}
    
    /**
	 * 批量禁用启用
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "updateStateList")
	public String updateStateList(String[] ids,String state, RedirectAttributes redirectAttributes) {
		int j=0;
		String sjjdId=equipmentFieldService.get(ids[0]).getParent().getId();
		for(String id :ids){
			equipmentFieldService.updateStateList(id,state);
			j++;
		}
		if(null!=state&&state.equals("1")){
			addMessage(redirectAttributes, "禁用"+j+"个设备层次！");
		}else{
			addMessage(redirectAttributes, "启用"+j+"个设备层次！");
		}
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentField/list?nodeId="+sjjdId;
	}
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		String val2=null;
		try {
			val2 = java.net.URLDecoder.decode(val1,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		boolean flag=equipmentFieldService.validation(val2);
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
