/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.entity.Jldw;
import com.thinkgem.jeesite.modules.ip.service.JldwService;

/**
 * 计量单位Controller
 * @author lucl
 * @version 2016-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/jldw")
public class JldwController extends BaseController {

	@Autowired
	private JldwService jldwService;
	
	@ModelAttribute
	public Jldw get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return jldwService.get(id);
		}else{
			return new Jldw();
		}
	}
	
	@RequiresPermissions("ip:jldw:view")
	@RequestMapping(value = {"list", ""})
	public String list(Jldw jldw, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			jldw.setCreateBy(user);
		}
        Page<Jldw> page = jldwService.find(new Page<Jldw>(request, response), jldw); 
        model.addAttribute("page", page);
		return "modules/" + "ip/jldwList";
	}

	@RequiresPermissions("ip:jldw:view")
	@RequestMapping(value = "form")
	public String form(Jldw jldw, Model model) {
		model.addAttribute("jldw", jldw);
		return "modules/" + "ip/jldwForm";
	}

	@RequiresPermissions("ip:jldw:edit")
	@RequestMapping(value = "save")
	public String save(Jldw jldw, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jldw)){
			return form(jldw, model);
		}
		if((null!=jldw&&(null!=jldw.getXssx()&&jldw.getXssx().equals("")))||(null!=jldw&&null==jldw.getXssx())){
			jldw.setXssx(100);
		}
		jldwService.save(jldw);
		addMessage(redirectAttributes, "保存计量单位'" + jldw.getJldwmc() + "'成功");
		return "redirect:"+Global.getAdminPath()+"/ip/jldw/?repage";
	}
	
	@RequiresPermissions("ip:jldw:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		jldwService.delete(id);
		addMessage(redirectAttributes, "删除计量单位成功");
		return "redirect:"+Global.getAdminPath()+"/ip/jldw/?repage";
	}
	/**
	 * 批量删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ip:jldw:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes){
		int j=0;
		for(String id :ids){
			jldwService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个计量单位！");
		return "redirect:"+Global.getAdminPath()+"/ip/jldw/";
	}
	/**
	 * 批量启用禁用
	 * @param ids
	 * @param state
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ip:jldw:edit")
	@RequestMapping(value = "updateStateList")
	public String updateStateList(String[] ids,String state, RedirectAttributes redirectAttributes) {
		int j=0;
	//	String sjjdId=ckDao.get(ids[0]).getSjjd().getId();
		for(String id :ids){
			jldwService.updateStateList(id,state);
			j++;
		}
		if(null!=state&&state.equals("1")){
			addMessage(redirectAttributes, "禁用"+j+"个计量单位！");
		}else{
			addMessage(redirectAttributes, "启用"+j+"个计量单位！");
		}
		return "redirect:"+Global.getAdminPath()+"/ip/jldw/";
	}
	/**
	 * 验证编码是否唯一
	 * @param val1
	 */
	@RequestMapping(value = "validation")
	public void validation(String val1,HttpServletResponse response){
		//查询编码名称是否重复
		boolean flag=jldwService.validation(val1);
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
