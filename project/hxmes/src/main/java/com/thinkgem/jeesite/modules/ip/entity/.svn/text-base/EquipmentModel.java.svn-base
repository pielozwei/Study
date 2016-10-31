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
 * 基本信息Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBGGXH")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentModel extends IdEntity<EquipmentModel> {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentCategory equipmentCategory; 	// 设备类别_ID
	@NotNull
	@Length(min=1,max=100)
	private String sbggxhbm;//	VARCHAR2(100)	N			设备规格型号编码
	@Length(min=0,max=30)
	private String jssp;//	VARCHAR2(30)	Y			技术水平
	@Length(min=0,max=30)
	private String sbgg;//	VARCHAR2(30)	Y			设备规格
	@Length(min=0,max=30)
	private String sbxh;//	VARCHAR2(30)	Y			设备型号
	@Length(min=0,max=30)
	private String sbggxhmc;//	VARCHAR2(30)	Y			设备规格型号名称
	@Length(min=0,max=30)
	private String sbggxhjc;//	VARCHAR2(30)	Y			设备规格型号简称
	private Boolean sftzsb;//	INTEGER	Y			是否特种设备
	private Integer tzsblb;//	INTEGER	Y			特种设备类别
	@Length(min=0,max=30)
	private String wxzcc;//	VARCHAR2(30)	Y			外形状尺寸
	@Length(min=0,max=30)
	private String zl;//	VARCHAR2(30)	Y			重量
	@Length(min=0,max=4000)
	private String jsxnms;//	CLOB	Y			技术性能描述
	private Integer gl;//	INTEGER	Y			功率
	//zhd增加字段
	private String sfqy;//是否启用
	private Integer xssx;//显示顺序
	private String bz;//显示顺序
	
	
	public EquipmentModel() {
		super();
	}
	public EquipmentModel(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="SBLB_ID")
	@ManyToOne
	@JoinColumn(name="SBLB_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="equipmentCategory.id", align=1, sort=20)
	public EquipmentCategory getEquipmentCategory() {
		return equipmentCategory;
	}
	public void setEquipmentCategory(EquipmentCategory equipmentCategory) {
		this.equipmentCategory = equipmentCategory;
	}
	public String getSbggxhbm() {
		return sbggxhbm;
	}
	public void setSbggxhbm(String sbggxhbm) {
		this.sbggxhbm = sbggxhbm;
	}
	public String getJssp() {
		return jssp;
	}
	public void setJssp(String jssp) {
		this.jssp = jssp;
	}
	public String getSbgg() {
		return sbgg;
	}
	public void setSbgg(String sbgg) {
		this.sbgg = sbgg;
	}
	public String getSbxh() {
		return sbxh;
	}
	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}
	public String getSbggxhmc() {
		return sbggxhmc;
	}
	public void setSbggxhmc(String sbggxhmc) {
		this.sbggxhmc = sbggxhmc;
	}
	public String getSbggxhjc() {
		return sbggxhjc;
	}
	public void setSbggxhjc(String sbggxhjc) {
		this.sbggxhjc = sbggxhjc;
	}
	public Boolean getSftzsb() {
		return sftzsb;
	}
	public void setSftzsb(Boolean sftzsb) {
		this.sftzsb = sftzsb;
	}
	public Integer getTzsblb() {
		return tzsblb;
	}
	public void setTzsblb(Integer tzsblb) {
		this.tzsblb = tzsblb;
	}
	public String getWxzcc() {
		return wxzcc;
	}
	public void setWxzcc(String wxzcc) {
		this.wxzcc = wxzcc;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public String getJsxnms() {
		return jsxnms;
	}
	public void setJsxnms(String jsxnms) {
		this.jsxnms = jsxnms;
	}
	public Integer getGl() {
		return gl;
	}
	public void setGl(Integer gl) {
		this.gl = gl;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
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


