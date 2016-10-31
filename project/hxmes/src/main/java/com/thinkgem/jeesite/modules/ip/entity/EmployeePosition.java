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
 * 行政党派职务经历Entity
 * 
 * @author yrd
 * @version 2016-06-20
 */
@Entity
@Table(name = "IP_ZGXZDPZWJL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePosition extends IdEntity<EmployeePosition> {

	private static final long serialVersionUID = 1L;
	// private String id; // 编号
	//private String zg_id; // 职工个人信息_ID
	private Integer xl; // 序列
	private Integer dqrzzt; // 当前任职状态
	private String zw;// 职务
	private Date pzrq;// 批准日期
	private String rzwh;// 任职文号
	private Date basj;// 备案时间
	
	private Employee employee;

	public EmployeePosition() {
		super();
	}

	public EmployeePosition(String id) {
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


	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	@Temporal(TemporalType.DATE)
	public Date getPzrq() {
		return pzrq;
	}

	public void setPzrq(Date pzrq) {
		this.pzrq = pzrq;
	}

	public String getRzwh() {
		return rzwh;
	}

	public void setRzwh(String rzwh) {
		this.rzwh = rzwh;
	}

	public Date getBasj() {
		return basj;
	}

	public void setBasj(Date basj) {
		this.basj = basj;
	}

	public Integer getDqrzzt() {
		return dqrzzt;
	}

	public void setDqrzzt(Integer dqrzzt) {
		this.dqrzzt = dqrzzt;
	}


}
