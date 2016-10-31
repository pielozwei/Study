/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 工厂日历方案Entity
 * @author lzq
 * @version 2016-08-16
 */
@Entity
@Table(name = "IP_GCRLFA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Calendar extends IdEntity<Calendar> {
	
	private static final long serialVersionUID = 1L;
	private String id; 		// 编号
	private String famc; 	// 方案名称
	private Integer nf;
	private String bz;

	public Calendar() {
		super();
	}

	public Calendar(String id){
		this();
		this.id = id;
	}
	
	public String getFamc() {
		return famc;
	}

	public void setFamc(String famc) {
		this.famc = famc;
	}

	public Integer getNf() {
		return nf;
	}

	public void setNf(Integer nf) {
		this.nf = nf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	 


}


