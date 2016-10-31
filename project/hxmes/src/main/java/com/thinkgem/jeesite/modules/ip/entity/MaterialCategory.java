/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 物料编码信息Entity
 * @author ZhangHD
 * @version 2016-06-03
 */
@Entity
@Table(name = "ip_wllb")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MaterialCategory extends IdEntity<MaterialCategory> {
	// MaterialCategory
	private static final long serialVersionUID = 1L;
	// private String id; // 编号
	private String wllbbm;// 物料类别编码
	private String wllbmc;// 物料类别名称
	private MaterialCategory parent;// 上级类别编码
	private Integer ccm;// 层次
	private String bz;// 备注
	private Integer sfqy;
	private Integer xssx;


	public MaterialCategory() {
		super();
	}


	@Transient
	public boolean isRoot() {
		return isRoot(this.id);
	}


	@Transient
	public static boolean isRoot(String id) {
		return id != null && id.equals("1");
	}


	public MaterialCategory(String id) {
		this();
		this.id = id;
	}



	public String getWllbbm() {
		return wllbbm;
	}


	public void setWllbbm(String wllbbm) {
		this.wllbbm = wllbbm;
	}


	public String getWllbmc() {
		return wllbmc;
	}


	public void setWllbmc(String wllbmc) {
		this.wllbmc = wllbmc;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SJLBID")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public MaterialCategory getParent() {
		return parent;
	}


	public void setParent(MaterialCategory parent) {
		this.parent = parent;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public Integer getCcm() {
		return ccm;
	}


	public void setCcm(Integer ccm) {
		this.ccm = ccm;
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
}
