/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.quality.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.hibernate.criterion.DetachedCriteria;
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
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.quality.entity.ZjWttzd;
import com.thinkgem.jeesite.modules.quality.service.ZjWttzdService;

/**
 * 质量问题通知单Controller
 * @author LiuBJ
 * @version 2016-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/quality/zjWttzd")
public class ZjWttzdController extends BaseController {

	@Autowired
	private ZjWttzdService zjWttzdService;
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private OfficeDao officeDao;
	
	@ModelAttribute
	public ZjWttzd get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return zjWttzdService.get(id);
		}else{
			return new ZjWttzd();
		}
	}
	
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
        Page<ZjWttzd> page = zjWttzdService.find(new Page<ZjWttzd>(request, response), zjWttzd); 
        model.addAttribute("page", page);
		return "modules/" + "quality/zjWttzdList";
	}

	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "form")
	public String form(ZjWttzd zjWttzd, Model model) {
		model.addAttribute("zjWttzd", zjWttzd);
		return "modules/" + "quality/zjWttzdForm";
	}

	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "save")
	public String save(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		User user = UserUtils.getUser();
		zjWttzd.setJcr(user.getId());			
		zjWttzdService.save(zjWttzd);
		addMessage(redirectAttributes, "保存质量问题通知单'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpQczbyPage";
	}
	
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		zjWttzdService.delete(id);
		addMessage(redirectAttributes, "删除质量问题通知单成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/?repage";
	}
	
	/**
	 * 流程部署
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "processDeploy")
	public String processDeploy(){
		zjWttzdService.processDeploy();
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpQczbyPage/?repage";
	}
	
	/*===========================================质保人员相关=======================================*/
	
	/**
	 * 跳转质保人员list页面，显示历史任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "jumpQczbyPage")
	public String jumpQczbyPage(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
		String userId = user.getId();
        Page<ZjWttzd> page = zjWttzdService.findQczbryHistory(new Page<ZjWttzd>(request, response), zjWttzd,userId); 
        model.addAttribute("page", page);
        model.addAttribute("sign","jumpQczbyPage");
		return "modules/" + "quality/qczbryList";
	}
	
	
	/**
	 * 质保员启动流程
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "start1")
	public String start1(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		zjWttzdService.start1(zjWttzd,httpServletRequest);
		addMessage(redirectAttributes, "保存质量问题通知单'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpQczbyPage?repage";
	}
	/**
	 * 质保人员获取自己需要完成的任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "getCompleteTask")
	public String getCompleteTask(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
        Page<ZjWttzd> page = zjWttzdService.getCompleteTask(new Page<ZjWttzd>(request, response), zjWttzd,user.getId()); 
        model.addAttribute("page", page);
        model.addAttribute("sign","getCompleteTask");
		return "modules/" + "quality/qczbryList";
	}
	/**
	 * 跳转到质保人员表单页面
	 * @param zjWttzd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "qczbyForm")
	public String qczbyForm(ZjWttzd zjWttzd, Model model,String identity) {
		model.addAttribute("zjWttzd", zjWttzd);
		model.addAttribute("identity", identity);
		return "modules/" + "quality/qczbryForm";
	}
	/**
	 * 以树结构形式显示所有机构信息
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		DetachedCriteria dc = officeDao.createDetachedCriteria();
		List<Office> list = officeDao.find(dc);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParent() != null ? e.getParent().getId() : 0);
			map.put("name", e.getName());
			mapList.add(map);
		}
		return mapList;
	}
	/**
	 * 得到质量问题通知单列表
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "requestList")
	public String requestList(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
		String userId = user.getId();
        Page<ZjWttzd> page = zjWttzdService.requestList(new Page<ZjWttzd>(request, response), zjWttzd,userId); 
        model.addAttribute("page", page);
        model.addAttribute("sign","requestList");
		return "modules/" + "quality/qczbryList";
	}
	/**
	 * 质保人员完成任务
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "zbryComplete")
	public String zbryComplete(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		User user = UserUtils.getUser();
		zjWttzdService.zbryComplete(zjWttzd,user.getId(),request);
		addMessage(redirectAttributes, "完成起草");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpQczbyPage?repage";
	}
	
	/**************************************问题确认车间负责人*****************************************/
	/**
	 * 跳转问题确认车间负责人list页面，显示历史任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "jumpWtqrcjfzrPage")
	public String jumpWtqrcjfzrPage(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
		String userId = user.getId();
        Page<ZjWttzd> page = zjWttzdService.findWtqrcjfzrHistory(new Page<ZjWttzd>(request, response), zjWttzd,userId); 
        model.addAttribute("page", page);
        model.addAttribute("sign","jumpQczbyPage");
		return "modules/" + "quality/wtqrcjfzrList";
	}
	
	/**
	 * 问题确认车间负责人获取自己需要完成的任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "getCompleteTaskWtqrcjfzr")
	public String getCompleteTaskWtqrcjfzr(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
        Page<ZjWttzd> page = zjWttzdService.getCompleteTaskWtqrcjfzr(new Page<ZjWttzd>(request, response), zjWttzd,user.getId()); 
        model.addAttribute("page", page);
        model.addAttribute("sign","getCompleteTask");
		return "modules/" + "quality/wtqrcjfzrList";
	}
	
	/**
	 * 跳转到确认车间负责人表单页面
	 * @param zjWttzd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "wtqrcjfzrForm")
	public String wtqrcjfzrForm(ZjWttzd zjWttzd, Model model,String identity) {
		model.addAttribute("zjWttzd", zjWttzd);
		model.addAttribute("identity", identity);
		return "modules/" + "quality/wtqrcjfzrForm";
	}
	/**
	 * 保存确认车间负责人form表单
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "saveWtqrcjfzr")
	public String saveWtqrcjfzr(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		zjWttzdService.save(zjWttzd);
		addMessage(redirectAttributes, "保存质量问题通知单'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpWtqrcjfzrPage";
	}
	
	/**
	 * 问题确认车间负责人完成任务
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "wtqrcjfzrComplete")
	public String wtqrcjfzrComplete(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		User user = UserUtils.getUser();
		zjWttzdService.wtqrcjfzrComplete(zjWttzd,user.getId(),request);
		addMessage(redirectAttributes, "完成问题确认");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpWtqrcjfzrPage?repage";
	}
	
	/***********************************************审核车间单位负责人******************************************************/
	/**
	 * 跳转审核车间单位负责人list页面，显示历史任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "jumpShcjdwfzrPage")
	public String jumpShcjdwfzrPage(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
		String userId = user.getId();
        Page<ZjWttzd> page = zjWttzdService.findShcjdwfzrHistory(new Page<ZjWttzd>(request, response), zjWttzd,userId); 
        model.addAttribute("page", page);
        model.addAttribute("sign","jumpQczbyPage");
		return "modules/" + "quality/shcjdwfzrList";
	}
	/**
	 * 审核车间单位负责人获取自己需要完成的任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "getCompleteTaskShcjdwfzr")
	public String getCompleteTaskShcjdwfzr(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
        Page<ZjWttzd> page = zjWttzdService.getCompleteTaskShcjdwfzr(new Page<ZjWttzd>(request, response), zjWttzd,user.getId()); 
        model.addAttribute("page", page);
        model.addAttribute("sign","getCompleteTask");
		return "modules/" + "quality/shcjdwfzrList";
	}
	/**
	 * 跳转到审核车间单位负责人表单页面
	 * @param zjWttzd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "shcjdwfzrForm")
	public String shcjdwfzrForm(ZjWttzd zjWttzd, Model model,String identity) {
		model.addAttribute("zjWttzd", zjWttzd);
		model.addAttribute("identity", identity);
		return "modules/" + "quality/shcjdwfzrForm";
	}
	/**
	 * 保存审核车间单位负责人form表单
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "saveShcjdwfzr")
	public String saveShcjdwfzr(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		zjWttzdService.save(zjWttzd);
		addMessage(redirectAttributes, "保存质量问题通知单'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpShcjdwfzrPage";
	}
	/**
	 * 审核车间单位负责人完成任务
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "shcjdwfzrComplete")
	public String shcjdwfzrComplete(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		User user = UserUtils.getUser();
		zjWttzdService.shcjdwfzrComplete(zjWttzd,user.getId(),request);
		addMessage(redirectAttributes, "完成问题确认");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpShcjdwfzrPage?repage";
	}
	
	/***************************************************完成情况填报质量员*****************************************************/
	/**
	 * 跳转完成情况填报质量员list页面，显示历史任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "jumpWcqktbzlyPage")
	public String jumpWcqktbzlyPage(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
		String userId = user.getId();
        Page<ZjWttzd> page = zjWttzdService.findWcqktbzlyHistory(new Page<ZjWttzd>(request, response), zjWttzd,userId); 
        model.addAttribute("page", page);
        model.addAttribute("sign","jumpQczbyPage");
		return "modules/" + "quality/wcqktbzlyList";
	}
	/**
	 * 完成情况填报质量员获取自己需要完成的任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "getCompleteTaskWcqktbzly")
	public String getCompleteTaskWcqktbzly(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
        Page<ZjWttzd> page = zjWttzdService.getCompleteTaskWcqktbzly(new Page<ZjWttzd>(request, response), zjWttzd,user.getId()); 
        model.addAttribute("page", page);
        model.addAttribute("sign","getCompleteTask");
		return "modules/" + "quality/wcqktbzlyList";
	}
	/**
	 * 跳转到完成情况填报质量员表单页面
	 * @param zjWttzd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "wcqktbzlyForm")
	public String wcqktbzlyForm(ZjWttzd zjWttzd, Model model,String identity) {
		model.addAttribute("zjWttzd", zjWttzd);
		model.addAttribute("identity", identity);
		return "modules/" + "quality/wcqktbzlyForm";
	}
	/**
	 * 保存完成情况填报质量员form表单
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "saveWcqktbzly")
	public String saveWcqktbzly(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		zjWttzdService.save(zjWttzd);
		addMessage(redirectAttributes, "保存质量问题通知单'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpWcqktbzlyPage";
	}
	/**
	 * 审核车间单位负责人完成任务
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "wcqktbzlyComplete")
	public String wcqktbzlyComplete(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		User user = UserUtils.getUser();
		zjWttzdService.wcqktbzlyComplete(zjWttzd,user.getId(),request);
		addMessage(redirectAttributes, "完成问题确认");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpWcqktbzlyPage?repage";
	}
	
	/*********************************************************验证关闭质保员*******************************************************/
	/**
	 * 跳转验证关闭质保人员list页面，显示历史任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "jumpYzgbzbryPage")
	public String jumpYzgbzbryPage(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
		String userId = user.getId();
        Page<ZjWttzd> page = zjWttzdService.findYzgbzbryHistory(new Page<ZjWttzd>(request, response), zjWttzd,userId); 
        model.addAttribute("page", page);
        model.addAttribute("sign","jumpQczbyPage");
		return "modules/" + "quality/yzgbzbryList";
	}
	/**
	 * 验证关闭质保人员获取自己需要完成的任务
	 * @param zjWttzd
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "getCompleteTaskYzgbzbry")
	public String getCompleteTaskYzgbzbry(ZjWttzd zjWttzd, HttpServletRequest request, HttpServletResponse response, Model model){
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			zjWttzd.setCreateBy(user);
		}
        Page<ZjWttzd> page = zjWttzdService.getCompleteTaskYzgbzbry(new Page<ZjWttzd>(request, response), zjWttzd,user.getId()); 
        model.addAttribute("page", page);
        model.addAttribute("sign","getCompleteTask");
		return "modules/" + "quality/yzgbzbryList";
	}
	/**
	 * 跳转到 验证关闭质保人员表单页面
	 * @param zjWttzd
	 * @param model
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "yzgbzbryForm")
	public String yzgbzbryForm(ZjWttzd zjWttzd, Model model,String identity) {
		model.addAttribute("zjWttzd", zjWttzd);
		model.addAttribute("identity", identity);
		return "modules/" + "quality/yzgbzbryForm";
	}
	/**
	 * 保存 验证关闭质保人员form表单
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:edit")
	@RequestMapping(value = "saveYzgbzbry")
	public String saveYzgbzbry(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		zjWttzdService.save(zjWttzd);
		addMessage(redirectAttributes, "保存质量问题通知单'" +  "'成功");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpYzgbzbryPage";
	}
	/**
	 *  验证关闭质保人员完成任务
	 * @param zjWttzd
	 * @param model
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequiresPermissions("quality:zjWttzd:view")
	@RequestMapping(value = "yzgbzbryComplete")
	public String yzgbzbryComplete(ZjWttzd zjWttzd, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		if (!beanValidator(model, zjWttzd)){
			return form(zjWttzd, model);
		}
		User user = UserUtils.getUser();
		zjWttzdService.yzgbzbryComplete(zjWttzd,user.getId(),request);
		addMessage(redirectAttributes, "完成问题确认");
		return "redirect:"+Global.getAdminPath()+"/quality/zjWttzd/jumpShcjdwfzrPage?repage";
	}
}
