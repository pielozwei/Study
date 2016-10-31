/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;


/**
 * 滚动工作计划Entity
 * @author LiHR
 * @version 2016-08-09
 */
@Entity
@Table(name = "IP_GDGZJH", uniqueConstraints = {@UniqueConstraint(columnNames={"jhbh"})})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScollingJobPlan extends IdEntity<ScollingJobPlan> {
	
	private static final long serialVersionUID = 1L;
	
	private WorkCenter workCenter;// 计划所属工作中心
	
	@NotNull
	@Length(min = 1, max = 64)
	private String jhfzrId;// 计划负责人_ID
	
	@NotNull
	@Length(min = 1, max = 30)
	private String fzr;// 计划负责人
	
	@NotNull
	@Length(min = 1, max = 64)
	private String jhxzrId;// 计划协助人_ID
	
	@NotNull
	@Length(min = 1, max = 30)
	private String xzr;// 计划协助人

	@NotNull
	@Length(min = 1, max = 30)
	private String jhbh;// 计划编号

	@NotNull
	@Length(min = 1, max = 100)
	private String jhmc;// 计划名称

	@NotNull
	private Integer fbzt;// 发布状态

	@NotNull
	private Date jhkssj;// 计划开始时间

	@NotNull
	private Date jhjzsj;// 计划截止时间

	public String getJhfzrId() {
		return jhfzrId;
	}
	public void setJhfzrId(String jhfzrId) {
		this.jhfzrId = jhfzrId;
	}
	@Length(min = 0, max = 30)
	private String bz;// 备注
	
	public ScollingJobPlan() {
		super();
	}
	public ScollingJobPlan(String id){
		this();
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="GZZX_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public WorkCenter getWorkCenter() {
		return workCenter;
	}
	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}
	public String getJhxzrId() {
		return jhxzrId;
	}
	public void setJhxzrId(String jhxzrId) {
		this.jhxzrId = jhxzrId;
	}
	public String getJhbh() {
		return jhbh;
	}
	public void setJhbh(String jhbh) {
		this.jhbh = jhbh;
	}
	public String getJhmc() {
		return jhmc;
	}
	public void setJhmc(String jhmc) {
		this.jhmc = jhmc;
	}
	public Integer getFbzt() {
		return fbzt;
	}
	public void setFbzt(Integer fbzt) {
		this.fbzt = fbzt;
	}
	public Date getJhkssj() {
		return jhkssj;
	}
	public void setJhkssj(Date jhkssj) {
		this.jhkssj = jhkssj;
	}
	public Date getJhjzsj() {
		return jhjzsj;
	}
	public void setJhjzsj(Date jhjzsj) {
		this.jhjzsj = jhjzsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getXzr() {
		return xzr;
	}
	public void setXzr(String xzr) {
		this.xzr = xzr;
	}
	
	
}


