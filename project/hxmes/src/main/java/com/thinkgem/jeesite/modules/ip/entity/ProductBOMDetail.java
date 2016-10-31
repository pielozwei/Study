/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

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
 * BOM明细Entity
 * @author ZhangHD
 * @version 2016-06-16
 */
@Entity
@Table(name = "ip_cpbommx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductBOMDetail extends IdEntity<ProductBOMDetail> {
	private static final long serialVersionUID = 1L;
	private ProductBOMSheet productBOMSheet;// 产品BOM单_产品BOM单编号
	private Material zwlbm;// 子物料编码
	private Jldw Jldw;// 单位
	private String bz;// 备注
	private Integer sl;// 数量
	private Integer sfmj;// 是否末级


	public ProductBOMDetail() {
		super();
	}


	public ProductBOMDetail(String id) {
		this();
		this.id = id;
	}


	@ManyToOne
	@JoinColumn(name = "WLBOMD_ID")
	public ProductBOMSheet getProductBOMSheet() {
		return productBOMSheet;
	}


	public void setProductBOMSheet(ProductBOMSheet productBOMSheet) {
		this.productBOMSheet = productBOMSheet;
	}


	@ManyToOne
	@JoinColumn(name = "ZWL_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public Material getZwlbm() {
		return zwlbm;
	}


	@ManyToOne
	@JoinColumn(name = "JLDW_ID")
	public Jldw getJldw() {
		return Jldw;
	}


	public void setJldw(Jldw jldw) {
		Jldw = jldw;
	}




	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public Integer getSl() {
		return sl;
	}


	public void setSl(Integer sl) {
		this.sl = sl;
	}


	public void setZwlbm(Material zwlbm) {
		this.zwlbm = zwlbm;
	}


	public Integer getSfmj() {
		return sfmj;
	}


	public void setSfmj(Integer sfmj) {
		this.sfmj = sfmj;
	}
}
