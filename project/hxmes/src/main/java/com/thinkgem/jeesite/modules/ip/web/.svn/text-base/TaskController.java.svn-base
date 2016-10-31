/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.thinkgem.jeesite.modules.ip.entity.Task;
import com.thinkgem.jeesite.modules.ip.service.ReferenceMessageService;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanService;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanTaskService;
import com.thinkgem.jeesite.modules.ip.service.TaskService;

/**
 * 滚动工作计划任务Controller
 * @author LiHR
 * @version 2016-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/task")
public class TaskController extends BaseController {

	@Autowired
	private ScollingJobPlanTaskService scollingJobPlanTaskService;
	@Autowired
	private ScollingJobPlanService scollingJobPlanService;
	@Autowired
	@Qualifier("TaskService")
	private TaskService taskService;
	@Autowired
	private ReferenceMessageService referenceMessageService;
	
	@ModelAttribute
	public Task get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return taskService.get(id);
		}else{
			return new Task();
		}
	}
	
	@RequiresPermissions("ip:task:view")
	@RequestMapping(value = "listCenter")
	/*任务中心列表---已发布的任务*/
	public String listCenter(Task task, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			task.setCreateBy(user);
		}
		Page<Task> paramPage = new Page<Task>(request, response);
        Page<Task> page = taskService.findCenter(paramPage, task); 
        model.addAttribute("page", page);
		return "modules/" + "ip/scollingJobPlanTaskListCenter";
	}
	
	//中心任务编辑页
	@RequiresPermissions("ip:task:view")
	@RequestMapping(value = "formCenter")
	public String formCenter(Task task, Model model) {
		model.addAttribute("task", task);
		return "modules/" + "ip/taskFormCenter";
	}
	
	@RequiresPermissions("ip:task:view")
	@RequestMapping(value = {"list",""})
	/*个人任务列表*/
	public String listPerson(Task task, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			task.setCreateBy(user);
		}
		Page<Task> paramPage = new Page<Task>(request, response);
        Page<Task> page = taskService.findPerson(paramPage, task); 
        model.addAttribute("page", page);
		return "modules/" + "ip/scollingJobPlanTaskListPerson";
	}

	//个人任务编辑页
	@RequiresPermissions("ip:task:view")
	@RequestMapping(value = "form")
	public String form(Task task, Model model) {
		model.addAttribute("task", task);
		return "modules/" + "ip/scollingJobPlanTaskFormPerson";
	}
	
	/**
	 * 修改或者新增任务时，逻辑验证
	 * @param  ScollingJobPlan 实体对象
	 * @return String 返回第一个错误消息
	 * @author lhr
	 * */
	public String checkTask(Task task) {
		String message = null;
	//能进入编辑的几种情况
		//能够新增或者修改的任务的状态 执行状态 是否按期完成 进展程度
		if (StringUtils.isBlank(task.getId()) || task.getFbzt() == 0) {
			if (!(task.getFbzt() == 0 && task.getZxzt() == 1 && task.getSfaqwc() == 1 && task.getJzcd() == 0)) {
				message = "保存失败，初始化发布状态或者执行状态或者是否按期完成或者进展程度错误！";
				return message;
			}
		} else if (task.getFbzt() == 1) {//已发布发布
			if (task.getZxzt() == 3) {
				message = "保存失败，任务已取消，不能修改";
				return message;
			}
		}
		
		//2.任务的计划完成时间大于等于计划开始时间
		if (task.getJhwcsj().getTime() < task.getJhkssj().getTime()) {
			message = "保存失败，任务的计划完成时间不晚于计划开始时间";
			return message;
		}
		Date sjwcsj = task.getSjwcsj();
		Date sjkssj = task.getSjkssj();
		int jzcd = task.getJzcd();
		int zxzt = task.getZxzt();
		int sfaqwc = task.getSfaqwc();//0-是 1-否
		if (sjkssj == null) {
			if (sjwcsj != null) {
				message = "保存失败，有实际完成时间，却没有实际开始时间";
				return message;
			}
			if (zxzt != 1) {
				message = "保存失败，实际开始时间为空，任务执行状态应为进行中";
				return message;
			}
			if (sfaqwc != 1) {
				message = "保存失败，实际开始时间为空，按期完成情况应为否";
				return message;
			}
			if (jzcd != 0) {
				message = "保存失败，实际开始时间为空，任务进展程度应为0%";
				return message;
			}
		} else {
			if (sjwcsj == null) {
				if (zxzt != 1) {
					message = "保存失败，实际完成时间为空，任务执行状态应为进行中";
					return message;
				}
				if (sfaqwc != 1) {
					message = "保存失败，实际完成时间为空，按期完成情况应为否";
					return message;
				}
				if (jzcd == 10) {
					message = "保存失败，实际完成时间为空，任务进展程度不应为100%";
					return message;
				}
			} else {//实际开始时间完成时间均不为空
				if (sjwcsj.getTime() < sjkssj.getTime()) {
					message = "保存失败，任务的实际完成时间不晚于实际开始时间";
					return message;
				}
				//5.任务的实际完成时间不为空，任务执行状态只能是已完成，是否按期完成可以计算出来了，进展程度只能是100%
				if (zxzt != 2) {
					message = "保存失败，实际完成时间不为空，任务执行状态应为已完成";
					return message;
				}
				int cur_Sfaqwc = task.getJhwcsj().getTime() >= sjwcsj.getTime() ? 0 : 1;
				String cur_Sfaqwc_Value = cur_Sfaqwc == 0 ? "是" : "否";
				if (sfaqwc != cur_Sfaqwc) {
					message = "保存失败，实际完成时间不为空，按期完成情况应为" + cur_Sfaqwc_Value;
					return message;
				}
				if (jzcd != 10) {
					message = "保存失败，实际完成时间不为空，任务进展程度应为100%";
					return message;
				}
			}
		}
		
		return message;
	}
	
	public String checkName_save(String name, String oldName) {
		if (name != null && oldName.equals(name)) {
			return "true";
		} else if (name != null && taskService.findByName(name) == null) {
			return "true";
		}
		return "false";
	}
	
	@RequiresPermissions("ip:task:edit")
	@RequestMapping(value = "save")
	public String save(Task task, Model model, RedirectAttributes redirectAttributes) {
		String path = "redirect:"+Global.getAdminPath()+"/ip/task/";
		String message = null;
		if (task == null) {
			message = "保存失败，该任务对象不存在，被删除了或者非法输入";
			addMessage(redirectAttributes, message);
			return path;
		}
		message = "保存任务'" + task.getGzrwmc() + "'成功";
		
		String oldGzrwmc = "";
		if (StringUtils.isNotBlank(task.getId())) {
			oldGzrwmc = taskService.get(task.getId()).getGzrwmc();
		}
		if ("false".equals(checkName_save(task.getGzrwmc(), oldGzrwmc))) {
			message = "保存失败，该任务名称已存在";
			addMessage(redirectAttributes, message);
			return path;
		}
		if (!beanValidator(model, task)){
			return form(task, model);
		}
		String msg = checkTask(task);
		if (msg == null) {
			taskService.save(task);
		} else {
			message = msg;
			taskService.clear();
		}
		
		addMessage(redirectAttributes, message);
		return path;
	}
	
}
