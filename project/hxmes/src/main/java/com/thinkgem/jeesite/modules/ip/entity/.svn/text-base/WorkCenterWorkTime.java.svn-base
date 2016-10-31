/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 工作中心班次配置Entity
 * @author lucl
 * @version 2016-06-21
 */
@Entity
@Table(name = "IP_GZZXBC")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkCenterWorkTime extends IdEntity<WorkCenterWorkTime> {
	
	private static final long serialVersionUID = 1L;
	//private String id; 		// 编号
	private String name; 	// 名称
//	private WorkCenterWorkTime ipGzzxId; 	// 工作中心_ID    
	private Integer bc; 	// 班次
	private String bm; 	// 班名
	private Integer xz; 	// 性质
	private Integer bs; 	// 班时
	private String bz; 	// 备注
	
	private WorkShift gzbzId;    //工作班制ID
	private Integer ktbs;    //跨天标识
	private Integer sfqy;    //是否启用
	private Integer xssx;    //显示顺序
	
	//开始时间和结束时间格式不对，修改为字符串类型
	private String kssj;    //开始时间
	private String jssj;    //结束时间
	

	public WorkCenterWorkTime() {
		super();
	}

	public WorkCenterWorkTime(String id){
		this();
		this.id = id;
	}
	 
	@Length(min=1, max=200)
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public WorkCenterWorkTime getIpGzzxId() {
		return ipGzzxId;
	}

	public void setIpGzzxId(WorkCenterWorkTime ipGzzxId) {
		this.ipGzzxId = ipGzzxId;
	}*/

	public Integer getBc() {
		return bc;
	}

	public void setBc(Integer bc) {
		this.bc = bc;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public Integer getXz() {
		return xz;
	}

	public void setXz(Integer xz) {
		this.xz = xz;
	}

	public Integer getBs() {
		return bs;
	}

	public void setBs(Integer bs) {
		this.bs = bs;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public WorkShift getGzbzId() {
		return gzbzId;
	}

	public void setGzbzId(WorkShift gzbzId) {
		this.gzbzId = gzbzId;
	}

	public Integer getKtbs() {
		return ktbs;
	}

	public void setKtbs(Integer ktbs) {
		this.ktbs = ktbs;
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


