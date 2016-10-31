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

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 科研项目记录Entity
 * @author ls
 * @version 2016-06-20
 */
@Entity
@Table(name = "ip_zgkyxm")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeProject extends IdEntity<EmployeeProject> {
	
	private static final long serialVersionUID = 1L;
	// private String id; 		// ID
	private String zg_id; 	// 职工个人信息-ID
	private Integer xl;		//序列 
	private String kyxmmc;		//科研项目名称
	private Integer kyxmjb;		//科研项目级别
	private Date kssj;		//开始时间
	private Date jssj;		//结束时间
	private String jbdw;		//举办单位
	private String cgmc;		//成果名称
	private Integer wcxs;		//完成形式

	private Employee employee;

	public EmployeeProject() {
		super();
	}

	public EmployeeProject(String id){
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

	public String getKyxmmc() {
		return kyxmmc;
	}

	public void setKyxmmc(String kyxmmc) {
		this.kyxmmc = kyxmmc;
	}

	public Integer getKyxmjb() {
		return kyxmjb;
	}

	public void setKyxmjb(Integer kyxmjb) {
		this.kyxmjb = kyxmjb;
	}

	@Temporal(TemporalType.DATE)
	public Date getKssj() {
		return kssj;
	}

	public void setKssj(Date kssj) {
		this.kssj = kssj;
	}

	@Temporal(TemporalType.DATE)
	public Date getJssj() {
		return jssj;
	}

	public void setJssj(Date jssj) {
		this.jssj = jssj;
	}

	public String getJbdw() {
		return jbdw;
	}

	public void setJbdw(String jbdw) {
		this.jbdw = jbdw;
	}

	public String getCgmc() {
		return cgmc;
	}

	public void setCgmc(String cgmc) {
		this.cgmc = cgmc;
	}

	public Integer getWcxs() {
		return wcxs;
	}

	public void setWcxs(Integer wcxs) {
		this.wcxs = wcxs;
	}
}


