/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.thinkgem.jeesite.common.persistence.IdEntity;


/**
 * 滚动工作计划任务Entity
 * @author LiHR
 * @version 2016-08-11
 */
@Entity
@Table(name = "ip_gdgzjhrw")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScollingJobPlanTask extends IdEntity<ScollingJobPlanTask> {
	
	private static final long serialVersionUID = 1L;
	private Task task; 	// 工作任务
	private ScollingJobPlan scollingJobPlan; // 滚动工作计划
	
	public ScollingJobPlanTask() {
		super();
	}
	public ScollingJobPlanTask(String id){
		this();
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="GZRW_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	@ManyToOne
	@JoinColumn(name="GDGZJH_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public ScollingJobPlan getScollingJobPlan() {
		return scollingJobPlan;
	}
	public void setScollingJobPlan(ScollingJobPlan scollingJobPlan) {
		this.scollingJobPlan = scollingJobPlan;
	}
}


