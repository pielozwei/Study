/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 仓库Entity
 * @author Lucl
 * @version 2016-06-13
 */
@Entity
@Table(name = "ip_ck",uniqueConstraints = {@UniqueConstraint(columnNames="ckbh")})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Store extends IdEntity<Store> {
	
	private static final long serialVersionUID = 1L;
	
	//private String id; 		// 编号
	
	private String name; 	// 名称
	private String ckbh;    // 仓库编号
	private String mc;    // 名称
	private String wllbjc;    // 简称
	private Integer ckzt;    // 仓库状态
	private String lxdh;    // 联系电话
	private String zbyddh;    // 值班移动电话
	private Integer sfqjxdw;    // 是否全局性单位
	private Integer sfstdw;    // 是否实体单位
	private String lxr1;    // 联系人1
	private String lxdh1;    // 联系电话1
	private String lxr2;     // 联系人2
	private String lxdh2;    // 联系电话2
	private String lxr3;    // 联系人3
	private String lxdh3;    // 联系电话3
	private Integer zdccts;    // 最大存储天数
	private Integer ckxz;    // 仓库性质
	private Integer ckjz;    // 仓库建筑
	private Integer zdhcd;    // 自动化程度
	private String mj;    // 面积
	private String tj;    // 体积
	private Integer cklx;    // 仓库类型
	private String wz;    // 网址
	private String yx;    // 邮箱    
	private String dz;    // 地址
	private String yzbm;    // 邮政编码
	private Float jd;    // 经度
	private Float wd;    // 维度
	private String xzzgldmc;    // 行政主管领导名称
	private String xzfgldmc;    // 行政副管领导名称
	private String dwzgldmc;    // 党务主管领导名称
	private String dwfgldmc;    // 党务副管领导名称
	private String jj;    // 简介
	private Integer sfyfz;    // 是否有分址
	private Integer ckzn;    // 仓库职能
	
	private Store sjjd;    //上级节点
	private Integer sfqy;    //是否启用
	private Integer xssx;    //显示顺序
	
	//条件
	private String bh;  //仓库编号条件
	private Integer zn;  //仓库职能条件
	private Integer ccxzfl;  //存储性质分类条件
	private Integer jz;    //仓库建筑条件

	public Store() {
		super();
	}

	public Store(String id){
		this();
		this.id = id;
	}
	
	@Transient
	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	@Transient
	public static boolean isRoot(String id){
		return id != null && id.equals("0");
	}
	
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Length(min=1, max=200)
	public String getCkbh() {
		return ckbh;
	}

	public void setCkbh(String ckbh) {
		this.ckbh = ckbh;
	}
	@NotNull
	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}
	@NotNull
	public String getWllbjc() {
		return wllbjc;
	}

	public void setWllbjc(String wllbjc) {
		this.wllbjc = wllbjc;
	}

	public Integer getCkzt() {
		return ckzt;
	}

	public void setCkzt(Integer ckzt) {
		this.ckzt = ckzt;
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
	@NotNull
	public Integer getSfqjxdw() {
		return sfqjxdw;
	}

	public void setSfqjxdw(Integer sfqjxdw) {
		this.sfqjxdw = sfqjxdw;
	}
	@NotNull
	public Integer getSfstdw() {
		return sfstdw;
	}

	public void setSfstdw(Integer sfstdw) {
		this.sfstdw = sfstdw;
	}

	public String getLxr1() {
		return lxr1;
	}

	public void setLxr1(String lxr1) {
		this.lxr1 = lxr1;
	}

	public String getLxdh1() {
		return lxdh1;
	}

	public void setLxdh1(String lxdh1) {
		this.lxdh1 = lxdh1;
	}

	public String getLxr2() {
		return lxr2;
	}

	public void setLxr2(String lxr2) {
		this.lxr2 = lxr2;
	}

	public String getLxdh2() {
		return lxdh2;
	}

	public void setLxdh2(String lxdh2) {
		this.lxdh2 = lxdh2;
	}

	public String getLxr3() {
		return lxr3;
	}

	public void setLxr3(String lxr3) {
		this.lxr3 = lxr3;
	}

	public String getLxdh3() {
		return lxdh3;
	}

	public void setLxdh3(String lxdh3) {
		this.lxdh3 = lxdh3;
	}

	public Integer getZdccts() {
		return zdccts;
	}

	public void setZdccts(Integer zdccts) {
		this.zdccts = zdccts;
	}

	public Integer getCkxz() {
		return ckxz;
	}

	public void setCkxz(Integer ckxz) {
		this.ckxz = ckxz;
	}

	public Integer getCkjz() {
		return ckjz;
	}

	public void setCkjz(Integer ckjz) {
		this.ckjz = ckjz;
	}

	public Integer getZdhcd() {
		return zdhcd;
	}

	public void setZdhcd(Integer zdhcd) {
		this.zdhcd = zdhcd;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getTj() {
		return tj;
	}

	public void setTj(String tj) {
		this.tj = tj;
	}

	public Integer getCklx() {
		return cklx;
	}

	public void setCklx(Integer cklx) {
		this.cklx = cklx;
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

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
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

	public Integer getSfyfz() {
		return sfyfz;
	}

	public void setSfyfz(Integer sfyfz) {
		this.sfyfz = sfyfz;
	}

	public Integer getCkzn() {
		return ckzn;
	}

	public void setCkzn(Integer ckzn) {
		this.ckzn = ckzn;
	}
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	/*@NotNull*/
	public Store getSjjd() {
		return sjjd;
	}
	
	public void setSjjd(Store sjjd) {
		this.sjjd = sjjd;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Transient
	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}
	@Transient
	public Integer getZn() {
		return zn;
	}

	public void setZn(Integer zn) {
		this.zn = zn;
	}
	@Transient
	public Integer getCcxzfl() {
		return ccxzfl;
	}

	public void setCcxzfl(Integer ccxzfl) {
		this.ccxzfl = ccxzfl;
	}
	@Transient
	public Integer getJz() {
		return jz;
	}

	public void setJz(Integer jz) {
		this.jz = jz;
	}
	
	
	
}


