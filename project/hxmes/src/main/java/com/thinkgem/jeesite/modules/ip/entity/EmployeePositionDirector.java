/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 人员岗位设置Entity
 * 
 * @author yrd
 * @version 2016-09-01
 */
@Entity
@Table(name = "IP_RYGW")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePositionDirector extends
		IdEntity<EmployeePositionDirector> {

	private static final long serialVersionUID = 1L;
//	private String id; // 编号
//	private String gw_id; // 岗位_ID
//	private String zg_id; // 职工_ID

	private Employee employee; //对应职工ID
	private OrganizationPosition organizationposition; //对应岗位ID
	
	@OneToOne
	@JoinColumn(name="GW_ID")
	public OrganizationPosition getOrganizationposition() {
		return organizationposition;
	}

	public void setOrganizationposition(OrganizationPosition organizationposition) {
		this.organizationposition = organizationposition;
	}
	
	@OneToOne
	@JoinColumn(name="ZG_ID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

//	
//
//	
//	public String getGw_id() {
//		return gw_id;
//	}
//
//	public void setGw_id(String gw_id) {
//		this.gw_id = gw_id;
//	}
//	
//	
//	public String getZg_id() {
//		return zg_id;
//	}
//
//	public void setZg_id(String zg_id) {
//		this.zg_id = zg_id;
//	}

	public EmployeePositionDirector() {
		super();
	}

	public EmployeePositionDirector(String id) {
		this();
		this.id = id;
	}
}
