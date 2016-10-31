/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.entity.ReferenceMessage;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlan;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlanTask;
import com.thinkgem.jeesite.modules.ip.entity.Task;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanService;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterService;
import com.thinkgem.jeesite.modules.ip.service.ReferenceMessageService;
import com.thinkgem.jeesite.modules.ip.util.ScollingJobPlanUtil;

/**
 * 参考消息Controller
 * @author LiHR
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/referenceMessage")
public class ReferenceMessageController extends BaseController {

	@Autowired
	private ReferenceMessageService referenceMessageService;
	@Autowired
	private WorkCenterService workCenterService;
	@Autowired
	private ScollingJobPlanService scollingJobPlanService;
	
	@ModelAttribute
	public ReferenceMessage get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return referenceMessageService.get(id);
		}else{
			return new ReferenceMessage();
		}
	}
	
	@RequiresPermissions("ip:referenceMessage:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReferenceMessage referenceMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			referenceMessage.setCreateBy(user);
		}
		Page<ReferenceMessage> paramPage = new Page<ReferenceMessage>(request, response);
        Page<ReferenceMessage> page = referenceMessageService.find(paramPage, referenceMessage); 
        model.addAttribute("page", page);
		return "modules/" + "ip/referenceMessageList";
	}
	
	@RequiresPermissions("ip:referenceMessage:view")
	@RequestMapping(value = "listForWorkCenter")
	public String listForWorkCenter(ReferenceMessage referenceMessage, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			referenceMessage.setCreateBy(user);
		}
		Page<ReferenceMessage> paramPage = new Page<ReferenceMessage>(request, response);
        Page<ReferenceMessage> page = referenceMessageService.findForWorkCenter(paramPage, referenceMessage); 
        model.addAttribute("page", page);
		return "modules/" + "ip/referenceMessageListWorkCenter";
	}

	@RequiresPermissions("ip:referenceMessage:view")
	@RequestMapping(value = "form")
	public String form(ReferenceMessage referenceMessage, Model model) {
		List<WorkCenter> listWorkCenter = workCenterService.findAll();
		model.addAttribute("referenceMessage", referenceMessage);
		model.addAttribute("listWorkCenter", listWorkCenter);
		return "modules/" + "ip/referenceMessageForm";
	}
	
	@RequiresPermissions("ip:referenceMessage:view")
	@RequestMapping(value = "formWorkCenter")
	public String formWorkCenter(ReferenceMessage referenceMessage, Model model) {
		String path = "modules/" + "ip/referenceMessageFormWorkCenter";
		
		List<String> myWorkCenterIds = ScollingJobPlanUtil.selectWorkCenterIdsByUserId(UserUtils.getUser().getId());
		String currentWorkCenterId = referenceMessage.getWorkCenter().getId();
		//1.检查此消息属于所负责的工作中心，否则提示：此消息不属于您负责的工作中心。
		//2.检查此消息是否已发送，如果未发送：此消息不存在。
		//3.如果是未读状态的消息，设置为已读
		if (!myWorkCenterIds.contains(currentWorkCenterId) || referenceMessage.getSffs() != 0) {
			path = "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/listForWorkCenter";
		} else {
			if (referenceMessage.getSfyd()==1) {
				referenceMessage.setSfyd(0);
				//referenceMessageService.save(referenceMessage);
			}
		}
		
		List<WorkCenter> listWorkCenter = workCenterService.findAll();
		model.addAttribute("referenceMessage", referenceMessage);
		model.addAttribute("listWorkCenter", listWorkCenter);
		return path;
	}

	/**
	 * 修改或者新增 个人参考消息时，数据的逻辑性检查----逻辑验证
	 * @param  ReferenceMessage 实体对象
	 * @return String 返回第一个错误消息
	 * */
	public String checkReferenceMessage(ReferenceMessage referenceMessage) {
		String message = null;
		
		//1.参考消息应该 未发送（主要的）--- /发送时间为空 未引用 未读 （次要的）---只判断主要的
		if (referenceMessage.getSffs() != 1) {
			message = "保存失败，已发送，不能保存";
			return message;
		}
		//2.都不为空时 ：计划开始时间 <= 计划完成时间
		Date jhkssj = referenceMessage.getJhkssj();
		Date jhwcsj = referenceMessage.getJhwcsj();
		if (jhkssj != null && jhwcsj != null && jhkssj.getTime() > jhwcsj.getTime()) {
			message = "保存失败，计划开始时间不能大于计划完成时间，不能保存";
			return message;
		}
		
		return message;
	}
	
	@RequiresPermissions("ip:referenceMessage:edit")
	@RequestMapping(value = "save")
	public String save(ReferenceMessage referenceMessage, Model model, RedirectAttributes redirectAttributes) {
		String message = "保存参考消息'" + referenceMessage.getBt() + "'成功";
		String path = "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/";
		String oldBt = "";
		if (StringUtils.isBlank(referenceMessage.getId())) {
			referenceMessage.setFsr(UserUtils.getUser());
		} else {
			oldBt = referenceMessageService.get(referenceMessage.getId()).getBt();
		}
		
		/*大致验证方案：1.唯一性验证 2.实体定义验证3.逻辑验证  。。注意 唯一性验证要写并写在实体验证之前，是因为实体的唯一性验证前台要给提示，写在前面避开实体验证时捕捉不到的异常*/
		if ("false".equals(checkBt(referenceMessage.getBt(), oldBt))) {
			message = "该参考消息名称 已存在";
			addMessage(redirectAttributes, message);
			return path;
		}
		if (!beanValidator(model, referenceMessage)){
			return form(referenceMessage, model);
		}
		String msg = checkReferenceMessage(referenceMessage);
		if (msg == null) {
			referenceMessageService.save(referenceMessage);
		} else {
			message = msg;
		}
		
		addMessage(redirectAttributes, message);
		return path;
	}
	
	@RequiresPermissions("ip:referenceMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		referenceMessageService.delete(id);
		addMessage(redirectAttributes, "删除参考消息成功");
		return "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/";
	}
	
	@RequiresPermissions("ip:referenceMessage:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(@RequestParam(required=true) List<String> ids, RedirectAttributes redirectAttributes) {
		if(!referenceMessageService.hasSend(ids)){
			for(String id:ids){
				referenceMessageService.delete(id);
			}
			addMessage(redirectAttributes, "删除"+ids.size()+"个参考消息成功");
		} else {
			addMessage(redirectAttributes, "删除失败，所选的参考消息中含有已发送的消息");
		}
		
		return "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/";
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "hasSend")
	public boolean hasSend(@RequestParam(required=true) List<String> ids, HttpServletResponse response) {
		boolean b = referenceMessageService.hasSend(ids);
		return b;
	}
	
	@RequiresPermissions("ip:referenceMessage:edit")
	@RequestMapping(value = "send")
	public String send(@RequestParam(required=true)List<String> ids, RedirectAttributes redirectAttributes) {
		boolean b = referenceMessageService.send(ids);
		if (b) {
			addMessage(redirectAttributes, "发送参考消息成功");
		} else {
			addMessage(redirectAttributes, "发送参考消息失败");
		}
		
		return "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/";
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "hasUnpublishedPlan")
	public boolean hasUnpublishedPlan(@RequestParam(required=true)String id, HttpServletResponse response) {
		//未发布的计划
		String workCenterId = referenceMessageService.get(id).getWorkCenter().getId();
		boolean b = scollingJobPlanService.onlyOneUnpublishedPlan(workCenterId) != null ? true : false;
		return b;
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "hasQuote")
	public boolean hasQuote(@RequestParam(required=true)String id, HttpServletResponse response) {
		boolean b = referenceMessageService.hasQuote(id);
		return b;
	}
	
	@RequiresPermissions("ip:referenceMessage:edit")
	@RequestMapping(value = "quote")
	public String quote(@RequestParam(required=true)String id, Model model, RedirectAttributes redirectAttributes) {
		String path = "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/";
		
		ReferenceMessage rm = referenceMessageService.get(id);
		String workCenterId = rm.getWorkCenter().getId();
		ScollingJobPlan scollingJobPlan = scollingJobPlanService.onlyOneUnpublishedPlan(workCenterId);
		if (scollingJobPlan == null) {
			addMessage(redirectAttributes, "引用参考消息失败");
			return path;
		}		
		String scollingJobPlanId = scollingJobPlan.getId();
		
		ScollingJobPlanTask scollingJobPlanTask = new ScollingJobPlanTask();
		scollingJobPlanTask.setScollingJobPlan(scollingJobPlan);
		Task task =new Task();
		task.setGzrwmc(rm.getBt());
		task.setMbjyq(rm.getNr());
		task.setJhkssj(rm.getJhkssj());
		task.setJhwcsj(rm.getJhwcsj());
		scollingJobPlanTask.setTask(task);
		
		model.addAttribute("referenceMessageId", id);//
		model.addAttribute("scollingJobPlanTask", scollingJobPlanTask);
		model.addAttribute("scollingJobPlanId", scollingJobPlanId);
		path = "modules/"+"ip/scollingJobPlanTaskFormCkxx";
		
		return path;
	}
	
	@ResponseBody
	@RequestMapping("checkBt")
	public String checkBt(String bt, String oldBt) {
		if (bt != null && oldBt.equals(bt)) {
			return "true";
		} else if (bt != null && referenceMessageService.findByBt(bt) == null) {
			return "true";
		}
		return "false";
	}
}
