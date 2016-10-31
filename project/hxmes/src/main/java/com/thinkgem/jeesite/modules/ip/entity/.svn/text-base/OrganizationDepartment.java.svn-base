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
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 组织机构部门Entity
 * @author xht
 * @version 2016-09-07
 */
@Entity
@Table(name = "ip_zzjgbm")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganizationDepartment extends IdEntity<OrganizationDepartment> {
	
	private static final long serialVersionUID = 1L;
	//private String zzjg_id; 
	private String sjbmid;
	private String bmbm;
	private String bmmc;
	private String bmjc;
	private int ywlx;
	private int bmlx;
	private int sfqjxjg;
	private int sfstxjg;
	private String yb;
	private String dz;
	private String lxdh;
	private String wz;
	private String yx;
	private String xzzgldmc;
	private String xzfgldmc;
	private String dwzgldmc;
	private String dwfgldmc;
	private int sfqy;
	private int xssx;
	private String bz;

	private Organization organization;
	
	public OrganizationDepartment() {
		super();
	}

	public OrganizationDepartment(String id){
		this();
		this.id = id;
	}

//	public String getZzjg_id() {
//		return zzjg_id;
//	}
//
//	public void setZzjg_id(String zzjg_id) {
//		this.zzjg_id = zzjg_id;
//	}

	
	@ManyToOne
	@JoinColumn(name = "ZZJG_ID")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSjbmid() {
		return sjbmid;
	}

	public void setSjbmid(String sjbmid) {
		this.sjbmid = sjbmid;
	}

	public String getBmbm() {
		return bmbm;
	}

	public void setBmbm(String bmbm) {
		this.bmbm = bmbm;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getBmjc() {
		return bmjc;
	}

	public void setBmjc(String bmjc) {
		this.bmjc = bmjc;
	}

	public int getYwlx() {
		return ywlx;
	}

	public void setYwlx(int ywlx) {
		this.ywlx = ywlx;
	}

	public int getBmlx() {
		return bmlx;
	}

	public void setBmlx(int bmlx) {
		this.bmlx = bmlx;
	}

	public int getSfqjxjg() {
		return sfqjxjg;
	}

	public void setSfqjxjg(int sfqjxjg) {
		this.sfqjxjg = sfqjxjg;
	}

	public int getSfstxjg() {
		return sfstxjg;
	}

	public void setSfstxjg(int sfstxjg) {
		this.sfstxjg = sfstxjg;
	}

	public String getYb() {
		return yb;
	}

	public void setYb(String yb) {
		this.yb = yb;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
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

	public int getSfqy() {
		return sfqy;
	}

	public void setSfqy(int sfqy) {
		this.sfqy = sfqy;
	}

	public int getXssx() {
		return xssx;
	}

	public void setXssx(int xssx) {
		this.xssx = xssx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	 
	
	
	
}


