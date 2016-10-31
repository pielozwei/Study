/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlan;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlanTask;
import com.thinkgem.jeesite.modules.ip.entity.Task;
import com.thinkgem.jeesite.modules.ip.service.ReferenceMessageService;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanService;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanTaskService;
import com.thinkgem.jeesite.modules.ip.service.TaskService;
import com.thinkgem.jeesite.modules.ip.util.ScollingJobPlanUtil;

/**
 * 滚动工作计划任务Controller
 * @author LiHR
 * @version 2016-08-11
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/scollingJobPlanTask")
public class ScollingJobPlanTaskController extends BaseController {

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
	public ScollingJobPlanTask get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return scollingJobPlanTaskService.get(id);
		}else{
			return new ScollingJobPlanTask();
		}
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:view")
	@RequestMapping(value = {"list", ""})
	/*非历史      滚动工作计划里的任务列表---*/
	public String list(ScollingJobPlanTask scollingJobPlanTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			scollingJobPlanTask.setCreateBy(user);
		}
		
		String scollingJobPlanId = request.getParameter("scollingJobPlanId");
		model.addAttribute("scollingJobPlanId", scollingJobPlanId);
		Page<ScollingJobPlanTask> paramPage = new Page<ScollingJobPlanTask>(request, response);
        Page<ScollingJobPlanTask> page = scollingJobPlanTaskService.find(paramPage, scollingJobPlanTask, scollingJobPlanId); 
        model.addAttribute("page", page);
        model.addAttribute("task", new Task());
        ScollingJobPlan  s = scollingJobPlanService.get(scollingJobPlanId);
        //计划发布状态
       // model.addAttribute("jhfbzt", s.getFbzt());
        model.addAttribute("jhmc", s.getJhmc());
        model.addAttribute("gzzxmc", s.getWorkCenter().getGzzxmc());
		return "modules/" + "ip/scollingJobPlanTaskList";
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:view")
	@RequestMapping(value = "listHost")
	/*历史      滚动工作计划里的任务列表*/
	public String listHost(ScollingJobPlanTask scollingJobPlanTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			scollingJobPlanTask.setCreateBy(user);
		}
		String scollingJobPlanId = request.getParameter("scollingJobPlanId");
		Page<ScollingJobPlanTask> paramPage = new Page<ScollingJobPlanTask>(request, response);
        Page<ScollingJobPlanTask> page = scollingJobPlanTaskService.find(paramPage, scollingJobPlanTask, scollingJobPlanId); 
        model.addAttribute("page", page);
        model.addAttribute("scollingJobPlanId", scollingJobPlanId);
		return "modules/" + "ip/scollingJobPlanTaskListHost";
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:view")
	@RequestMapping(value = "form")
	public String form(ScollingJobPlanTask scollingJobPlanTask, String scollingJobPlanId, Model model) {
		if (scollingJobPlanTask != null && StringUtils.isNotEmpty(scollingJobPlanTask.getId()) && StringUtils.isBlank(scollingJobPlanId)) {
			scollingJobPlanId = scollingJobPlanTask.getScollingJobPlan().getId();
		}
		if (StringUtils.isEmpty(scollingJobPlanTask.getId())) {
			scollingJobPlanTask.setScollingJobPlan(scollingJobPlanService.get(scollingJobPlanId));
		}
		
		model.addAttribute("scollingJobPlanId", scollingJobPlanId);
		model.addAttribute("scollingJobPlanTask", scollingJobPlanTask);
		return "modules/" + "ip/scollingJobPlanTaskForm";
	}

	@RequiresPermissions("ip:scollingJobPlanTask:view")
	@RequestMapping(value = "formReleased")
	public String formReleased(ScollingJobPlanTask scollingJobPlanTask, String scollingJobPlanId, Model model) {
		if (scollingJobPlanTask != null && StringUtils.isNotEmpty(scollingJobPlanTask.getId()) && StringUtils.isBlank(scollingJobPlanId)) {
			scollingJobPlanId = scollingJobPlanTask.getScollingJobPlan().getId();
		}
		
		model.addAttribute("scollingJobPlanId", scollingJobPlanId);
		model.addAttribute("scollingJobPlanTask", scollingJobPlanTask);
		return "modules/" + "ip/scollingJobPlanTaskFormReleased";
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:view")
	@RequestMapping(value = "formCanceled")
	public String formCanceled(ScollingJobPlanTask scollingJobPlanTask, String scollingJobPlanId, Model model) {
		if (scollingJobPlanTask != null && StringUtils.isNotEmpty(scollingJobPlanTask.getId()) &&StringUtils.isBlank(scollingJobPlanId)) {
			scollingJobPlanId = scollingJobPlanTask.getScollingJobPlan().getId();
		}
		
		model.addAttribute("scollingJobPlanId", scollingJobPlanId);
		model.addAttribute("scollingJobPlanTask", scollingJobPlanTask);
		return "modules/" + "ip/scollingJobPlanTaskFormCanceled";
	}
	
	/**
	 * 修改或者新增任务时，逻辑验证
	 * @param  ScollingJobPlan 实体对象
	 * @return String 返回第一个错误消息
	 * @author lhr
	 * */
	public String checkScollingJobPlanTask(ScollingJobPlanTask scollingJobPlanTask) {
		String message = null;
		Task task = scollingJobPlanTask.getTask();
		//ScollingJobPlan scollingJobPlan = scollingJobPlanService.get(scollingJobPlanTask.getScollingJobPlan().getId());
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
		
	//编辑项之间的逻辑关系
		//1.任务的计划开始时间 大于等于 任务所在计划的计划开始时间---有可能是历史计划里的任务
		/*if (task.getJhkssj().getTime() < scollingJobPlan.getJhkssj().getTime()) {
			message = "保存失败，任务的计划开始时间 不晚于 任务所在计划的计划开始时间";
			return message;
		}*/
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
	
	@RequiresPermissions("ip:scollingJobPlanTask:edit")
	@RequestMapping(value = "save")
	public String save(ScollingJobPlanTask scollingJobPlanTask, String scollingJobPlanId, String referenceMessageId, Model model, RedirectAttributes redirectAttributes) {
		//计划id是不是当前用户管理的范围 这个就不在验证了。。没人闲的没事乱整 就算是范围内又能验证什么，还是可以非法输入
		String path = "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlanTask/?scollingJobPlanId="+scollingJobPlanId;
		String message = null;
		if (scollingJobPlanTask == null) {
			message = "保存失败，该任务对象不存在，被删除了或者非法输入";
			addMessage(redirectAttributes, message);
			return path;
		}
		if (!scollingJobPlanId.equals(scollingJobPlanTask.getScollingJobPlan().getId())) {
			message = "非法输入";
			addMessage(redirectAttributes, message);
			path = "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
			return path;
		}
		
		message = "保存滚动工作计划任务'" + scollingJobPlanTask.getTask().getGzrwmc() + "'成功";
		if (StringUtils.isNotBlank(referenceMessageId)) {//引用消息--失败好跳转呀--成功也跳
			path = "redirect:"+Global.getAdminPath()+"/ip/referenceMessage/listForWorkCenter";
			message = "引用消息失败";
		}
		
		String oldGzrwmc = "";
		Task task = scollingJobPlanTask.getTask();
		if (StringUtils.isNotBlank(task.getId())) {
			oldGzrwmc = taskService.get(task.getId()).getGzrwmc();
		}
		if ("false".equals(checkName_save(task.getGzrwmc(), oldGzrwmc))) {
			message = "保存失败，该任务名称已存在";
			addMessage(redirectAttributes, message);
			return path;
		}
		if (!beanValidator(model, scollingJobPlanTask)){
			return form(scollingJobPlanTask, scollingJobPlanId, model);
		}
		//完善数据
		//由于只能部分修改，所以保存时先查出来在一项一项修改，不能修改的不动---如果发布状态、执行状态都是伪造的就只能给页面加密了
		Task t = new Task();
		if (StringUtils.isBlank(scollingJobPlanTask.getId())) {
			//添加时初始化(任务汇报项)和(取消项): 实际开始完成时间为空,执行状态为进行中，是否按期完成为否，进展程度为0
			t.setGzrwmc(task.getGzrwmc());
			t.setWcqx(task.getWcqx());
			t.setRwfzr(task.getRwfzr());
			t.setRwxzr(task.getRwxzr());
			t.setRwfjwj(task.getRwfjwj());
			t.setMbjyq(task.getMbjyq());
			t.setJhkssj(task.getJhkssj());
			t.setJhwcsj(task.getJhwcsj());
			t.setBz(task.getBz());
			t.setFbzt(0);
			t.setZxzt(1);
			t.setSfaqwc(new Short("1"));
			t.setJzcd(0);
		} else if (task.getFbzt() == 0) {//未发布
			t.setId(task.getId());
			t.setGzrwmc(task.getGzrwmc());
			t.setWcqx(task.getWcqx());
			t.setRwfzr(task.getRwfzr());
			t.setRwxzr(task.getRwxzr());
			t.setRwfjwj(task.getRwfjwj());
			t.setMbjyq(task.getMbjyq());
			t.setJhkssj(task.getJhkssj());
			t.setJhwcsj(task.getJhwcsj());
			t.setBz(task.getBz());
			t.setFbzt(0);
			t.setZxzt(1);
			t.setSfaqwc(new Short("1"));
			t.setJzcd(0);
		} else if (task.getFbzt() == 1 && task.getZxzt() != 3) {//已发布，未取消
			t = task;
		}
		scollingJobPlanTask.setTask(t);
		String msg = checkScollingJobPlanTask(scollingJobPlanTask);
		if (msg == null) {
			taskService.save(t);
			scollingJobPlanTask.setTask(t);
			scollingJobPlanTaskService.save(scollingJobPlanTask);
			
			if (StringUtils.isNotBlank(referenceMessageId)) {//引用消息--失败好跳转呀--成功也跳
				path = "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlanTask/?scollingJobPlanId="+scollingJobPlanId;
				boolean b = referenceMessageService.quote(referenceMessageId);	
				if (b) {
					message = "引用消息成功";
				}
			}
		} else {
			if (StringUtils.isNotBlank(referenceMessageId)) {//引用消息--失败好跳转呀--成功也跳
				message = "引用消息失败";
			} else {
				message = msg;
				taskService.clear();
			}
		}
		
		addMessage(redirectAttributes, message);
		return path;
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:edit")
	@RequestMapping(value = "cancelTask")
	public String cancelTask(ScollingJobPlanTask scollingJobPlanTask, Model model, RedirectAttributes redirectAttributes) {
		//取消原因 /取消类型/取消状态/ 取消时间
		ScollingJobPlan scollingJobPlan = scollingJobPlanTask.getScollingJobPlan();
		String scollingJobPlanId = scollingJobPlan.getId();
		Task task = scollingJobPlanTask.getTask();
		String path = "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlanTask/?scollingJobPlanId="+scollingJobPlanId;
		//取消的前提，1.任务未取消
		if (task.getZxzt() == 3) {
			addMessage(redirectAttributes, "该任务已取消");
			return path;
		}
		//取消的前提，2.任务已发布
		if (task.getFbzt() == 0) {
			addMessage(redirectAttributes, "该任务未发布");
			return path;
		}
		//取消的前提，3.计划已发布
		if (scollingJobPlan.getFbzt() == 0) {
			addMessage(redirectAttributes, "该任务所在的计划未发布");
			return path;
		}
		
		
		int qxlx = task.getQxlx();
		String qxyy = task.getQxyy();
		String id = task.getId();
		if (StringUtils.isNotBlank(id)) {
			task = taskService.get(id);
			task.setQxlx(qxlx);
			task.setQxyy(qxyy);
			task.setZxzt(3);
			task.setQxsj(new Date());
		} else {
			addMessage(redirectAttributes, "id不能为空");
			return path;
		}
		taskService.save(task);
		addMessage(redirectAttributes, "取消任务成功");
		return path;
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		String scollingJobPlanId = scollingJobPlanTaskService.get(id).getScollingJobPlan().getId();
		scollingJobPlanTaskService.delete(id);
		addMessage(redirectAttributes, "删除滚动工作计划任务成功");
		return "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlanTask/?scollingJobPlanId="+scollingJobPlanId;
	}
	
	@RequiresPermissions("ip:scollingJobPlanTask:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		String scollingJobPlanId = scollingJobPlanTaskService.get(ids[0]).getScollingJobPlan().getId();
		for(String id:ids){
			scollingJobPlanTaskService.delete(id);
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个滚动工作计划任务成功");
		return "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlanTask/?scollingJobPlanId="+scollingJobPlanId;
	}
	
	/*@RequiresPermissions("ip:scollingJobPlanTask:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ScollingJobPlanTask scollingJobPlanTask, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "滚动工作计划任务数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<ScollingJobPlanTask> page = scollingJobPlanTaskService.find(new Page<ScollingJobPlanTask>(request, response), scollingJobPlanTask, null); 
    		new ExportExcel("滚动工作计划任务数据", ScollingJobPlanTask.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出滚动工作计划任务失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/scollingJobPlanTask/";
    }*/

	/**
	 * 查找该工作中心的所有人
	 */
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "getAllMemberByWorkCenterId")
	protected List<Map<String, Object>> getAllMemberByWorkCenterId(String scollingJobPlanId, HttpServletResponse response) {
		String workCenterId = "";
		if (StringUtils.isNotBlank(scollingJobPlanId)) {
			workCenterId = scollingJobPlanService.get(scollingJobPlanId).getWorkCenter().getId();
		}
		return ScollingJobPlanUtil.selectAllMemberByWorkCenterId(workCenterId);
	}

	
	/**
	 * 打印计划
	 * @author lhr
	 * @param  ids
	 * */
	@RequiresPermissions("ip:scollingJobPlanTask:edit")
	@RequestMapping(value = "print")
	public String print(String[] ids, Model model) {
		for(String id:ids){
			scollingJobPlanTaskService.get(id);
		}
		return "modules/ip/scollingJobPlanTask/scollingJobPlanTaskList_print";
	}
	
	@ResponseBody
	@RequestMapping("checkName")
	public String checkName(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("task.gzrwmc");
		String oldName = request.getParameter("oldName");
		if (name != null && oldName.equals(name)) {
			return "true";
		} else if (name != null && taskService.findByName(name) == null) {
			return "true";
		}
		return "false";
	}
	
	public String checkName_save(String name, String oldName) {
		if (name != null && oldName.equals(name)) {
			return "true";
		} else if (name != null && taskService.findByName(name) == null) {
			return "true";
		}
		return "false";
	}
}
