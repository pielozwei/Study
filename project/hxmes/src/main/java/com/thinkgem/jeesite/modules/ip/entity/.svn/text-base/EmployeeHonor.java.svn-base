/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 荣誉称号记录Entity
 * 
 * @author lzq
 * @version 2016-06-20
 */
@Entity
@Table(name = "IP_ZGRYCH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeeHonor extends IdEntity<EmployeeHonor> {

	private static final long serialVersionUID = 1L;

	//private String ip_zggrxx_id;
	private Integer xl; // 序列
	private String jb; // 级别
	private String mc;// 名称
	private Date pzrq;// 批准日期
	private String pzjgmc;// 批准机构名称
	private Integer ycxjlje;// 一次性奖励金额
	private Integer yjlje;// 月奖励金额
	
	private Employee employee;
//	private Integer xssx; // 显示顺序	
//	private String bz; // 备注
//	public Integer getXssx() {
//		return xssx;
//	}
//
//
//	public void setXssx(Integer xssx) {
//		this.xssx = xssx;
//	}
//
//	public String getBz() {
//		return bz;
//	}
//
//
//	public void setBz(String bz) {
//		this.bz = bz;
//	}

	public EmployeeHonor() {
		super();
	}


	@ManyToOne
	@JoinColumn(name = "IP_ZGGRXX_ID")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



//	public String getIp_zggrxx_id() {
//		return ip_zggrxx_id;
//	}
//
//
//	public void setIp_zggrxx_id(String ip_zggrxx_id) {
//		this.ip_zggrxx_id = ip_zggrxx_id;
//	}


	public EmployeeHonor(String id) {
		this();
		this.id = id;
	}


	public Integer getXl() {
		return xl;
	}

	public void setXl(Integer xl) {
		this.xl = xl;
	}

	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Date getPzrq() {
		return pzrq;
	}

	@Temporal(TemporalType.DATE)
	public void setPzrq(Date pzrq) {
		this.pzrq = pzrq;
	}

	public String getPzjgmc() {
		return pzjgmc;
	}

	public void setPzjgmc(String pzjgmc) {
		this.pzjgmc = pzjgmc;
	}

	public Integer getYcxjlje() {
		return ycxjlje;
	}

	public void setYcxjlje(Integer ycxjlje) {
		this.ycxjlje = ycxjlje;
	}

	public Integer getYjlje() {
		return yjlje;
	}

	public void setYjlje(Integer yjlje) {
		this.yjlje = yjlje;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}