/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.quality.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 质量问题通知单Entity
 * @author LiuBJ
 * @version 2016-08-25
 */
@Entity
@Table(name = "zj_wttzd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ZjWttzd extends IdEntity<ZjWttzd> {
	
	private static final long serialVersionUID = 1L;
	private String xuhao;		// 序号
	private String processInstanceId;		// 流程实例编号
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Office dwZr;		// 责任单位
	private Office dwJd;		// 监督单位
	private Office dwJc;		// 检查单位
	private Date dwJcdate;		// 检查日期
	private String wtms;		// 问题描述
	private String jcdwYj;		// 被监督单位意见
	private String jcdwFzr;		// 被检查单位负责人
	private Date jcdwDate;		// 被检查单位确认日期
	private String zgTb;		// 整改措施及完成情况
	private String zgTbr;		// 整改完成填报人
	private Date zgTbrq;		// 整改完成填报日期
	private String zgYz;		// 整改完成验证情况
	private String zgYzr;		// 整改完成验证人
	private Date zgYzrq;		// 整改完成验证日期
	private String processStatus;		// 流程状态
	
	/**吕春亮添加字段**/
	private String jcr;    //检查人

	public ZjWttzd() {
		super();
	}

	public ZjWttzd(String id){
		this();
		this.id = id;
	}
	 
	
	
	@Length(min=0, max=16, message="序号长度必须介于 0 和 16 之间")
	public String getXuhao() {
		return xuhao;
	}
	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}
	
	@Length(min=0, max=64, message="流程实例编号长度必须介于 0 和 64 之间")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@ManyToOne
	public Office getDwZr() {
		return dwZr;
	}

	public void setDwZr(Office dwZr) {
		this.dwZr = dwZr;
	}
	@ManyToOne
	public Office getDwJd() {
		return dwJd;
	}

	public void setDwJd(Office dwJd) {
		this.dwJd = dwJd;
	}
	@ManyToOne
	public Office getDwJc() {
		return dwJc;
	}

	public void setDwJc(Office dwJc) {
		this.dwJc = dwJc;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDwJcdate() {
		return dwJcdate;
	}
	public void setDwJcdate(Date dwJcdate) {
		this.dwJcdate = dwJcdate;
	}
	
	@Length(min=0, max=255, message="问题描述长度必须介于 0 和 255 之间")
	public String getWtms() {
		return wtms;
	}
	public void setWtms(String wtms) {
		this.wtms = wtms;
	}
	
	@Length(min=0, max=255, message="被监督单位意见长度必须介于 0 和 255 之间")
	public String getJcdwYj() {
		return jcdwYj;
	}
	public void setJcdwYj(String jcdwYj) {
		this.jcdwYj = jcdwYj;
	}
	
	@Length(min=0, max=255, message="被检查单位负责人长度必须介于 0 和 255 之间")
	public String getJcdwFzr() {
		return jcdwFzr;
	}
	public void setJcdwFzr(String jcdwFzr) {
		this.jcdwFzr = jcdwFzr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getJcdwDate() {
		return jcdwDate;
	}
	public void setJcdwDate(Date jcdwDate) {
		this.jcdwDate = jcdwDate;
	}
	
	@Length(min=0, max=255, message="整改措施及完成情况长度必须介于 0 和 255 之间")
	public String getZgTb() {
		return zgTb;
	}
	public void setZgTb(String zgTb) {
		this.zgTb = zgTb;
	}
	
	@Length(min=0, max=32, message="整改完成填报人长度必须介于 0 和 32 之间")
	public String getZgTbr() {
		return zgTbr;
	}
	public void setZgTbr(String zgTbr) {
		this.zgTbr = zgTbr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZgTbrq() {
		return zgTbrq;
	}
	public void setZgTbrq(Date zgTbrq) {
		this.zgTbrq = zgTbrq;
	}
	
	@Length(min=0, max=255, message="整改完成验证情况长度必须介于 0 和 255 之间")
	public String getZgYz() {
		return zgYz;
	}
	public void setZgYz(String zgYz) {
		this.zgYz = zgYz;
	}
	
	@Length(min=0, max=32, message="整改完成验证人长度必须介于 0 和 32 之间")
	public String getZgYzr() {
		return zgYzr;
	}
	public void setZgYzr(String zgYzr) {
		this.zgYzr = zgYzr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getZgYzrq() {
		return zgYzrq;
	}
	public void setZgYzrq(Date zgYzrq) {
		this.zgYzrq = zgYzrq;
	}
	
	@Length(min=0, max=50, message="流程状态长度必须介于 0 和 50 之间")
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getJcr() {
		return jcr;
	}

	public void setJcr(String jcr) {
		this.jcr = jcr;
	}

}


