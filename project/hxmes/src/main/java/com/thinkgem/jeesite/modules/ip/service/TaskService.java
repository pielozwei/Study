/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.Task;
import com.thinkgem.jeesite.modules.ip.util.ScollingJobPlanUtil;
import com.thinkgem.jeesite.modules.ip.dao.TaskDao;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 工作任务Controller
 * @author LiHR
 * @version 2016-08-15
 */
@Component("TaskService")
@Transactional(readOnly = true)
public class TaskService extends BaseService {

	@Autowired
	private TaskDao taskDao;
	
	public Task get(String id) {
		return taskDao.get(id);
	}
	
	public Page<Task> find(Page<Task> page, Task task,String taskId) {
		DetachedCriteria dc = taskDao.createDetachedCriteria();
		dc.createAlias("task", "task");
		if (StringUtils.isNotEmpty(taskId)){
			dc.add(Restrictions.eq("task.id", taskId));
		}
		if (StringUtils.isNotEmpty(task.getGzrwmc())){
			dc.add(Restrictions.like("gzrwmc", "%"+task.getGzrwmc()+"%"));
		}
		return taskDao.find(page, dc);
	}
	
	public void clear(){
		taskDao.clear();
	}
	@Transactional(readOnly = false)
	public void save(Task task) {
		taskDao.clear();
		if (task != null) {
			taskDao.save(task);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		Task task = taskDao.get(id);
		if (task != null) {
			taskDao.getSession().delete(task);
		}
	}
	
	public List<Task> findByTaskId(String taskId) {
		DetachedCriteria dc = taskDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Task.FIELD_DEL_FLAG, Task.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("task.id", taskId));
		return taskDao.find(dc);
	}
	public List<Task> findAll() {
		return taskDao.find(taskDao.createDetachedCriteria());
	}

	public Task findByName(String name) {
		return taskDao.findByName(name);
	}

	public Page<Task> findPerson(Page<Task> page, Task task) {
		//必须是任务所在计划发布了 --- 也可以是只差已发布的任务
		DetachedCriteria dc = taskDao.createDetachedCriteria();
		String userId = UserUtils.getUser().getId();
		dc.add(Restrictions.eq("fbzt", 1));
		dc.add(Restrictions.or(Restrictions.eq("rwxzr.id", userId), Restrictions.eq("rwfzr.id", userId)));
		
		if (task != null && StringUtils.isNotEmpty(task.getGzrwmc())) {
			dc.add(Restrictions.like("gzrwmc", "%"+task.getGzrwmc()+"%"));
		}
		
		dc.addOrder(Order.asc("createDate"));
		return taskDao.find(page, dc, Criteria.DISTINCT_ROOT_ENTITY);
	}

	public Page<Task> findCenter(Page<Task> page, Task task) {
		DetachedCriteria dc = taskDao.createDetachedCriteria();
		dc.add(Restrictions.eq("fbzt", 1));
		
		String userId = UserUtils.getUser().getId();
		List<String> list = ScollingJobPlanUtil.selectWorkCenterIdsByUserId(userId);//负责的工作中心
		String workCenterIdIn = "";
		for (int i=0; i < list.size(); i++) {
			if (i == 0)
				workCenterIdIn += "'" + list.get(i) + "'";
			else workCenterIdIn += ",'" + list.get(i) + "'";
		}
		dc.add(Restrictions.sqlRestriction("EXISTS(select 1 from IP_GDGZJH s,IP_GDGZJHRW st where st.GDGZJH_ID = s.id and st.gzrw_id = this_.id and s.gzzx_id in (" + workCenterIdIn + "))"));
		
		if (task != null && StringUtils.isNotEmpty(task.getGzrwmc())) {
			dc.add(Restrictions.like("gzrwmc", "%"+task.getGzrwmc()+"%"));
		}
		dc.addOrder(Order.asc("createDate"));
		return taskDao.find(page, dc);
	}
}
