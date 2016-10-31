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
 * 缺陷树Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBQXS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentDefect extends IdEntity<EquipmentDefect> {
	private static final long serialVersionUID = 1L;
	private EquipmentModel equipmentModel; // 名称
	@NotNull
	@Length(min = 1, max = 30)
	@ExcelField(title = "缺陷编码", align = 1, sort = 25)
	private String qxbm;// 缺陷编码 VARCHAR2 30 0 true
	@ExcelField(title = "缺陷类别", dictType = "d_qxlb", align = 1, sort = 30)
	private Integer qxlb;// 缺陷类别-d_qxlb NUMBER 0 0 False
	@ExcelField(title = "缺陷级别ABC", dictType = "d_abc", align = 1, sort = 35)
	private Integer qxjbabc;// 缺陷级别ABC-d_abc NUMBER 0 0 False
	@Length(min = 0, max = 30)
	@ExcelField(title = "现象描述关键词", align = 1, sort = 40)
	private String xxmsgjc;// 现象描述关键词 VARCHAR2 30 0 False
	@Length(min = 0, max = 3000)
	@ExcelField(title = "缺陷音视频文件名", align = 1, sort = 45)
	private String qxyspwjm;// 缺陷音视频文件名 VARCHAR2 100 0 False
	@Length(min = 0, max = 3000)
	@ExcelField(title = "缺陷排除指南文件名", align = 1, sort = 50)
	private String qxpcznwjm;// 缺陷排除指南文件名 VARCHAR2 30 0 False
	@Length(min = 0, max = 3000)
	@ExcelField(title = "缺陷排除音视频文件名", align = 1, sort = 55)
	private String qxpcyspwjm;// 缺陷排除音视频文件名VARCHAR2 30 0 False
	// zhd增加
	private String qxxx;// 缺陷现象
	private Integer sfqy;
	private Integer xssx;
	private String bz;


	public EquipmentDefect() {
		super();
	}


	public EquipmentDefect(String id) {
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


	public String getQxbm() {
		return qxbm;
	}


	public void setQxbm(String qxbm) {
		this.qxbm = qxbm;
	}





	public Integer getQxlb() {
		return qxlb;
	}


	public void setQxlb(Integer qxlb) {
		this.qxlb = qxlb;
	}


	public Integer getQxjbabc() {
		return qxjbabc;
	}


	public void setQxjbabc(Integer qxjbabc) {
		this.qxjbabc = qxjbabc;
	}


	public String getXxmsgjc() {
		return xxmsgjc;
	}


	public void setXxmsgjc(String xxmsgjc) {
		this.xxmsgjc = xxmsgjc;
	}


	public String getQxyspwjm() {
		return qxyspwjm;
	}


	public void setQxyspwjm(String qxyspwjm) {
		this.qxyspwjm = qxyspwjm;
	}


	public String getQxpcznwjm() {
		return qxpcznwjm;
	}


	public void setQxpcznwjm(String qxpcznwjm) {
		this.qxpcznwjm = qxpcznwjm;
	}


	public String getQxpcyspwjm() {
		return qxpcyspwjm;
	}


	public void setQxpcyspwjm(String qxpcyspwjm) {
		this.qxpcyspwjm = qxpcyspwjm;
	}


	public String getQxxx() {
		return qxxx;
	}


	public void setQxxx(String qxxx) {
		this.qxxx = qxxx;
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
