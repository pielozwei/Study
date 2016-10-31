/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
 * 参考消息Entity
 * @author LiHR
 * @version 2016-08-18
 */
@Entity
@Table(name = "IP_CKXX")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReferenceMessage extends IdEntity<ReferenceMessage> {
	
	private static final long serialVersionUID = 1L;
	private WorkCenter workCenter; 	//接收工作中心_ID 
	private User fsr;//发送人_IDfsr_id
	
	@NotNull
	@Length(min=1, max=100)
	private String bt;//标题	VARCHAR2(100)
	
	@Length(min=0, max=500)
	private String nr;//内容	VARCHAR2(500)
	private Date jhkssj;//计划开始时间
	private Date jhwcsj;//计划完成时间	DATE
	@NotNull
	private Integer sffs;//是否发送   SMALLINT
	@NotNull
	private Integer sfyd;//是否已读	SMALLINT
	@NotNull
	private Integer sfyyg;//是否引用过	SMALLINT
	private Date fssj;//发送时间DATE
	
	public ReferenceMessage() {
		super();
	}
	public ReferenceMessage(String id){
		this();
		this.id = id;
	}
	@NotNull
	@ManyToOne
	@JoinColumn(name="JSGZZX_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public WorkCenter getWorkCenter() {
		return workCenter;
	}
	public void setWorkCenter(WorkCenter workCenter) {
		this.workCenter = workCenter;
	}
	@NotNull
	@ManyToOne
	@JoinColumn(name="FSR_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public User getFsr() {
		return fsr;
	}
	public void setFsr(User fsr) {
		this.fsr = fsr;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
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
	public Integer getSffs() {
		return sffs;
	}
	public void setSffs(Integer sffs) {
		this.sffs = sffs;
	}
	public Integer getSfyd() {
		return sfyd;
	}
	public void setSfyd(Integer sfyd) {
		this.sfyd = sfyd;
	}
	public Integer getSfyyg() {
		return sfyyg;
	}
	public void setSfyyg(Integer sfyyg) {
		this.sfyyg = sfyyg;
	}
	public Date getFssj() {
		return fssj;
	}
	public void setFssj(Date fssj) {
		this.fssj = fssj;
	}
}


