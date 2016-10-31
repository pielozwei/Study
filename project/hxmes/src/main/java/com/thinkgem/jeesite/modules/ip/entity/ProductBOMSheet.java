/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.ip.entity.Material;

/**
 * 基本信息Entity
 * @author ZhangHD
 * @version 2016-06-16
 */
@Entity
@Table(name = "ip_cpbomd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductBOMSheet extends IdEntity<ProductBOMSheet> {
	private static final long serialVersionUID = 1L;
	private String cpbomdbh;// 产品BOM单编号
	private Material wlbm;// 物料编码
	private String bomzb;// BOM组别
	private String bb;// 版本
	private String shry;// 审核人员
	private Date shrq;// 审核日期
	private String syry;// 使用人员
	private Date syrq;// 使用日期
	private Integer sfzy;// 是否在用
	private Integer sfqy;// 是否在用


	public ProductBOMSheet() {
		super();
	}


	public ProductBOMSheet(String id) {
		this();
		this.id = id;
	}


	public String getCpbomdbh() {
		return cpbomdbh;
	}


	public void setCpbomdbh(String cpbomdbh) {
		this.cpbomdbh = cpbomdbh;
	}


	@ManyToOne
	@JoinColumn(name = "ZWL_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public Material getWlbm() {
		return wlbm;
	}


	public void setWlbm(Material wlbm) {
		this.wlbm = wlbm;
	}


	public String getBomzb() {
		return bomzb;
	}


	public void setBomzb(String bomzb) {
		this.bomzb = bomzb;
	}


	public String getBb() {
		return bb;
	}


	public void setBb(String bb) {
		this.bb = bb;
	}


	public String getShry() {
		return shry;
	}


	public void setShry(String shry) {
		this.shry = shry;
	}


	public Date getShrq() {
		return shrq;
	}


	public void setShrq(Date shrq) {
		this.shrq = shrq;
	}


	public String getSyry() {
		return syry;
	}


	public void setSyry(String syry) {
		this.syry = syry;
	}


	public Date getSyrq() {
		return syrq;
	}


	public void setSyrq(Date syrq) {
		this.syrq = syrq;
	}


	public Integer getSfzy() {
		return sfzy;
	}


	public void setSfzy(Integer sfzy) {
		this.sfzy = sfzy;
	}


	public Integer getSfqy() {
		return sfqy;
	}


	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}
}
