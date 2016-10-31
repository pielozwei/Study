/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentDocument;
import com.thinkgem.jeesite.modules.ip.service.EquipmentModelService;
import com.thinkgem.jeesite.modules.ip.service.EquipmentDocumentService;

/**
 * 技术资料Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/equipmentDocument")
public class EquipmentDocumentController extends BaseController {

	@Autowired
	private EquipmentDocumentService equipmentDocumentService;
	@Autowired
	private EquipmentModelService equipmentModelService;
	
	@ModelAttribute
	public EquipmentDocument get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return equipmentDocumentService.get(id);
		}else{
			EquipmentDocument equipmentDocument = new EquipmentDocument();
			return equipmentDocument;
		}
	}
	
	@RequiresPermissions("ip:equipmentDocument:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipmentDocument equipmentDocument,Map<String,Object> displayColumnsMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			equipmentDocument.setCreateBy(user);
		}
		String equipmentModelId=request.getParameter("equipmentModelId");
		model.addAttribute("equipmentModelId", equipmentModelId);
		Page<EquipmentDocument> paramPage = new Page<EquipmentDocument>(request, response);
        Page<EquipmentDocument> page = equipmentDocumentService.find(paramPage, equipmentDocument,equipmentModelId); 
        model.addAttribute("page", page);
		return "modules/" + "ip/equipmentDocumentList";
	}

	@RequiresPermissions("ip:equipmentDocument:view")
	@RequestMapping(value = "form")
	public String form(EquipmentDocument equipmentDocument, Model model) {
		String equipmentModelId=null;
		if(StringUtils.isEmpty(equipmentDocument.getId())){
			User user=UserUtils.getUser();
			equipmentDocument.setCreateBy(user);
			equipmentDocument.setCreateDate(new Date());
		}
		if(equipmentDocument.getEquipmentModel()!=null&&StringUtils.isNotBlank(equipmentDocument.getEquipmentModel().getId())){
			equipmentModelId=equipmentDocument.getEquipmentModel().getId();
			equipmentDocument.setEquipmentModel(equipmentModelService.get(equipmentModelId));
		}
		if(StringUtils.isBlank(equipmentDocument.getId())){//添加给个编码
			List<EquipmentDocument> list=equipmentDocumentService.findAll();
			model.addAttribute("bm", list.size()+1);
		}
		model.addAttribute("equipmentDocument", equipmentDocument);
		return "modules/" + "ip/equipmentDocumentForm";
	}

	@RequiresPermissions("ip:equipmentDocument:edit")
	@RequestMapping(value = "save")
	public String save(EquipmentDocument equipmentDocument, Model model, RedirectAttributes redirectAttributes) {
		String equipmentModelId = equipmentDocument.getEquipmentModel()!=null?equipmentDocument.getEquipmentModel().getId():"";
		if (!beanValidator(model, equipmentDocument)){
			return form(equipmentDocument, model);
		}
		equipmentDocumentService.save(equipmentDocument);
		addMessage(redirectAttributes, "保存技术资料'" + equipmentDocument.getWdbt() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentDocument/?equipmentModelId="+equipmentModelId;
	}
	
	
	@RequiresPermissions("ip:equipmentDocument:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] ids, RedirectAttributes redirectAttributes) {
		String equipmentModelId =equipmentDocumentService.get(ids[0]).getEquipmentModel().getId();
		for(String id:ids){
			equipmentDocumentService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个技术资料成功");
		return "redirect:"+Global.getAdminPath()+"/ip/equipmentDocument/?equipmentModelId="+(equipmentModelId!=null?equipmentModelId:"");
	}
	
	@RequiresPermissions("ip:equipmentDocument:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(EquipmentDocument equipmentDocument, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		String equipmentModelId=request.getParameter("equipmentModelId");
		try {
			String fileName = "技术资料数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<EquipmentDocument> page = equipmentDocumentService.find(new Page<EquipmentDocument>(request, response), equipmentDocument,null); 
    		new ExportExcel("技术资料数据", EquipmentDocument.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出技术资料失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentDocument/?equipmentModelId="+equipmentModelId;
    }
	
	@RequiresPermissions("ip:equipmentDocument:view")
    @RequestMapping(value = "downLoad", method=RequestMethod.POST)
    public String downLoadFile(EquipmentDocument equipmentDocument, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
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
	       fullFileName=fullFileName.replaceFirst(xmmc+xmmc, xmmc);  
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
			addMessage(redirectAttributes, "下载失败！失败信息："+e.getMessage());
			e.printStackTrace();
		}
		return "redirect:" + Global.getAdminPath() + "/ip/equipmentDocument/?equipmentModelId="+equipmentModelId;
    }
	@ResponseBody
	@RequestMapping("checkWdbm")
	public String checkWdbm(String wdbm,String oldWdbm) {
		if (wdbm != null && oldWdbm.equals(wdbm)) {
			return "true";
		}else if (wdbm != null && equipmentDocumentService.findByWdbm(wdbm) == null) {
			return "true";
		}
		return "false";
	}
}
