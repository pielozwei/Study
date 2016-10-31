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
 * 工作班制Entity
 * @author lucl
 * @version 2016-08-15
 */
@Entity
@Table(name = "IP_GZBZ")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkShift extends IdEntity<WorkShift> {
	
	private static final long serialVersionUID = 1L;
	//private String name; 	// 名称
	
	private String bzmc;    //班制名称
	private Integer sfqy;    //是否启用
	private Integer xssx;    //显示顺序
	private String bz;    //备注

	public WorkShift() {
		super();
	}

	public WorkShift(String id){
		this();
		this.id = id;
	}
	 
	/*@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}

	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
}


