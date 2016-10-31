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
 * 政治面貌经历Entity
 * @author ks
 * @version 2016-06-21
 */
@Entity
@Table(name = "ip_zgzzmm")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePoliticalStatus extends IdEntity<EmployeePoliticalStatus> {
	
	private static final long serialVersionUID = 1L;
	
	//private String zg_id; 	// 名称
	private Integer xl;
	private Integer zzmm;
	private Date jrsj;

	private Employee employee;

	public EmployeePoliticalStatus() {
		super();
	}
	
	public EmployeePoliticalStatus(String id){
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

	public Integer getZzmm() {
		return zzmm;
	}

	public void setZzmm(Integer zzmm) {
		this.zzmm = zzmm;
	}

	@Temporal(TemporalType.DATE)
	public Date getJrsj() {
		return jrsj;
	}

	public void setJrsj(Date jrsj) {
		this.jrsj = jrsj;
	}

}


