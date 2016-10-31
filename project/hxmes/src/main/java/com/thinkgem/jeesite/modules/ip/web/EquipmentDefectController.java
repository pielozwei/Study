/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentDefect;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentDefectService;

/**
 * 缺陷树Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentDefect")
public class EquipmentDefectController extends BaseController {

	@Autowired
	private EquipmentDefectService equipmentDefectService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	
	@ModelAttribute
	public EquipmentDefect get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentDefectService.get(id);
		}else{
			return new EquipmentDefect();
		}
	}
	
	@RequiresPermissions("ip:equipmentDefect:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentDefect equipmentDefect,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentDefect.setCreateBy(user);
		}
		String equipmentModelId=request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentDefect> paramPage = new Page<EquipmentDefect>(request, response);
        Page<EquipmentDefect> page = equipmentDefectService.find(paramPage, equipmentDefect,equipmentModelId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentDefectList";
	}

	@RequiresPermissions("ip:equipmentDefect:view")
	@RequestMapping(value = "form")
	public String form(EquipmentDefect equipmentDefect, Model model) {
		String equipmentModelId=null;
		if(equipmentDefect.getEquipmentModel()!=null&&StringUtils.isNotBlank(equipmentDefect.getEquipmentModel().getId())){
			equipmentModelId=equipmentDefect.getEquipmentModel().getId();
			equipmentDefect.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if(StringUtils.isBlank(equipmentDefect.getId())){//添加给个编码
			List<EquipmentDefect> list=equipmentDefectService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentDefect", equipmentDefect);
		return "modules/" + "ip/equipmentDefectForm";
	}

	@RequiresPermissions("ip:equipmentDefect:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentDefect equipmentDefect, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentDefect.getEquipmentModel()!=null?equipmentDefect.getEquipmentModel().getId():"";
		if (!beanValidator(model, equipmentDefect)){
			return form(equipmentDefect, model);
		}
		equipmentDefectService.save(equipmentDefect);
		addMessage(redirectAttributes, "保存缺陷树'" + equipmentDefect.getQxbm() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentDefect/?equipmentModelId="+equipmentModelId;
	}
	
	@RequiresPermissions("ip:equipmentDefect:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId =equipmentDefectService.get(ids[0]).getEquipmentModel().getId();
		for(String id:ids){
			equipmentDefectService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个缺陷树成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentDefect/?equipmentModelId="+(equipmentModelId!=null?equipmentModelId:"");
	}
	
	@RequiresPermissions("ip:equipmentDefect:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentDefect equipmentDefect, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			String fileName = "缺陷树数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentDefect> page = equipmentDefectService.find(new Page<EquipmentDefect>(request, response), equipmentDefect,null); 
    		new ExportExcel("缺陷树数据", EquipmentDefect.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出缺陷树失败！失败信息："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentDefect/?equipmentModelId="+equipmentModelId;
    }
	
	@RequiresPermissions("ip:equipmentDefect:view")
    @RequestMapping(value = "downLoad", method=RequestMethod.POST)
    public String downLoadFile(EquipmentDefect equipmentDefect, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			 //获得请求文件名  
	        //String filename = request.getParameter("filename");  
	        String url=request.getParameter("url");
	        //文件后缀
	        int i=url.lastIndexOf("/");
	        String str=url.substring(i+1, url.length());
	        String houzhui=str.substring(str.indexOf("."),str.length());
	      //解决中文乱码问题
	        String filename=new String(request.getParameter("filename").getBytes("utf-8"),"iso-8859-1");
	        //设置response的编码方式
	        response.setContentType("application/x-msdownload");
	        response.setHeader("Content-Disposition", "attachment;filename="+filename+DateUtils.getDate("yyyyMMddHHmmss")+houzhui);  
	        //读取目标文件，通过response将目标文件写到客户端  
	        //获取目标文件的绝对路径 
	        String xmmc=request.getContextPath().replace("/", "")+"\\\\";
	       @SuppressWarnings("deprecation")
		String fullFileName = request.getRealPath(url);
	       fullFileName=fullFileName.replaceFirst(xmmc, "");  
	        //读取文件  
	        InputStream in = new FileInputStream(fullFileName);  
	        OutputStream out = response.getOutputStream();  
	        //写文件  
	        int b;  
	        while((b=in.read())!= -1) {  
	            out.write(b);  
	        }  
	        in.close();  
	        out.close();
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentDefect/?equipmentModelId="+equipmentModelId;
    }
	@ResponseBody
	@RequestMapping("checkQxbm")
	public String checkQxbm(String qxbm,String oldQxbm) {
		if (qxbm != null && oldQxbm.equals(qxbm)) {
			return "true";
		}else if (qxbm != null && equipmentDefectService.findByQxbm(qxbm) == null) {
			return "true";
		}
		return "false";
	}

}
