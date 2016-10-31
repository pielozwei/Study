/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 工艺路线基本信息Entity
 * @author zzc
 * @version 2016-06-27
 */
@Entity
@Table(name = "ip_gylx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Technique extends IdEntity<Technique> {
	
	private static final long serialVersionUID = 1L;
	// private String id; 		// ID
	

	private String sjgylxid;	//上级工艺路线ID
	private String bm; 	// 编码
	private String mc; //名称
	private String jc; //简称
	private Integer gylx; //工艺类型
	private Integer sfzgy; //是否主工艺		
	private Integer jdlx; //节点类型
	private Integer gysylx; //工艺使用类型
	private Integer sftsgx; //是否特殊工艺
	private Integer sfzlkzd; //是否质量控制点
	private Integer yhkzdlx; //用户控制点类型
	private Integer cnkzdlx; //厂内控制点类型
	private Integer sfqy; //是否启用
	private Integer xssx;	//显示顺序
	private String bz; 		// 备注
	
	private Material material;

	@ManyToOne
	@JoinColumn(name="IP_WLBM_ID")
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Technique() {
		super();
	}

	public Technique(String id){
		this();
		this.id = id;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	
//	public String getZcpwl_id() {
//		return zcpwl_id;
//	}
//
//	public void setZcpwl_id(String zcpwl_id) {
//		this.zcpwl_id = zcpwl_id;
//	}

	public String getSjgylxid() {
		return sjgylxid;
	}

	public void setSjgylxid(String sjgylxid) {
		this.sjgylxid = sjgylxid;
	}

	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public Integer getGylx() {
		return gylx;
	}

	public void setGylx(Integer gylx) {
		this.gylx = gylx;
	}

	@Column(name="syzt")
	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}

	public Integer getSfzgy() {
		return sfzgy;
	}

	public void setSfzgy(Integer sfzgy) {
		this.sfzgy = sfzgy;
	}

	public Integer getJdlx() {
		return jdlx;
	}

	public void setJdlx(Integer jdlx) {
		this.jdlx = jdlx;
	}

	public Integer getGysylx() {
		return gysylx;
	}

	public void setGysylx(Integer gysylx) {
		this.gysylx = gysylx;
	}

	public Integer getSftsgx() {
		return sftsgx;
	}

	public void setSftsgx(Integer sftsgx) {
		this.sftsgx = sftsgx;
	}

	public Integer getSfzlkzd() {
		return sfzlkzd;
	}

	public void setSfzlkzd(Integer sfzlkzd) {
		this.sfzlkzd = sfzlkzd;
	}

	public Integer getYhkzdlx() {
		return yhkzdlx;
	}

	public void setYhkzdlx(Integer yhkzdlx) {
		this.yhkzdlx = yhkzdlx;
	}

	public Integer getCnkzdlx() {
		return cnkzdlx;
	}

	public void setCnkzdlx(Integer cnkzdlx) {
		this.cnkzdlx = cnkzdlx;
	}

	
}


