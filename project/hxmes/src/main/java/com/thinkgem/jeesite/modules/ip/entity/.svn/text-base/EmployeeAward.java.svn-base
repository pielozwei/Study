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
 * 获奖情况记录Entity
 * @author zzc
 * @version 2016-06-20
 */
@Entity
@Table(name = "ip_zghjqk")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeAward extends IdEntity<EmployeeAward> {
	
	private static final long serialVersionUID = 1L;
	//private String zg_id; 	// 职工个人信息
	private Integer xl; 		// 序列
	private Integer hjjb; 		//获奖级别
	private String hjmc; 		// 获奖名称
	private Date hjrq; 		// 获奖日期
	private String bjjgmc; 		// 获奖机构名称

	private Employee employee;

	public EmployeeAward() {
		super();
	}

	public EmployeeAward(String id){
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
	
	public Integer getXl() {
		return xl;
	}

	public void setXl(Integer xl) {
		this.xl = xl;
	}

	public Integer getHjjb() {
		return hjjb;
	}

	public void setHjjb(Integer hjjb) {
		this.hjjb = hjjb;
	}

	public String getHjmc() {
		return hjmc;
	}

	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}

	@Temporal(TemporalType.DATE)
	public Date getHjrq() {
		return hjrq;
	}

	public void setHjrq(Date hjrq) {
		this.hjrq = hjrq;
	}

	public String getBjjgmc() {
		return bjjgmc;
	}

	public void setBjjgmc(String bjjgmc) {
		this.bjjgmc = bjjgmc;
	}

	 


}


