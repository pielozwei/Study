/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlanTask;
import com.thinkgem.jeesite.modules.ip.entity.Task;
import com.thinkgem.jeesite.modules.ip.dao.ScollingJobPlanTaskDao;
import com.thinkgem.jeesite.modules.ip.dao.TaskDao;

/**
 * 滚动工作计划任务Controller
 * @author LiHR
 * @version 2016-08-11
 */
@Component
@Transactional(readOnly = true)
public class ScollingJobPlanTaskService extends BaseService {

	@Autowired
	private ScollingJobPlanTaskDao scollingJobPlanTaskDao;
	@Autowired
	private TaskDao taskDao;
	
	public ScollingJobPlanTask get(String id) {
		return scollingJobPlanTaskDao.get(id);
	}
	/*计划里的任务*/
	public Page<ScollingJobPlanTask> find(Page<ScollingJobPlanTask> page, ScollingJobPlanTask scollingJobPlanTask, String scollingJobPlanId) {
		DetachedCriteria dc = scollingJobPlanTaskDao.createDetachedCriteria();
		dc.createAlias("task", "task");
		if (StringUtils.isNotEmpty(scollingJobPlanId)){
			dc.add(Restrictions.eq("scollingJobPlan.id", scollingJobPlanId));
		}
		Task task = scollingJobPlanTask.getTask();
		if (task != null && StringUtils.isNotEmpty(task.getGzrwmc())) {
			dc.add(Restrictions.like("task.gzrwmc", "%"+scollingJobPlanTask.getTask().getGzrwmc()+"%"));
		}
		dc.addOrder(Order.asc("task.createDate"));
		return scollingJobPlanTaskDao.find(page, dc);
	}
	
/*	任务中心:所负责的工作中心的非历史计划里的任务--已发布的任务
	public Page<ScollingJobPlanTask> findCenter(Page<ScollingJobPlanTask> page, ScollingJobPlanTask scollingJobPlanTask) {
		DetachedCriteria dc = scollingJobPlanTaskDao.createDetachedCriteria();
		dc.createAlias("task", "task");
		dc.createAlias("scollingJobPlan", "scollingJobPlan");
		String userId = UserUtils.getUser().getId();
		dc.add(Restrictions.or(Restrictions.eq("scollingJobPlan.jhfzrId", userId), Restrictions.eq("scollingJobPlan.jhxzrId", userId)));
		//dc.add(Restrictions.in("scollingJobPlan.workCenter.id", ScollingJobPlanUtil.selectWorkCenterIdsByUserId(userId)));
		dc.add(Restrictions.eq("task.fbzt", 1));
		//任务 已完成的 + 已取消的 + 最新计划里进行中的---
		查询满足单个工作中心的最新创建的计划里的进行中的任务
		List<String> list = ScollingJobPlanUtil.selectWorkCenterIdsByUserId(userId);
		String hql = "";
		for (int i=0; i < list.size(); i++) {
			hql += " or EXISTS(select 1 from IP_GDGZJH s,IP_GZRW t"
					+ " where scollingjo2_.id = s.id and task1_.id = t.id and t.zxzt = 1"
					+ " and scollingjo2_.GZZX_ID = '" + list.get(i) 
					+ "' and scollingjo2_.CREATE_DATE = (select max(CREATE_DATE) from IP_GDGZJH s1 where s1.GZZX_ID = '" + list.get(i) 
					+ "')) ";
		}
		dc.add(Restrictions.sqlRestriction("(task1_.zxzt != 1 " + hql + ")"));
		
		Task task = scollingJobPlanTask.getTask();
		if (task != null && StringUtils.isNotEmpty(task.getGzrwmc())) {
			dc.add(Restrictions.like("task.gzrwmc", "%"+scollingJobPlanTask.getTask().getGzrwmc()+"%"));
		}
		dc.addOrder(Order.asc("task.createDate"));
		return scollingJobPlanTaskDao.find(page, dc);
	}*/
	
	// 该工作中心  历史计划里 未完成的任务 并且当前计划里没有这些任务
	public List<Task> doingTask(String workCenterId) {
		return scollingJobPlanTaskDao.findDoingTask(workCenterId);
	}
	
	@Transactional(readOnly = false)
	public void save(ScollingJobPlanTask scollingJobPlanTask) {
		scollingJobPlanTaskDao.clear();
		scollingJobPlanTaskDao.save(scollingJobPlanTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {//这里的删除应该是删除计划里的任务 并不删除任务--只删除子表  如果该任务已不存在计划里，就是垃圾数据，在这里检查下并回收
		Session session = scollingJobPlanTaskDao.getSession();
		
		ScollingJobPlanTask scollingJobPlanTask = scollingJobPlanTaskDao.get(id);
		Task task = scollingJobPlanTask.getTask();
		String taskId = task.getId();
		
		if (scollingJobPlanTask != null) {
			session.delete(scollingJobPlanTask);
		}
		
		List<ScollingJobPlanTask> list = findByTaskId(taskId);
		if (list.size() == 0) {
			if (task != null) {
				session.delete(task);
			}
		}
	}
	
	public List<ScollingJobPlanTask> findByTaskId(String taskId) {
		DetachedCriteria dc = scollingJobPlanTaskDao.createDetachedCriteria();
		dc.add(Restrictions.eq("task.id", taskId));
		return scollingJobPlanTaskDao.find(dc);
	}
	
	public List<ScollingJobPlanTask> findAll() {
		return scollingJobPlanTaskDao.find(scollingJobPlanTaskDao.createDetachedCriteria());
	}
	
	public List<ScollingJobPlanTask> findByJhId(String id) {//根据计划id查
		DetachedCriteria dc = scollingJobPlanTaskDao.createDetachedCriteria();
		dc.add(Restrictions.eq("scollingJobPlan.id", id));
		return scollingJobPlanTaskDao.find(dc);
	}
	//根据计划id 发布旗下的所有任务
	public boolean releaseTaskByScollingJobPlanId(String scollingJobPlanId) {
		return scollingJobPlanTaskDao.releaseTaskByScollingJobPlanId(scollingJobPlanId);
	}
}
