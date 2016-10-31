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
import com.thinkgem.jeesite.modules.sys.entity.User;


/**
 * 工作任务Entity
 * @author LiHR
 * @version 2016-08-09
 */
@Entity
@Table(name = "IP_gzrw", uniqueConstraints = {@UniqueConstraint(columnNames={"gzrwmc"})})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends IdEntity<Task> {
	
	private static final long serialVersionUID = 1L;
//	任务协助人_ID	VARCHAR2(64
	private User rwxzr;
	
//	任务负责人_ID	VARCHAR2(64)
	private User rwfzr;
	
//	发布状态	INTEGER
	@NotNull
	private Integer fbzt;
	
//	执行状态	INTEGER
	@NotNull
	private Integer zxzt;
	
//	工作任务名称		VARCHAR2(200)
	@NotNull
	@Length(min = 1, max = 200)
	private String gzrwmc;
	
//	计划开始时间		DATE
	@NotNull
	private Date jhkssj;
	
//	计划完成时间		DATE
	@NotNull
	private Date jhwcsj;
	
//	完成期限	INTEGER
	@NotNull
	private Integer wcqx;
	
//	任务附件名称		VARCHAR2(100)
	@Length(min = 0, max = 100)
	private String rwfjmc;
//	任务附件文件		VARCHAR2(200)
	@Length(min = 0, max = 200)
	private String rwfjwj;
//	目标及要求	VARCHAR2(500)
	@Length(min = 0, max = 500)
	private String mbjyq;
//	任务难度	INTEGER
	private Integer rwnd;
//	实际开始时间		DATE
	private Date sjkssj;
//	实际完成时间		DATE
	private Date sjwcsj;
//	完成情况说明		VARCHAR2(100)
	@Length(min = 0, max = 100)
	private String wcqksm;
//	进展程度	INTEGER
	private Integer jzcd;
//	进展情况说明		VARCHAR2(30)
	@Length(min = 0, max = 30)
	private String jzqksm;
//	是否按期完成		SMALLINT
	private Short sfaqwc;
//	取消类型		INTEGER
	private Integer qxlx;
//	取消原因	VARCHAR2(100)
	@Length(min = 0, max = 100)
	private String qxyy;
//	取消时间	DATE
	private Date qxsj;
//	备注	VARCHAR2(500)
	@Length(min = 0, max = 500)
	private String bz;
	
	public Task() {
		super();
	}
	public Task(String id){
		this();
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "rwxzrId")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@NotNull(message = "协助人不能为空")
	public User getRwxzr() {
		return rwxzr;
	}
	public void setRwxzr(User rwxzr) {
		this.rwxzr = rwxzr;
	}
	@ManyToOne
	@JoinColumn(name = "rwfzrId")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@NotNull(message = "协助人不能为空")
	public User getRwfzr() {
		return rwfzr;
	}
	public void setRwfzr(User rwfzr) {
		this.rwfzr = rwfzr;
	}
	public Integer getFbzt() {
		return fbzt;
	}
	public void setFbzt(Integer fbzt) {
		this.fbzt = fbzt;
	}
	public Integer getZxzt() {
		return zxzt;
	}
	public void setZxzt(Integer zxzt) {
		this.zxzt = zxzt;
	}
	public String getGzrwmc() {
		return gzrwmc;
	}
	public void setGzrwmc(String gzrwmc) {
		this.gzrwmc = gzrwmc;
	}
	public Date getJhkssj() {
		return jhkssj;
	}
	public void setJhkssj(Date jhkssj) {
		this.jhkssj = jhkssj;
	}
	public Date getJhwcsj() {
		return jhwcsj;
	}
	public void setJhwcsj(Date jhwcsj) {
		this.jhwcsj = jhwcsj;
	}
	public Integer getWcqx() {
		return wcqx;
	}
	public void setWcqx(Integer wcqx) {
		this.wcqx = wcqx;
	}
	public String getRwfjmc() {
		return rwfjmc;
	}
	public void setRwfjmc(String rwfjmc) {
		this.rwfjmc = rwfjmc;
	}
	public String getRwfjwj() {
		return rwfjwj;
	}
	public void setRwfjwj(String rwfjwj) {
		this.rwfjwj = rwfjwj;
	}
	public String getMbjyq() {
		return mbjyq;
	}
	public void setMbjyq(String mbjyq) {
		this.mbjyq = mbjyq;
	}
	public Integer getRwnd() {
		return rwnd;
	}
	public void setRwnd(Integer rwnd) {
		this.rwnd = rwnd;
	}
	public Date getSjkssj() {
		return sjkssj;
	}
	public void setSjkssj(Date sjkssj) {
		this.sjkssj = sjkssj;
	}
	public Date getSjwcsj() {
		return sjwcsj;
	}
	public void setSjwcsj(Date sjwcsj) {
		this.sjwcsj = sjwcsj;
	}
	public String getWcqksm() {
		return wcqksm;
	}
	public void setWcqksm(String wcqksm) {
		this.wcqksm = wcqksm;
	}
	public Integer getJzcd() {
		return jzcd;
	}
	public void setJzcd(Integer jzcd) {
		this.jzcd = jzcd;
	}
	public String getJzqksm() {
		return jzqksm;
	}
	public void setJzqksm(String jzqksm) {
		this.jzqksm = jzqksm;
	}
	public Short getSfaqwc() {
		return sfaqwc;
	}
	public void setSfaqwc(Short sfaqwc) {
		this.sfaqwc = sfaqwc;
	}
	public Integer getQxlx() {
		return qxlx;
	}
	public void setQxlx(Integer qxlx) {
		this.qxlx = qxlx;
	}
	public String getQxyy() {
		return qxyy;
	}
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	public Date getQxsj() {
		return qxsj;
	}
	public void setQxsj(Date qxsj) {
		this.qxsj = qxsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}

}


