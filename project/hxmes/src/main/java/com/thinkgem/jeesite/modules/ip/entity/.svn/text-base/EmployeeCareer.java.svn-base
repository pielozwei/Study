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
 * 工作经历Entity
 * @author cml
 * @version 2016-06-21
 */
@Entity
@Table(name = "ip_zggzjl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeCareer extends IdEntity<EmployeeCareer> {
	
	private static final long serialVersionUID = 1L;


	//private String zg_id; 	// 名称
	private Integer xl; 	// 名称
	private Date qsrq; 	// 名称
	private Date zzrq; 	// 名称
	private String szdw; 	// 名称
	private String zw; 	// 名称
	
	private Employee employee;
	
	
	@ManyToOne
	@JoinColumn(name = "IP_ZGGRXX_ID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSzdw() {
		return szdw;
	}

	public void setSzdw(String szdw) {
		this.szdw = szdw;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public Integer getXl() {
		return xl;
	}

	public void setXl(Integer xl) {
		this.xl = xl;
	}

	@Temporal(TemporalType.DATE)
	public Date getQsrq() {
		return qsrq;
	}

	public void setQsrq(Date qsrq) {
		this.qsrq = qsrq;
	}

	@Temporal(TemporalType.DATE)
	public Date getZzrq() {
		return zzrq;
	}

	public void setZzrq(Date zzrq) {
		this.zzrq = zzrq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmployeeCareer() {
		super();
	}

	public EmployeeCareer(String id){
		this();
		this.id = id;
	}
	 


	
}


