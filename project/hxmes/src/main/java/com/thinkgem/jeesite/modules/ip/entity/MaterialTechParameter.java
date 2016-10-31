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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 物料技术参数Entity
 * @author ZhangHD
 * @version 2016-06-14
 */
@Entity
@Table(name = "ip_wljscs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MaterialTechParameter extends IdEntity<MaterialTechParameter> {
	private static final long serialVersionUID = 1L;
	private Material Wlbm;// 物料编码_物料编码
	private String csdm;// 参数代码
	private String csmc;// 参数名称
	private String csjc;// 参数简称
	private String cssm;// 参数说明
	private Integer cszlx;// 参数值类型
	private Jldw csjldw;// 参数计量单位
	private Float bzz;// 标准值
	private Float sxz;// 上限值
	private Float xxz;// 下限值
	private String bz;// 备注--
	private String xssx;// 显示顺序--
	private String sfqy;// 是否启用--

	public MaterialTechParameter() {
		super();
	}


	public MaterialTechParameter(String id) {
		this();
		this.id = id;
	}


	@ManyToOne
	@JoinColumn(name = "WL_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public Material getWlbm() {
		return Wlbm;
	}


	public void setWlbm(Material wlbm) {
		Wlbm = wlbm;
	}


	@Column(name = "CSBM")
	public String getCsdm() {
		return csdm;
	}


	public void setCsdm(String csdm) {
		this.csdm = csdm;
	}


	public String getCsmc() {
		return csmc;
	}


	public void setCsmc(String csmc) {
		this.csmc = csmc;
	}


	public String getCsjc() {
		return csjc;
	}


	public void setCsjc(String csjc) {
		this.csjc = csjc;
	}


	public String getCssm() {
		return cssm;
	}


	public void setCssm(String cssm) {
		this.cssm = cssm;
	}


	public Integer getCszlx() {
		return cszlx;
	}


	public void setCszlx(Integer cszlx) {
		this.cszlx = cszlx;
	}

	@ManyToOne
	@JoinColumn(name = "JLDW_ID")
	public Jldw getCsjldw() {
		return csjldw;
	}
	public void setCsjldw(Jldw csjldw) {
		this.csjldw = csjldw;
	}

	public Float getBzz() {
		return bzz;
	}


	public void setBzz(Float bzz) {
		this.bzz = bzz;
	}


	public Float getSxz() {
		return sxz;
	}


	public void setSxz(Float sxz) {
		this.sxz = sxz;
	}


	public Float getXxz() {
		return xxz;
	}


	public void setXxz(Float xxz) {
		this.xxz = xxz;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getXssx() {
		return xssx;
	}


	public void setXssx(String xssx) {
		this.xssx = xssx;
	}


	public String getSfqy() {
		return sfqy;
	}


	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
}
