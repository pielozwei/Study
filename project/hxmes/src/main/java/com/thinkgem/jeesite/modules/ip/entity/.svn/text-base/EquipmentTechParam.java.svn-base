/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 技术参数Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBJSCS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentTechParam extends IdEntity<EquipmentTechParam> {
	private static final long serialVersionUID = 1L;
	private EquipmentModel equipmentModel; // 名称
	@Length(min = 1, max = 30)
	@NotNull
	@ExcelField(title = "参数代码", fieldType = String.class, align = 1, sort = 25)
	private String csbm; // 30 0 0 0 参数代码
	@Length(min = 1, max = 30)
	@NotNull
	@ExcelField(title = "参数名称", fieldType = String.class, align = 1, sort = 30)
	private String csmc; // 30 0 0 0 参数名称
	@Length(min = 0, max = 30)
	@ExcelField(title = "参数说明", fieldType = String.class, align = 1, sort = 35)
	private String cssm; // 30 0 -1 0 参数说明
	@ExcelField(title = "参数值类型", dictType = "d_jscszlx", fieldType = String.class, align = 1, sort = 40)
	private Integer cszlx; // 0 0 -1 0 参数值类型
	@ExcelField(title = "参数计量单位", fieldType = String.class, align = 1, sort = 45)
	private Jldw csjldw; // 30 0 -1 0 参数计量单位
	@ExcelField(title = "标准值", fieldType = Float.class, align = 1, sort = 50)
	private Float bzz; // 126 0 -1 0 标准值
	@ExcelField(title = "上限值", fieldType = Float.class, align = 1, sort = 55)
	private Float sxz; // 126 0 -1 0 上限值
	@ExcelField(title = "下限值", fieldType = Float.class, align = 1, sort = 60)
	private Float xxz; // 126 0 -1 0 下限值
	// zhd增加字段
	private String csjc;// 参数简称
	private Integer sfqy;// 是否启用
	private Integer xssx;// 显示顺序
	private String bz;// 备注


	public EquipmentTechParam() {
		super();
	}


	public EquipmentTechParam(String id) {
		this();
		this.id = id;
	}


	@IndexColumn(name = "SBGGXH_ID")
	@ManyToOne
	@JoinColumn(name = "SBGGXH_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title = "设备规格型号编码", value = "equipmentModel.id", align = 1, sort = 20)
	public EquipmentModel getEquipmentModel() {
		return equipmentModel;
	}


	public void setEquipmentModel(EquipmentModel equipmentModel) {
		this.equipmentModel = equipmentModel;
	}


	public String getCsmc() {
		return csmc;
	}


	public void setCsmc(String csmc) {
		this.csmc = csmc;
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


	public String getCsbm() {
		return csbm;
	}


	public void setCsbm(String csbm) {
		this.csbm = csbm;
	}


	public String getCsjc() {
		return csjc;
	}


	public void setCsjc(String csjc) {
		this.csjc = csjc;
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
