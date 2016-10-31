/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlanTask;
import com.thinkgem.jeesite.modules.ip.entity.Task;

/**
 * 滚动工作计划任务DAO接口
 * @author LiHR
 * @version 2016-08-11
 */
@Repository
public class ScollingJobPlanTaskDao extends BaseDao<ScollingJobPlanTask> {

	public boolean releaseTaskByScollingJobPlanId(String scollingJobPlanId) {
		String qlString = "";
		Query query = null;
		if (StringUtils.isNotBlank(scollingJobPlanId)) {
			qlString = "update Task set fbzt=1"
						+ " where  EXISTS(select 1 from ScollingJobPlan as s,ScollingJobPlanTask as st,Task as t" 
						+ " where s.id=st.scollingJobPlan.id"
						+ " and t.id=st.task.id and s.id = :p1)";
			query = getSession().createQuery(qlString);
			query.setString("p1", scollingJobPlanId);
		}
		if (query==null) {
			return false;
		} else {
			query.executeUpdate();
			return true;
		}
		
	}

	/**
	 * 查出当前工作中心里所有历史计划中进行中的任务
	 * @param workCenterId 工作中心id
	 * @return List<Task>
	 */
	@SuppressWarnings("unchecked")
	public List<Task> findDoingTask(String workCenterId) {
		//hql思路：从任务表里查出既是发布且进行中的任务又是当前工作中心的历史计划里的任务
		//Date today = DateUtils.getDateStart(new Date());
		String hql = "from Task t where t.zxzt = 1 and t.fbzt = 1 and "
						+ "EXISTS (select 1 from ScollingJobPlanTask s1 where t.id = s1.task.id and s1.scollingJobPlan.workCenter.id = :p1)";
		Query query = getSession().createQuery(hql);
		query.setString("p1", workCenterId);
		//query.setTimestamp("p2", today);//注意这里，用setTimestamp﻿e，不要用setDate
		
		List<Task> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Page<ScollingJobPlanTask> findCenter(Page<ScollingJobPlanTask> page, DetachedCriteria detachedCriteria) {
		// get count
		if (!page.isDisabled() && !page.isNotCount()){
			page.setCount(count(detachedCriteria));
			if (page.getCount() < 1) {
				return page;
			}
		}
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// set page
		if (!page.isDisabled()){
	        criteria.setFirstResult(page.getFirstResult());
	        criteria.setMaxResults(page.getMaxResults()); 
		}
		// order by
		if (StringUtils.isNotBlank(page.getOrderBy())){
			for (String order : StringUtils.split(page.getOrderBy(), ",")){
				String[] o = StringUtils.split(order, " ");
				if (o.length==1){
					criteria.addOrder(Order.asc(o[0]));
				}else if (o.length==2){
					if ("DESC".equals(o[1].toUpperCase())){
						criteria.addOrder(Order.desc(o[0]));
					}else{
						criteria.addOrder(Order.asc(o[0]));
					}
				}
			}
		}
		page.setList(criteria.list());
		return page;
	}
}
