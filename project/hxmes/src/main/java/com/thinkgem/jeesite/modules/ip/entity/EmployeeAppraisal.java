/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 组织考核经历Entity
 * 
 * @author WuWB
 * @version 2016-06-20
 */
@Entity
@Table(name = "ip_zgzzkhjl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeAppraisal extends IdEntity<EmployeeAppraisal> {

	private static final long serialVersionUID = 1L;
	//private String zg_id;
	private Integer xl;
	private Integer khnd;
	private Integer khjllb;
	private Integer cjkhbddcyy;
	private Integer wcjkhyy;

	private Employee employee;
	
//	@Column(name="IP_ZGGRXX_ID")
//	public String getZg_id() {
//		return zg_id;
//	}
//
//	public void setZg_id(String zg_id) {
//		this.zg_id = zg_id;
//	}

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

	public Integer getKhnd() {
		return khnd;
	}

	public void setKhnd(Integer khnd) {
		this.khnd = khnd;
	}

	public Integer getKhjllb() {
		return khjllb;
	}

	public void setKhjllb(Integer khjllb) {
		this.khjllb = khjllb;
	}

	public Integer getCjkhbddcyy() {
		return cjkhbddcyy;
	}

	public void setCjkhbddcyy(Integer cjkhbddcyy) {
		this.cjkhbddcyy = cjkhbddcyy;
	}

	public Integer getWcjkhyy() {
		return wcjkhyy;
	}

	public void setWcjkhyy(Integer wcjkhyy) {
		this.wcjkhyy = wcjkhyy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmployeeAppraisal() {
		super();
	}

	public EmployeeAppraisal(String id) {
		this();
		this.id = id;
	}

}
