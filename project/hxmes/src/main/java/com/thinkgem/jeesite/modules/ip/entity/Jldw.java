/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 计量单位Entity
 * @author lucl
 * @version 2016-08-10
 */
@Entity
@Table(name = "IP_JLDW")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Jldw extends IdEntity<Jldw> {
	private String name;
	private String jldwbm;   //计量单位编码
	private String jldwmc;    //计量单位名称
	private String bz;    //备注
	private Integer xssx;   //显示顺序
	private Integer sfqy;    //是否 启用
	@Transient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJldwbm() {
		return jldwbm;
	}
	public void setJldwbm(String jldwbm) {
		this.jldwbm = jldwbm;
	}
	public String getJldwmc() {
		return jldwmc;
	}
	public void setJldwmc(String jldwmc) {
		this.jldwmc = jldwmc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public Integer getXssx() {
		return xssx;
	}
	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}
	public Integer getSfqy() {
		return sfqy;
	}
	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}
		
	
}


