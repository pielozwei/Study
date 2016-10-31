/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.ArrayList;
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
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlan;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlanTask;
import com.thinkgem.jeesite.modules.ip.entity.Task;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanTaskService;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterService;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanService;
import com.thinkgem.jeesite.modules.ip.util.ScollingJobPlanUtil;

/**
 * 滚动工作计划Controller
 * @author LiHR
 * @version 2016-08-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ip/scollingJobPlan")
public class ScollingJobPlanController extends BaseController {

	@Autowired
	private ScollingJobPlanService scollingJobPlanService;
	@Autowired
	private WorkCenterService workCenterService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ScollingJobPlanTaskService scollingJobPlanTaskService;
	
	@ModelAttribute
	public ScollingJobPlan get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return scollingJobPlanService.get(id);
		}else{
			return new ScollingJobPlan();
		}
	}
	
	@RequiresPermissions("ip:scollingJobPlan:view")
	@RequestMapping(value = "listHosted")
	public String listHosted(ScollingJobPlan scollingJobPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		//此方法是查询历史滚动工作计划：即    当前用户角色     已发布  的计划; 注意：当前用可能是多个工作中心的负责人或者协助人
		User user = UserUtils.getUser();//当前用户
		Page<ScollingJobPlan> paramPage = new Page<ScollingJobPlan>(request, response);
        Page<ScollingJobPlan> page = scollingJobPlanService.findHost(paramPage, scollingJobPlan, user.getId()); 
        model.addAttribute("page", page);
		return "modules/" + "ip/scollingJobPlanListHost";
	}
	
	@RequiresPermissions("ip:scollingJobPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(ScollingJobPlan scollingJobPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();//当前工作中心负责人或者协助人
		if (!user.isAdmin()){
			scollingJobPlan.setCreateBy(user);
		}
		Page<ScollingJobPlan> paramPage = new Page<ScollingJobPlan>(request, response);
        Page<ScollingJobPlan> page = scollingJobPlanService.find(paramPage, scollingJobPlan,user.getId()); 
        model.addAttribute("page", page);
		return "modules/" + "ip/scollingJobPlanList";
	}

	/**
	 * 获取新增时能添加计划的工作中心
	 * @return
	 */
	public List<WorkCenter> getCanAdd(){
		List<WorkCenter> listAll = ScollingJobPlanUtil.selectWorkCentersByUserId(UserUtils.getUser().getId());
		List<WorkCenter> listWorkCenter = new ArrayList<WorkCenter>();
		for (WorkCenter wc : listAll) {
			if (!scollingJobPlanService.hasNotPublished(wc.getId())) {
				listWorkCenter.add(wc);
			}
		}
		return listWorkCenter;
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "canAdd")
	public boolean canAdd(HttpServletResponse response) {
		return getCanAdd().size() > 0 ? true : false;
	}
	
	@RequiresPermissions("ip:scollingJobPlan:view")
	@RequestMapping(value = "form")
	public String form(ScollingJobPlan scollingJobPlan, Model model) {
		User user = UserUtils.getUser();//当前工作中心负责人或者协助人
		if (StringUtils.isNotBlank(user.getId())){
			//开始查找该用户是几个工作中心的负责人或者协助人
			List<WorkCenter> listWorkCenter = getCanAdd();//新增前做了验证---所以工作中心至少有一个
			if (StringUtils.isNotBlank(scollingJobPlan.getId())) {
				listWorkCenter.add(scollingJobPlan.getWorkCenter());
			}
			if (listWorkCenter.size() == 0) {//如果有检查异常的话，就不给提示，刷新列表页面，重新新增
				return "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
			}
			model.addAttribute("listWorkCenter", listWorkCenter);
		}
	
		model.addAttribute("scollingJobPlan", scollingJobPlan);
		return "modules/" + "ip/scollingJobPlanForm";
	}
	
	/**
	 * 查找该工作中心的负责人和协助人（用list存，第一个为负责人，第二个为协助人）
	 */
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "selectUserByWorkCenterId")
	protected List<User> selectUserByWorkCenterId(String workCenterId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<User> listUser = scollingJobPlanService.getFzrAndXzr(workCenterId);
		return listUser;
	}
	
	/**
	 * 修改或者新增计划时，逻辑验证
	 * @param  ScollingJobPlan 实体对象
	 * @return String 返回第一个错误消息
	 * @author lhr
	 * */
	public String checkScollingJobPlan(ScollingJobPlan scollingJobPlan) {
		String message = null;
		WorkCenter wc = workCenterService.get(scollingJobPlan.getWorkCenter().getId());
		
	//1.创建计划时，同一个工作中心  当前计划里  没有一个未发布的计划--一个工作中心只有一个计划未发布
		boolean isAdd = false;
		if (StringUtils.isBlank(scollingJobPlan.getId())) {
			isAdd = true;
		}
		if (isAdd && scollingJobPlanService.hasNotPublished(wc.getId())) {//如果有未发布的　不能新增
			message = "保存失败，工作中心【" + wc.getGzzxmc() + "】当前还有为发布的计划，请发布后再来创建计划";
		}
	//2.判断发布状态 为 未发布，否则不可保存
		if (scollingJobPlan.getFbzt() != 0) {//不是未发布
			message = "保存失败，已发布，不能保存";
			return message;
		}
		
		//本工作中心 最大的计划截止时间+1天
		Date jhkssj = scollingJobPlan.getJhkssj();
		Date jhjzsj = scollingJobPlan.getJhjzsj();
		//Date maxJhjzsj = scollingJobPlanService.selectMaxJhjzsj(wc.getId(), scollingJobPlan.getId());
		Date currentDate = DateUtils.getDateStart(new Date());
	//3.新增/修改计划时:
		//1).计划截止时间>=开始时间: [计划截止时间不能早于开始时间]， ---
		//2).也不能早于当前时间: [计划截止时间不能早于今天(xxxx-xx-xx)]---计划开始时间=<计划截止时间,当前日期=<计划截止时间
		/*//3).每个计划的周期不冲突：[计划的开始时间要大于本工作中心所有计划的截止时间] --计划开始时间>=所有截止时间+1天（没截止时间就可以随便填）
		 * if (maxJhjzsj !=null && jhkssj.getTime() < maxJhjzsj.getTime()) {//有可能是第一个计划
			message = "保存失败，计划的开始时间要大于本工作中心所有其他计划的截止时间";
			return message;
		}*/
		if (jhjzsj.getTime() < jhkssj.getTime()) {
			message = "保存失败，计划截止时间不能早于开始时间";
			return message;
		}
		if (jhjzsj.getTime() < currentDate.getTime()) {
			message = "保存失败，计划截止时间不能早于今天";
			return message;
		}
		
		return message;
	}
	
	@RequiresPermissions("ip:scollingJobPlan:edit")
	@RequestMapping(value = "save")
	public String save(ScollingJobPlan scollingJobPlan, Model model, RedirectAttributes redirectAttributes) {
		String path = "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
		String message = null;
		if (scollingJobPlan == null) {
			message = "保存失败，该对象不存在，被删除了或者非法输入";
			addMessage(redirectAttributes, message);
			return path;
		}
		message = "保存滚动工作计划'" + scollingJobPlan.getJhmc() + "'成功";
		
		String oldJhbh = "";
		boolean isAdd = false;
		if (StringUtils.isBlank(scollingJobPlan.getId())) {
			isAdd = true;
		}
		
		if (!isAdd) {
			ScollingJobPlan s = scollingJobPlanService.get(scollingJobPlan.getId());
			if (s == null) {
				message = "保存失败，该滚动工作计划已不存在，不能修改";
				addMessage(redirectAttributes, message);
				return path;
			}
			oldJhbh = s.getJhbh();
		}
		if ("false".equals(checkJhbh(scollingJobPlan.getJhbh(), oldJhbh))) {
			message = "保存失败，该滚动工作计划编号已存在";
			addMessage(redirectAttributes, message);
			return path;
		}
		if (!beanValidator(model, scollingJobPlan)){
			return form(scollingJobPlan, model);
		}
		String msg = checkScollingJobPlan(scollingJobPlan);
		if (msg == null) {
			scollingJobPlanService.save(scollingJobPlan);
			WorkCenter wc = workCenterService.get(scollingJobPlan.getWorkCenter().getId());
			if (isAdd) {//新增计划时，要把 该工作中心  历史计划里 未完成的任务 加进来  并且 这些任务不在当前计划里
				List<Task> listTask = scollingJobPlanTaskService.doingTask(wc.getId());//历史计划中未完成的任务
				if (listTask.size() > 0) {
					for (Task task : listTask) {
						ScollingJobPlanTask s = new ScollingJobPlanTask();
						s.setScollingJobPlan(scollingJobPlan);
						s.setTask(task);
						scollingJobPlanTaskService.save(s);
					}
				}
			}
		} else {
			message = msg;
		}
		
		addMessage(redirectAttributes, message);
		return path;
	}
	
	@RequiresPermissions("ip:scollingJobPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes) {
		scollingJobPlanService.delete(id);
		addMessage(redirectAttributes, "删除滚动工作计划成功");
		return "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
	}
	
	@RequiresPermissions("ip:scollingJobPlan:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		for(String id:ids){
			if(scollingJobPlanService.get(id).getFbzt() == 0) {
				List<ScollingJobPlanTask> list = scollingJobPlanTaskService.findByJhId(id);
				for (ScollingJobPlanTask st : list) {
					scollingJobPlanTaskService.delete(st.getId());
				}
				scollingJobPlanService.delete(id);
			} else {
				addMessage(redirectAttributes, "删除失败，已发布的不能删除");
				return "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
			}
			
		}
		addMessage(redirectAttributes, "删除"+ids.length+"个滚动工作计划成功");
		return "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
	}
	
	@RequiresPermissions("ip:scollingJobPlan:edit")
	@RequestMapping(value = "publishList")
	public String publishList(String[] ids, RedirectAttributes redirectAttributes) {//发布计划
		String path = "redirect:"+Global.getAdminPath()+"/ip/scollingJobPlan/";
		String msg = "";
		//整体思路：无论选中的是已发布的还是未发布的计划--都可以发布----发布之时发布未发布的有任务的计划
		//第一次循环，获取没有任务的计划信息
		for(String id:ids){
			ScollingJobPlan scollingJobPlan = scollingJobPlanService.get(id);
			if(scollingJobPlan.getFbzt()==0) {//未发布
				List<ScollingJobPlanTask> list = scollingJobPlanTaskService.findByJhId(id);
				if (list == null || list.size() == 0) {
					msg += "计划'" + scollingJobPlan.getJhmc() + "'中没有任务不能发布;";
				}
			}
		}
		//如果所有的这些计划都有任务，进入第2次循环--进行发布计划，以及计划下的任务
		if (msg.equals("")) {
			msg = "发布成功";
			for(String id:ids){
				ScollingJobPlan scollingJobPlan = scollingJobPlanService.get(id);
				if(scollingJobPlan.getFbzt()==0) {//未发布
					scollingJobPlan.setFbzt(1);//hibernate 持久化
					//scollingJobPlanService.save(scollingJobPlan);//手动改数据库，持久化就会识别不了了
					//一旦发布计划，其下面的所有任务都要一起发布
					//发布该计划下的所有任务
					scollingJobPlanTaskService.releaseTaskByScollingJobPlanId(scollingJobPlan.getId());
				}
			}
		} else msg = "发布失败:" + msg.substring(0, msg.lastIndexOf(";")) + ".";
		
		addMessage(redirectAttributes, msg);
		return path;
	}
	
	@RequiresPermissions("ip:scollingJobPlan:view")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ScollingJobPlan scollingJobPlan, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "滚动工作计划数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx"; 
    		Page<ScollingJobPlan> page = scollingJobPlanService.find(new Page<ScollingJobPlan>(request, response), scollingJobPlan,null); 
    		new ExportExcel("滚动工作计划数据", ScollingJobPlan.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出滚动工作计划失败！失败信息："+e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/ip/scollingJobPlan/";
    }

	@ResponseBody
	@RequestMapping("checkJhbh")
	public String checkJhbh(String jhbh, String oldJhbh) {
		if (jhbh != null && oldJhbh.equals(jhbh)) {
			return "true";
		} else if (jhbh != null && scollingJobPlanService.findByJhbh(jhbh) == null) {
			return "true";
		}
		return "false";
	}
	
}
