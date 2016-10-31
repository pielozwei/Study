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
 * 设备层次Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBCC")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentField extends IdEntity<EquipmentField> {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentField parent; //上级id 不为空
	@NotNull
	@Length(min=1,max=30)
	private String ccbm;		//设备层次编码
	@NotNull
	@Length(min=1,max=100)
	private String ccmc; 		// 名称
	@NotNull
	private Integer cclx;			// 层次类型
	private Integer cczt;			// 层次状态--暂时放启用禁用
	@Length(min=0,max=100)
	private String lxdh;		// 联系电话
	@Length(min=0,max=30)
	private String zbyddh;		// 值班移动电话
	private Boolean sfqjxdw;    // 是否全局性单位
	private Boolean sfstdw;		// 是否实体单位
	@Length(min=0,max=128)
	private String wz;			// 网址
	@Length(min=0,max=100)
	private String yx;			// 邮箱
	private Float jd;			// 经度
	private Float wd;			// 维度
	@Length(min=0,max=30)
	private String xzzgldmc;	// 行政主管领导名称
	@Length(min=0,max=30)
	private String xzfgldmc; 	// 行政副主管领导名称XZFGLDMC
	@Length(min=0,max=30)
	private String dwzgldmc;    // 党务主管领导名称
	@Length(min=0,max=30)
	private String dwfgldmc;	// 党务副主管领导名称
	@Length(min=0,max=4000)
	private String jj;			// 简介
	private Boolean sfyfz;		//  是否有分址
	
	private Integer sfqy;    //是否启用
	private Integer xssx;    //显示顺序
	private String bz;    //备注
	
	public EquipmentField() {
		super();
	}
	public EquipmentField(String id){
		this();
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SJCCID")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	@ExcelField(title="父级id",value="parent.id", align=1, sort=20)
	public EquipmentField getParent() {
		return parent;
	}
	public void setParent(EquipmentField parent) {
		this.parent = parent;
	}
	public String getCcbm() {
		return ccbm;
	}
	public void setCcbm(String ccbm) {
		this.ccbm = ccbm;
	}
	public String getCcmc() {
		return ccmc;
	}
	public void setCcmc(String ccmc) {
		this.ccmc = ccmc;
	}
	public Integer getCclx() {
		return cclx;
	}
	public void setCclx(Integer cclx) {
		this.cclx = cclx;
	}
	public Integer getCczt() {
		return cczt;
	}
	public void setCczt(Integer cczt) {
		this.cczt = cczt;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getZbyddh() {
		return zbyddh;
	}
	public void setZbyddh(String zbyddh) {
		this.zbyddh = zbyddh;
	}
	public Boolean getSfqjxdw() {
		return sfqjxdw;
	}
	public void setSfqjxdw(Boolean sfqjxdw) {
		this.sfqjxdw = sfqjxdw;
	}
	public Boolean getSfstdw() {
		return sfstdw;
	}
	public void setSfstdw(Boolean sfstdw) {
		this.sfstdw = sfstdw;
	}
	public String getWz() {
		return wz;
	}
	public void setWz(String wz) {
		this.wz = wz;
	}
	public String getYx() {
		return yx;
	}
	public void setYx(String yx) {
		this.yx = yx;
	}
	public Float getJd() {
		return jd;
	}
	public void setJd(Float jd) {
		this.jd = jd;
	}
	public Float getWd() {
		return wd;
	}
	public void setWd(Float wd) {
		this.wd = wd;
	}
	public String getXzzgldmc() {
		return xzzgldmc;
	}
	public void setXzzgldmc(String xzzgldmc) {
		this.xzzgldmc = xzzgldmc;
	}
	public String getXzfgldmc() {
		return xzfgldmc;
	}
	public void setXzfgldmc(String xzfgldmc) {
		this.xzfgldmc = xzfgldmc;
	}
	public String getDwzgldmc() {
		return dwzgldmc;
	}
	public void setDwzgldmc(String dwzgldmc) {
		this.dwzgldmc = dwzgldmc;
	}
	public String getDwfgldmc() {
		return dwfgldmc;
	}
	public void setDwfgldmc(String dwfgldmc) {
		this.dwfgldmc = dwfgldmc;
	}
	public String getJj() {
		return jj;
	}
	public void setJj(String jj) {
		this.jj = jj;
	}
	public Boolean getSfyfz() {
		return sfyfz;
	}
	public void setSfyfz(Boolean sfyfz) {
		this.sfyfz = sfyfz;
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


