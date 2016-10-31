/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
 * 关键监控参数Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_sbjkcs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentMonitorParam extends IdEntity<EquipmentMonitorParam> {
	
	private static final long serialVersionUID = 1L;
	
	private Equipment equipment; 	// 名称
	
	@Length(min=1, max=30)
	@NotNull
	@ExcelField(title="参数代码",align=1, sort=25)
	private String csdm; //参数代码 最大长度30  非空 唯一
	
	@Length(min=1, max=30)
	@NotNull
	@ExcelField(title="参数名称",align=1, sort=30)
	private String csmc;//参数名称 最大长度30 非空
	
	@Length(min=0, max=30)
	@ExcelField(title="参数说明", align=1, sort=35)
	private String cssm;//参数说明 最大长度30
	
	@NotNull
	@ExcelField(title="参数值类型",dictType="d_jkcszlx", align=1, sort=40)
	private Integer cszlx;//参数值类型  字典值 非空
	
	@NotNull
	@ExcelField(title="参数计量单位", align=1, sort=45)
	private Jldw csjldw;//参数计量单位 最大长度30 非空
	

	@ExcelField(title="标准值", align=1, sort=50)
	private Float bzz;//标准值  非空
	

	@ExcelField(title="上限值", align=1, sort=55)
	private Float sxz;//上限值 非空
	
	@ExcelField(title="下限值", align=1, sort=60)
	private Float xxz;//下限值 非空
	
	private String csjc;     //参数简称
	private Integer sfqy;    //是否启用
	@NotNull
	private Integer xssx;     //显示顺序
	
	public EquipmentMonitorParam() {
		super();
	}
	public EquipmentMonitorParam(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="IP_SB_SBBM")
	@ManyToOne
	@JoinColumn(name="IP_SB_SBBM")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="equipment.id", align=1, sort=20)
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
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
	@NotFound(action = NotFoundAction.IGNORE)
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
}


