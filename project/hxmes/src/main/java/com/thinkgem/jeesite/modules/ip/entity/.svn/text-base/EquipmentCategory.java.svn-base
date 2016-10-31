/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;


/**
 * 设备分类Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBLB")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentCategory extends IdEntity<EquipmentCategory> {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentCategory parent; //上级id 不为空
	
	@NotNull
	@Length(min=1,max=30)
	private String sblbbm;//	VARCHAR2(30)	N			设备类别编码
	@NotNull
	@Length(min=1,max=30)
	private String sblbmc;//	VARCHAR2(30)	N			设备类别名称
	//zhd 增加字段
	private String sblbjc;//   设备简称
	private Integer sfqy;//   是否启用
	private Integer xssx;//   显示循序号
	
	public EquipmentCategory() {
		super();
	}
	public EquipmentCategory(String id){
		this();
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SJLBID")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	@ExcelField(title="父级id",value="parent.id", align=1, sort=20)
	public EquipmentCategory getParent() {
		return parent;
	}
	public void setParent(EquipmentCategory parent) {
		this.parent = parent;
	}
	public String getSblbbm() {
		return sblbbm;
	}
	public void setSblbbm(String sblbbm) {
		this.sblbbm = sblbbm;
	}
	public String getSblbmc() {
		return sblbmc;
	}
	public void setSblbmc(String sblbmc) {
		this.sblbmc = sblbmc;
	}
	public String getSblbjc() {
		return sblbjc;
	}
	public void setSblbjc(String sblbjc) {
		this.sblbjc = sblbjc;
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


