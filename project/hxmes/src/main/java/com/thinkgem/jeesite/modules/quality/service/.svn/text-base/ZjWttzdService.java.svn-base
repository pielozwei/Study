/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.quality.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.quality.entity.ZjWttzd;
import com.thinkgem.jeesite.modules.quality.dao.ZjWttzdDao;
import com.thinkgem.jeesite.modules.sys.dao.RoleDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;

/**
 * 质量问题通知单Service
 * @author LiuBJ
 * @version 2016-08-25
 */
@Component
@Transactional(readOnly = true)
public class ZjWttzdService extends BaseService {

	@Autowired
	private ZjWttzdDao zjWttzdDao;
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	private IdentityService identityService;
	
	private String processDefinitionKey = "issueNotice";
	
	public ZjWttzd get(String id) {
		return zjWttzdDao.get(id);
	}
	
	public Page<ZjWttzd> find(Page<ZjWttzd> page, ZjWttzd zjWttzd) {
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}
		
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(ZjWttzd zjWttzd) {
		zjWttzdDao.save(zjWttzd);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		zjWttzdDao.deleteById(id);
	}

	public Page<ZjWttzd> findQczbryHistory(Page<ZjWttzd> page, ZjWttzd zjWttzd,String userId) {
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//查询起草质保人员的历史任务
		List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
				.createHistoricTaskInstanceQuery()//创建历史任务实例查询
				.taskCandidateUser(userId)
				.list();
		List<String> con = new ArrayList<String>();
		//查询出质保起草员处理过的问题单列表
		for(HistoricTaskInstance hti : list){
			String processInstanceId = hti.getProcessInstanceId();
			if(processInstanceId!=null){
				con.add(processInstanceId);
			}
		}
		if(null!=con&&con.size()>0){
			dc.add(Restrictions.in("processInstanceId", con));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "lcl"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public Page<ZjWttzd> getCompleteTask(Page<ZjWttzd> page, ZjWttzd zjWttzd,String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//根据当前用户的id查询出该用户要处理的任务
		List<Task> list = taskService//
                .createTaskQuery()//
                .taskCandidateUser(userId)//指定组任务查询
                .list();
		List<String> processInstances = new ArrayList<>();
		//得到该任务的流程实例ID
		if(null!=list && list.size()>0){
			for(Task task : list){
				if(null!=task.getProcessInstanceId()){
					processInstances.add(task.getProcessInstanceId());
				}
			}
		}
		if(null!=processInstances && processInstances.size()>0){
			dc.add(Restrictions.in("processInstanceId", processInstances));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "-1"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public void start1(ZjWttzd zjWttzd,HttpServletRequest request) {
		//将车间信息放置到session作用域中
		request.getSession().setAttribute("sszzjg", zjWttzd.getDwZr().getId());
		//从数据库中查询出相应的车间负责人
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq("name","起草质保人员"));
		List<Role> roles = roleDao.find(dc);
		request.getSession().setAttribute("roles", roles);
		//开启流程实例
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
		//设置流程实例id
		zjWttzd.setProcessInstanceId(processInstance.getId());
		//查询出该流程实例启动时间
		/*Deployment deployment = repositoryService
			.createDeploymentQuery()
			.deploymentId(processInstance
			.getDeploymentId())
			.singleResult();*/
		//将流程开始时间保存到质量问题单中
		//zjWttzd.setStartTime(deployment.g);
		//将流程状态保存到质量问题单中
		zjWttzd.setProcessStatus(taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult().getName());
		//保存质保员起草信息
		zjWttzdDao.save(zjWttzd);
	}
	/**
	 * 流程部署
	 */
	public void processDeploy() {
		Deployment deployment = repositoryService
				.createDeployment()
				.addClasspathResource("diagrams/leave/issueNotice.bpmn")
				.addClasspathResource("diagrams/leave/issueNotice.png")
				.deploy();
		System.out.println(deployment.getId());
	}

	public Page<ZjWttzd> requestList(Page<ZjWttzd> page, ZjWttzd zjWttzd,
			String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//查询质量问题是否有流程实例id
		dc.add(Restrictions.isNull("processInstanceId"));
		dc.add(Restrictions.eq("jcr", userId));
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public void zbryComplete(ZjWttzd zjWttzd,String userId,HttpServletRequest request) {
		//通过流程实例id获取任务
		List<Task> list=taskService
				.createTaskQuery()
				.processInstanceId(zjWttzd.getProcessInstanceId())
				.taskCandidateUser(userId)
				.list();
		//通过角色名，查询出角色列表
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq("name","问题确认车间负责人"));
		List<Role> roles = roleDao.find(dc);
		request.getSession().setAttribute("roles", roles);
		//将相应的机构放置到作用域
		request.getSession().setAttribute("sszzjg", zjWttzd.getDwZr().getId());
		if(null!=list && list.size()>0){
			for(Task task : list){
				taskService.complete(task.getId());
				zjWttzd.setProcessStatus(taskService.createTaskQuery().processInstanceId(zjWttzd.getProcessInstanceId()).singleResult().getName());
				//保存问题通知单
				zjWttzdDao.save(zjWttzd);
			}
		}
	}
/***************************************************问题确认车间负责人********************************************************/
	public Page<ZjWttzd> findWtqrcjfzrHistory(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//查询问题确认车间负责人的历史任务
		List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
				.createHistoricTaskInstanceQuery()//创建历史任务实例查询
				.taskCandidateUser(userId)
				.list();
		List<String> con = new ArrayList<String>();
		//查询出问题确认车间负责人处理过的问题单列表
		for(HistoricTaskInstance hti : list){
			String processInstanceId = hti.getProcessInstanceId();
			if(processInstanceId!=null){
				con.add(processInstanceId);
			}
		}
		if(null!=con&&con.size()>0){
			dc.add(Restrictions.in("processInstanceId", con));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "lcl"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public Page<ZjWttzd> getCompleteTaskWtqrcjfzr(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//根据当前用户的id查询出该用户要处理的任务
		List<Task> list = taskService//
                .createTaskQuery()//
                .taskCandidateUser(userId)//指定组任务查询
                .list();
		List<String> processInstances = new ArrayList<>();
		//得到该任务的流程实例ID
		if(null!=list && list.size()>0){
			for(Task task : list){
				if(null!=task.getProcessInstanceId()){
					processInstances.add(task.getProcessInstanceId());
				}
			}
		}
		if(null!=processInstances && processInstances.size()>0){
			dc.add(Restrictions.in("processInstanceId", processInstances));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "-1"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public void wtqrcjfzrComplete(ZjWttzd zjWttzd, String userId,HttpServletRequest request) {
		//通过流程实例id获取任务
		List<Task> list=taskService
				.createTaskQuery()
				.processInstanceId(zjWttzd.getProcessInstanceId())
				.taskCandidateUser(userId)
				.list();
		//通过角色名，查询出角色列表
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq("name","审核车间单位负责人"));
		List<Role> roles = roleDao.find(dc);
		request.getSession().setAttribute("roles", roles);
		//将相应的机构放置到作用域
		request.getSession().setAttribute("sszzjg", zjWttzd.getDwJd().getId());
		if(null!=list && list.size()>0){
			for(Task task : list){
				taskService.complete(task.getId());
				zjWttzd.setProcessStatus(taskService.createTaskQuery().processInstanceId(zjWttzd.getProcessInstanceId()).singleResult().getName());
				//保存问题通知单
				String paramsTrans;
				try {
					paramsTrans = new String(zjWttzd.getJcdwYj().getBytes("ISO-8859-1"),"UTF-8");
					String jcdwYj = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
					if(null != jcdwYj){
						zjWttzd.setJcdwYj(jcdwYj);						
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				zjWttzdDao.save(zjWttzd);
			}
		}	
	}

	
	/***********************************************审核车间单位负责人******************************************************/
	public Page<ZjWttzd> findShcjdwfzrHistory(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//查询问题确认车间负责人的历史任务
		List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
				.createHistoricTaskInstanceQuery()//创建历史任务实例查询
				.taskCandidateUser(userId)
				.list();
		List<String> con = new ArrayList<String>();
		//查询出问题确认车间负责人处理过的问题单列表
		for(HistoricTaskInstance hti : list){
			String processInstanceId = hti.getProcessInstanceId();
			if(processInstanceId!=null){
				con.add(processInstanceId);
			}
		}
		if(null!=con&&con.size()>0){
			dc.add(Restrictions.in("processInstanceId", con));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "lcl"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public Page<ZjWttzd> getCompleteTaskShcjdwfzr(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//根据当前用户的id查询出该用户要处理的任务
		List<Task> list = taskService//
                .createTaskQuery()//
                .taskCandidateUser(userId)//指定组任务查询
                .list();
		List<String> processInstances = new ArrayList<>();
		//得到该任务的流程实例ID
		if(null!=list && list.size()>0){
			for(Task task : list){
				if(null!=task.getProcessInstanceId()){
					processInstances.add(task.getProcessInstanceId());
				}
			}
		}
		if(null!=processInstances && processInstances.size()>0){
			dc.add(Restrictions.in("processInstanceId", processInstances));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "-1"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public void shcjdwfzrComplete(ZjWttzd zjWttzd, String userId,HttpServletRequest request) {
		//通过流程实例id获取任务
		List<Task> list=taskService
				.createTaskQuery()
				.processInstanceId(zjWttzd.getProcessInstanceId())
				.taskCandidateUser(userId)
				.list();
		//通过角色名，查询出角色列表
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq("name","完成情况填报质量员"));
		List<Role> roles = roleDao.find(dc);
		request.getSession().setAttribute("roles", roles);
		//将相应的机构放置到作用域
		request.getSession().setAttribute("sszzjg", zjWttzd.getDwJd().getId());
		if(null!=list && list.size()>0){
			for(Task task : list){
				taskService.complete(task.getId());
				zjWttzd.setProcessStatus(taskService.createTaskQuery().processInstanceId(zjWttzd.getProcessInstanceId()).singleResult().getName());
				//保存问题通知单
				String paramsTrans;
				try {
					paramsTrans = new String(zjWttzd.getJcdwFzr().getBytes("ISO-8859-1"),"UTF-8");
					String jcdwFzr = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
					if(null != jcdwFzr){
						zjWttzd.setJcdwFzr(jcdwFzr);						
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				zjWttzdDao.save(zjWttzd);
			}
		}	
		
	}

	/***************************************************完成情况填报质量员*****************************************************/
	public Page<ZjWttzd> findWcqktbzlyHistory(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//查询问题确认车间负责人的历史任务
		List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
				.createHistoricTaskInstanceQuery()//创建历史任务实例查询
				.taskCandidateUser(userId)
				.list();
		List<String> con = new ArrayList<String>();
		//查询出问题确认车间负责人处理过的问题单列表
		for(HistoricTaskInstance hti : list){
			String processInstanceId = hti.getProcessInstanceId();
			if(processInstanceId!=null){
				con.add(processInstanceId);
			}
		}
		if(null!=con&&con.size()>0){
			dc.add(Restrictions.in("processInstanceId", con));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "lcl"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public Page<ZjWttzd> getCompleteTaskWcqktbzly(Page<ZjWttzd> page,ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//根据当前用户的id查询出该用户要处理的任务
		List<Task> list = taskService//
                .createTaskQuery()//
                .taskCandidateUser(userId)//指定组任务查询
                .list();
		List<String> processInstances = new ArrayList<>();
		//得到该任务的流程实例ID
		if(null!=list && list.size()>0){
			for(Task task : list){
				if(null!=task.getProcessInstanceId()){
					processInstances.add(task.getProcessInstanceId());
				}
			}
		}
		if(null!=processInstances && processInstances.size()>0){
			dc.add(Restrictions.in("processInstanceId", processInstances));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "-1"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public void wcqktbzlyComplete(ZjWttzd zjWttzd, String userId,HttpServletRequest request) {
		//通过流程实例id获取任务
		List<Task> list=taskService
				.createTaskQuery()
				.processInstanceId(zjWttzd.getProcessInstanceId())
				.taskCandidateUser(userId)
				.list();
		//通过角色名，查询出角色列表
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq("name","验证关闭质保人员"));
		List<Role> roles = roleDao.find(dc);
		request.getSession().setAttribute("roles", roles);
		//将相应的机构放置到作用域
		request.getSession().setAttribute("sszzjg", zjWttzd.getDwJc().getId());
		if(null!=list && list.size()>0){
			for(Task task : list){
				taskService.complete(task.getId());
				zjWttzd.setProcessStatus(taskService.createTaskQuery().processInstanceId(zjWttzd.getProcessInstanceId()).singleResult().getName());
				//保存问题通知单
				String paramsTrans;
				String paramsTrans1;
				try {
					paramsTrans = new String(zjWttzd.getZgTb().getBytes("ISO-8859-1"),"UTF-8");
					String zgTb = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
					if(null != zgTb){
						zjWttzd.setZgTb(zgTb);						
					}
					paramsTrans1 = new String(zjWttzd.getZgTbr().getBytes("ISO-8859-1"),"UTF-8");
					String zgTbr = java.net.URLDecoder.decode(paramsTrans1 , "UTF-8");
					if(null != zgTbr){
						zjWttzd.setZgTbr(zgTbr);						
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				zjWttzdDao.save(zjWttzd);
			}
		}	
		
	}

	/*********************************************************验证关闭质保员*******************************************************/
	public Page<ZjWttzd> findYzgbzbryHistory(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//查询问题确认车间负责人的历史任务
		List<HistoricTaskInstance> list = historyService//与历史数据（历史表）相关的Service
				.createHistoricTaskInstanceQuery()//创建历史任务实例查询
				.taskCandidateUser(userId)
				.list();
		List<String> con = new ArrayList<String>();
		//查询出问题确认车间负责人处理过的问题单列表
		for(HistoricTaskInstance hti : list){
			String processInstanceId = hti.getProcessInstanceId();
			if(processInstanceId!=null){
				con.add(processInstanceId);
			}
		}
		if(null!=con&&con.size()>0){
			dc.add(Restrictions.in("processInstanceId", con));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "lcl"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public Page<ZjWttzd> getCompleteTaskYzgbzbry(Page<ZjWttzd> page,
			ZjWttzd zjWttzd, String userId) {
		/*DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			dc.add(Restrictions.eq("xuhao", zjWttzd.getXuhao()));
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getId())){
			dc.add(Restrictions.eq("dwZr.id", zjWttzd.getDwZr().getId()));
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			dc.add(Restrictions.eq("processStatus", zjWttzd.getProcessStatus()));
		}*/
		DetachedCriteria dc = zjWttzdDao.createDetachedCriteria();
		dc.createAlias("dwZr", "dwZr");
		if (StringUtils.isNotEmpty(zjWttzd.getXuhao())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getXuhao().getBytes("ISO-8859-1"),"UTF-8");
				String xuhao = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("xuhao", xuhao));
				zjWttzd.setXuhao(xuhao);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (null!=zjWttzd && null!=zjWttzd.getDwZr() && StringUtils.isNotEmpty(zjWttzd.getDwZr().getName())){	
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getDwZr().getName().getBytes("ISO-8859-1"),"UTF-8");
				String dwZrName = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("dwZr.name", dwZrName));
				Office office = new Office();
				office.setName(dwZrName);
				zjWttzd.setDwZr(office);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(zjWttzd.getProcessStatus())){
			String paramsTrans;
			try {
				paramsTrans = new String(zjWttzd.getProcessStatus().getBytes("ISO-8859-1"),"UTF-8");
				String processStatus = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
				dc.add(Restrictions.eq("processStatus", processStatus));
				zjWttzd.setProcessStatus(processStatus);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		dc.add(Restrictions.eq(ZjWttzd.FIELD_DEL_FLAG, ZjWttzd.DEL_FLAG_NORMAL));
		//根据当前用户的id查询出该用户要处理的任务
		List<Task> list = taskService//
                .createTaskQuery()//
                .taskCandidateUser(userId)//指定组任务查询
                .list();
		List<String> processInstances = new ArrayList<>();
		//得到该任务的流程实例ID
		if(null!=list && list.size()>0){
			for(Task task : list){
				if(null!=task.getProcessInstanceId()){
					processInstances.add(task.getProcessInstanceId());
				}
			}
		}
		if(null!=processInstances && processInstances.size()>0){
			dc.add(Restrictions.in("processInstanceId", processInstances));
		}else{
			dc.add(Restrictions.eq("processInstanceId", "-1"));
		}
		dc.addOrder(Order.desc("id"));
		return zjWttzdDao.find(page, dc);
	}

	public void yzgbzbryComplete(ZjWttzd zjWttzd, String userId,HttpServletRequest request) {
		//通过流程实例id获取任务
		List<Task> list=taskService
				.createTaskQuery()
				.processInstanceId(zjWttzd.getProcessInstanceId())
				.taskCandidateUser(userId)
				.list();
		//通过角色名，查询出角色列表
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq("name","验证关闭质保人员"));
		List<Role> roles = roleDao.find(dc);
		request.getSession().setAttribute("roles", roles);
		//将相应的机构放置到作用域
		request.getSession().setAttribute("sszzjg", zjWttzd.getDwJc().getId());
		if(null!=list && list.size()>0){
			for(Task task : list){
				taskService.complete(task.getId());
				zjWttzd.setProcessStatus("完成");
				//保存问题通知单
				String paramsTrans;
				String paramsTrans1;
				String paramsTrans2;
				try {
					paramsTrans = new String(zjWttzd.getZgYz().getBytes("ISO-8859-1"),"UTF-8");
					String zgYz = java.net.URLDecoder.decode(paramsTrans , "UTF-8");
					if(null != zgYz){
						zjWttzd.setZgYz(zgYz);						
					}
					
					paramsTrans1 = new String(zjWttzd.getZgYzr().getBytes("ISO-8859-1"),"UTF-8");
					String zgYzr = java.net.URLDecoder.decode(paramsTrans1 , "UTF-8");
					if(null != zgYzr){
						zjWttzd.setZgYzr(zgYzr);					
					}
					
					paramsTrans2 = new String(zjWttzd.getRemarks().getBytes("ISO-8859-1"),"UTF-8");
					String remarks = java.net.URLDecoder.decode(paramsTrans2 , "UTF-8");
					if(null != remarks){
						zjWttzd.setRemarks(remarks);					
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				zjWttzdDao.save(zjWttzd);
			}
		}		
	}	
	
}
