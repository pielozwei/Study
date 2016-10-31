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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterService;

/**
 * 工作中心维护Controller
 * @author Lucl
 * @version 2016-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/gzzx/gzzxwh")
public class WorkCenterController extends BaseController {

	@Autowired
	private WorkCenterService gzzxwhService;
	
	@ModelAttribute
	public WorkCenter get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return gzzxwhService.get(id);
		}else{
			return new WorkCenter();
		}
	}
	
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = {"list", ""})
	public String list(WorkCenter gzzxwh, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			gzzxwh.setCreateBy(user);
		}
        Page<WorkCenter> page = gzzxwhService.find(new Page<WorkCenter>(request, response), gzzxwh); 
        model.addAttribute("page", page);
        model.addAttribute("gzzxwh", gzzxwh);
		return "modules/" + "ip/workCenterList";
	}

	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "form")
	public String form(WorkCenter gzzxwh, Model model) {
		model.addAttribute("gzzxwh", gzzxwh);
		return "modules/" + "ip/workCenterForm";
	}

	@RequiresPermissions("gzzx:gzzxwh:edit")
	@RequestMapping(value = "save")
	public String save(WorkCenter gzzxwh, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, gzzxwh)){
			return form(gzzxwh, model);
		}
		/*if(null!=gzzxwh.getId()&&!gzzxwh.getId().equals("")){
			Gzzxwh gzzxwh1=new Gzzxwh(gzzxwh.getSjgzzxid().getId());
			gzzxwh.setSjgzzxid(gzzxwh1);
		}*/
		gzzxwhService.save(gzzxwh);
		addMessage(redirectAttributes, "保存工作中心维护成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxwh/?repage";
	}
	
	@RequiresPermissions("gzzx:gzzxwh:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		gzzxwhService.delete(id);
		addMessage(redirectAttributes, "删除工作中心维护成功");
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxwh/?repage";
	}
	
	/**
	 * 方法功能：点击工作中心管理弹出主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "start")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxwh";
		}
		model.addAttribute("module", module);
		return "modules/ip/workCenterIndex";
	}
	
	/**
	 * 方法功能：生成仓库树
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxwh";
		}
		model.addAttribute("module", module);
		model.addAttribute("gzzxList", gzzxwhService.findTree(true, null));
		List<WorkCenter> list=gzzxwhService.findTree(true, null);
		return "modules/ip/workCenterTree";
	}
	
	/**
	 * 方法功能：显示提示信息
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/ip/workCenterNone";
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) Long extId,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		WorkCenter gzzxwh=new WorkCenter();
		List<WorkCenter> list =  (List<WorkCenter>) gzzxwhService.findTree(true, gzzxwh);
		WorkCenter wc1 = gzzxwhService.get("0");
		if(null==wc1){
			WorkCenter workCenter1 = new WorkCenter();
			//为了在没有上级ID是添加
			workCenter1.setId("0");
			workCenter1.setGzzxbm("78547856987");
			workCenter1.setGzzxjc("0");
			workCenter1.setGzzxzt(0);
			workCenter1.setSjgzzxid(workCenter1);
			workCenter1.setGzzxmc("无上级节点");
			//如果仓库中没有id为0的工作中心则插入
			gzzxwhService.save(workCenter1);
			list.add(workCenter1);
		}
		for (int i=0; i<list.size(); i++){
			WorkCenter e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getSjgzzxid()!=null?e.getSjgzzxid().getId():0);
				map.put("name", e.getGzzxmc());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
	/*****************************工作中心时界配置代码*********************************/
	/**
	 * 方法功能：点击工作中心管理弹出主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "start1")
	public String index1(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxwh/sjpz/";
		}
		model.addAttribute("module", module);
		return "modules/ip/workCenterIndex";
	}
	/**
	 * 方法功能：生成仓库树
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "tree1")
	public String tree1(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxwh/sjpz/";
		}
		model.addAttribute("module", module);
		model.addAttribute("gzzxList", gzzxwhService.findTree(true, null));
		List<WorkCenter> list=gzzxwhService.findTree(true, null);
		return "modules/ip/workCenterTree";
	}
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "sjpz/form")
	public String form1(WorkCenter gzzxwh, Model model) {
		model.addAttribute("gzzxwh", gzzxwh);
		return "modules/" + "ip/workCentersjpz";
	}
	/**
	 * 方法功能：查询当前仓库是否有子节点
	 * @return
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "checkHasChild")
	public String checkHasChild(String[] ids,HttpServletResponse response) {
		Map<String,String> mapper=gzzxwhService.checkHasChild(ids);
		JSONObject map=new JSONObject();
		String flag=mapper.get("flag");
		if(flag=="1"){
			map.put("flag", "1");
			map.put("mc", mapper.get("mc"));
		}else{
			map.put("flag", "0");
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
		return null;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		try{
			int j=0;
			for(String id :ids){
				gzzxwhService.delete(id);
				j++;
			}
			addMessage(redirectAttributes, "删除"+j+"个工作中心！");
			return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxwh/list";
		}catch(Exception exception){
			addMessage(redirectAttributes, "该信息有用，不能删除！");
			return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxwh/list";
		}	
	}
	
	@RequestMapping(value = "updateStateList")
	public String updateStateList(String[] ids,String state, RedirectAttributes redirectAttributes){
		int j=0;
		//String sjjdId=ckDao.get(ids[0]).getSjjd().getId();
		for(String id :ids){
			gzzxwhService.updateStateList(id,state);
			j++;
		}
		if(null!=state&&state.equals("1")){
			addMessage(redirectAttributes, "禁用"+j+"个工作中心！");
		}else{
			addMessage(redirectAttributes, "启用"+j+"个工作中心！");
		}
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxwh/list";
	}
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=gzzxwhService.validation(val1);
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
