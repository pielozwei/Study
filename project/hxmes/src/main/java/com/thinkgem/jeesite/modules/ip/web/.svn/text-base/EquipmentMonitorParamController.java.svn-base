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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentMonitorParam;
import com.thinkgem.jeesite.modules.ip.service.EquipmentService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentMonitorParamService;

/**
 * 关键监控参数Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentMonitorParam")
public class EquipmentMonitorParamController extends BaseController {

	@Autowired
	private EquipmentMonitorParamService equipmentMonitorParamService;
	@Autowired
	private EquipmentService equipmentService;
	
	@ModelAttribute
	public EquipmentMonitorParam get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentMonitorParamService.get(id);
		}else{
			return new EquipmentMonitorParam();
		}
	}
	
	@RequiresPermissions("ip:equipmentMonitorParam:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentMonitorParam equipmentMonitorParam,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentMonitorParam.setCreateBy(user);
		}
		String equipmentId=request.getParameter("equipmentId");
		model.addAttribute("equipmentId", equipmentId);
		Page<EquipmentMonitorParam> paramPage = new Page<EquipmentMonitorParam>(request, response);
        Page<EquipmentMonitorParam> page = equipmentMonitorParamService.find(paramPage, equipmentMonitorParam,equipmentId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentMonitorParamList";
	}

	@RequiresPermissions("ip:equipmentMonitorParam:view")
	@RequestMapping(value = "form")
	public String form(EquipmentMonitorParam equipmentMonitorParam, Model model) {
		String equipmentId=null;
		if(equipmentMonitorParam.getEquipment()!=null&&StringUtils.isNotBlank(equipmentMonitorParam.getEquipment().getId())){
			equipmentId=equipmentMonitorParam.getEquipment().getId();
			equipmentMonitorParam.setEquipment(equipmentService.get(equipmentId));
		}
		if(StringUtils.isBlank(equipmentMonitorParam.getId())){//添加给个编码
			List<EquipmentMonitorParam> list=equipmentMonitorParamService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentMonitorParam", equipmentMonitorParam);
		return "modules/" + "ip/equipmentMonitorParamForm";
	}

	@RequiresPermissions("ip:equipmentMonitorParam:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentMonitorParam equipmentMonitorParam, Model model, RedirectAttributes redirectAttributes) {
		String equipmentId = equipmentMonitorParam.getEquipment()!=null?equipmentMonitorParam.getEquipment().getId():"";
		if (!beanValidator(model, equipmentMonitorParam)){
			return form(equipmentMonitorParam, model);
		}
		equipmentMonitorParamService.save(equipmentMonitorParam);
		addMessage(redirectAttributes, "保存关键监控参数'" + equipmentMonitorParam.getCsmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentMonitorParam/?equipmentId="+equipmentId;
	}
	
	@RequiresPermissions("ip:equipmentMonitorParam:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String equipmentId =equipmentMonitorParamService.get(id).getEquipment().getId();
		equipmentMonitorParamService.delete(id);
		addMessage(redirectAttributes, "删除关键监控参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentMonitorParam/?equipmentId="+(equipmentId!=null?equipmentId:"");
	}
	
	@RequiresPermissions("ip:equipmentMonitorParam:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentId =equipmentMonitorParamService.get(ids[0]).getEquipment().getId();
		for(String id:ids){
			equipmentMonitorParamService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个关键监控参数成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentMonitorParam/?equipmentId="+(equipmentId!=null?equipmentId:"");
	}
	
	@RequiresPermissions("ip:equipmentMonitorParam:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentMonitorParam equipmentMonitorParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentId=request.getParameter("equipmentId");
		try {
			String fileName = "关键监控参数数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentMonitorParam> page = equipmentMonitorParamService.find(new Page<EquipmentMonitorParam>(request, response), equipmentMonitorParam,null); 
    		new ExportExcel("关键监控参数数据", EquipmentMonitorParam.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出关键监控参数失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentMonitorParam/?equipmentId="+equipmentId;
    }
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=equipmentMonitorParamService.validation(val1);
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
