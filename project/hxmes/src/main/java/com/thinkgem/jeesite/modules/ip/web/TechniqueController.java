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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.MaterialCategory;
import com.thinkgem.jeesite.modules.ip.entity.Technique;
import com.thinkgem.jeesite.modules.ip.service.MaterialService;
import com.thinkgem.jeesite.modules.ip.service.TechniqueService;


/**
 * 工艺路线基本信息Controller
 * @author zzc
 * @version 2016-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/technique")
public class TechniqueController extends BaseController {

	@Autowired
	private TechniqueService techniqueService;
	@Autowired
	private MaterialService materialService;
	
	@ModelAttribute
	public Technique get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return techniqueService.get(id);
		}else{
			return new Technique();
		}
	}
	
	@RequiresPermissions("ip:technique:view")
	@RequestMapping(value = {"list", ""})
	public String list(Technique technique, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			technique.setCreateBy(user);
		}
		technique.setJdlx(0);
		String pageType = request.getParameter("pageType");
		String moduleUrl = null;
		if(pageType == null){
			pageType = "1";
		}
		if(pageType.equals("1")){
			moduleUrl = "";
		}
		else if(pageType.equals("2")){
			moduleUrl = "techniqueParameter";
		}
		else if(pageType.equals("3")){
			moduleUrl = "techniqueCheckItem";
		}
		else if(pageType.equals("4")){
			moduleUrl = "techniqueOpsStep";
		}
		else if(pageType.equals("5")){
			moduleUrl = "techniqueDocument";
		}
		
		if(request.getParameter("material.id")!=null&&request.getParameter("material.id")!="")
		{
			
			technique.getMaterial().setId(request.getParameter("material.id"));
			technique.setSfzgy(Integer.parseInt(request.getParameter("sfzgy")));
			technique.setSfqy(Integer.parseInt(request.getParameter("sfqy")));
		}
		else if(request.getParameter("sfzgy")!=null&&request.getParameter("sfzgy")!=""){
	
			technique.setSfzgy(Integer.parseInt(request.getParameter("sfzgy")));
			technique.setSfqy(Integer.parseInt(request.getParameter("sfqy")));
		}
		
        Page<Technique> page = techniqueService.find(new Page<Technique>(request, response), technique); 
        model.addAttribute("page", page);
        model.addAttribute("direct", moduleUrl);
        model.addAttribute("technique", technique);
		return "modules/" + "ip/techniqueList";
	}

	@RequiresPermissions("ip:technique:view")
	@RequestMapping(value = "form")
	public String form(Technique technique, Model model) {
		model.addAttribute("technique", technique);
		return "modules/" + "ip/techniqueForm";
	}

	@RequiresPermissions("ip:technique:edit")
	@RequestMapping(value = "save")
	public String save(Technique technique, Model model, RedirectAttributes redirectAttributes) {
		System.out.println("hehe");
		if (!beanValidator(model, technique)){
			return form(technique, model);
		}
		techniqueService.save(technique);
		addMessage(redirectAttributes, "保存工艺路线基本信息'" + technique.getId() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/technique/?repage";
	}
	
	@RequiresPermissions("ip:technique:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		techniqueService.delete(id);
		addMessage(redirectAttributes, "删除工艺路线基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/technique/?repage";
	}

	@RequiresPermissions("ip:technique:edit")
	@RequestMapping(value = "deletes")
	public String deletes(String id[], RedirectAttributes redirectAttributes) {
		for(int i=0; i<id.length; i++){
			techniqueService.deletes(id[i]);
		}
		addMessage(redirectAttributes, "删除工艺路线基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/technique/?repage";
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping("treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		List<Technique> list = techniqueService.findAll();
		for (int i=0; i<list.size(); i++){
			Technique e = list.get(i);
		
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("name", e.getMc());
			mapList.add(map);
		}
		
		return mapList;
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping("wlbmtreeData")
	public List<Map<String, Object>> wlbmtreeData(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		List<Material> list = materialService.wlfindAll();
		for (int i=0; i<list.size(); i++){
			Material e = list.get(i);
		
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("name", e.getWlmc());
			mapList.add(map);
		}
		
		return mapList;
	}
	
	/*
	 * 用于对应原型设计中的增加工序组以及工序的编辑界面
	 */
	
	@RequiresPermissions("ip:technique:view")
	@RequestMapping(value = "index")
	public String indexform(Technique technique, Model model) {
		model.addAttribute("technique", technique);
		return "modules/" + "ip/techniqueIndex";
	}
	
	
	@RequiresPermissions("ip:technique:view")
	@RequestMapping(value = "editform")
	public String editform(Technique technique, Model model) {
		model.addAttribute("technique", technique);
		return "modules/" + "ip/techniqueEditForm";
	}
	
	@RequiresPermissions("ip:technique:edit")
	@RequestMapping(value = "editsave")
	public String editsave(Technique technique, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, technique)){
			return form(technique, model);
		}
		techniqueService.save(technique);
		addMessage(redirectAttributes, "保存工艺路线基本信息'" + technique.getId() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/technique/list";
	}
	
	
	@RequiresPermissions("ip:technique:view")
	@RequestMapping(value = "tree")
	public String tree(Technique technique, Model model) {
		
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		List<Technique> list = techniqueService.findAll();
		for (int i=0; i<list.size(); i++){
			Technique e = list.get(i);	
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getSjgylxid());
			map.put("name", e.getMc());
			mapList.add(map);
		}
		
		model.addAttribute("gylxList", mapList);
		
		return "modules/" + "ip/techniquetree";
	}
	
	@RequestMapping(value = "validation")
	public void validation(String val1,String val2,HttpServletResponse response){
		
		try {
			val2 = java.net.URLDecoder.decode(val2,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		boolean flag=techniqueService.validation(val1,val2);
		
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
