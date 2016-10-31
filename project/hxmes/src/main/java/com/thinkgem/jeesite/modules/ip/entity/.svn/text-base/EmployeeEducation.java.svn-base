/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 学习经历Entity
 * @author Generate Tools
 * @version 2016-06-16
 */
@Entity
@Table(name = "ip_zgxxjl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeEducation extends IdEntity<EmployeeEducation> {
	
	private static final long serialVersionUID = 1L;
//	private String id; 		// 编号
	
	private String zg_id,sxzylb,xz,xxdwmc,zymc,xw; 	
	private Integer xlxzdm,xldm,shzt;
	private Date rxsj,byyyrq,xwsyrq;

	private Employee employee;

	public EmployeeEducation() {
		super();
	}

	public EmployeeEducation(String id){
		this();
		this.id = id;
	}
	

	@ManyToOne
	@JoinColumn(name = "IP_ZGGRXX_ID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSxzylb() {
		return sxzylb;
	}

	public void setSxzylb(String sxzylb) {
		this.sxzylb = sxzylb;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getXxdwmc() {
		return xxdwmc;
	}

	public void setXxdwmc(String xxdwmc) {
		this.xxdwmc = xxdwmc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getXw() {
		return xw;
	}

	public void setXw(String xw) {
		this.xw = xw;
	}

	public Integer getXlxzdm() {
		return xlxzdm;
	}

	public void setXlxzdm(Integer xlxzdm) {
		this.xlxzdm = xlxzdm;
	}

	public Integer getXldm() {
		return xldm;
	}

	public void setXldm(Integer xldm) {
		this.xldm = xldm;
	}

	public Integer getShzt() {
		return shzt;
	}

	public void setShzt(Integer shzt) {
		this.shzt = shzt;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRxsj() {
		return rxsj;
	}

	public void setRxsj(Date rxsj) {
		this.rxsj = rxsj;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getByyyrq() {
		return byyyrq;
	}

	public void setByyyrq(Date byyyrq) {
		this.byyyrq = byyyrq;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getXwsyrq() {
		return xwsyrq;
	}

	public void setXwsyrq(Date xwsyrq) {
		this.xwsyrq = xwsyrq;
	}


	
	 


	
}


