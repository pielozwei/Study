/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

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
 * 工作中心产能配置Entity
 * @author lucl
 * @version 2016-06-22
 */
@Entity
@Table(name = "IP_GZZXCN")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkCenterCapacity extends IdEntity<WorkCenterCapacity> {
	
	private static final long serialVersionUID = 1L;
	//private String id; 		// 编号
	private String name; 	// 名称
	private WorkCenter ipGzzxId;    //工作中心_ID
	private String bzcpId;    //标准产品ID
	private Integer bzscsjdw;    //标准生产时间单位
	private Integer bzscsj;    //标准生产时间
	private Integer sjnscl;    //设计年生产量
	private Integer sdnscl;    //审定年生产量
	private Integer sjnscl2;    //实际年生产量
	private String nsclbfb;    //年生产量百分比
	private Float scxlyz;    //生产效率因子
	private Integer jhsjdw;    //计划时间单位
	private Integer jhsjzq;    //计划时间周期
	private Integer zxsq;    //执行时区
	private Integer xqsq;    //需求时区
	private Integer ghsq;    //规划时区
	private Integer ycsq;    //预测时区
	private Integer sctqq;    //生产提起期
	private Float cbpsjnscl;    //厂标品设计年生产量
	private Float cbpsjnscl2;    //厂标品实际年生产量
	private Float cbpsdnscl;    //厂标品审定年生产量
	private String cbpclbfb;    //厂标品产量百分比
	private Float cbpsjjscl;    //厂标品设计季生产量
	private Float cbpsdjscl;    //厂标品审定季生产量
	private Float cbpsjjscl2;    //厂标品实际季生产量
	private Float cbpsjyscl;    //厂标品设计月生产量
	private Float cbpsdyscl;    //厂标品审定月生产量
	private Float cbpsjyscl2;    //厂标品实际月生产量
	private Float cbpsjzscl;    //厂标品设计周生产量
	private Float cbpsjzscl2;    //厂标品实际周生产量
	private Float cbpsdzscl;    //厂标品审定周生产量
	private Float cbpsjrscl;    //厂标品设计日生产量
	private Float cbpsdrscl;    //厂标品审定日生产量
	private Float cbpsjrscl2;    //厂标品实际日生产量
	
	public WorkCenterCapacity() {
		super();
	}

	public WorkCenterCapacity(String id){
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
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public WorkCenter getIpGzzxId() {
		return ipGzzxId;
	}

	public void setipGzzxId(WorkCenter ipGzzxId) {
		this.ipGzzxId = ipGzzxId;
	}

	public String getBzcpId() {
		return bzcpId;
	}

	public void setBzcpId(String bzcpId) {
		this.bzcpId = bzcpId;
	}

	public Integer getBzscsjdw() {
		return bzscsjdw;
	}

	public void setBzscsjdw(Integer bzscsjdw) {
		this.bzscsjdw = bzscsjdw;
	}

	public Integer getBzscsj() {
		return bzscsj;
	}

	public void setBzscsj(Integer bzscsj) {
		this.bzscsj = bzscsj;
	}

	public Integer getSjnscl() {
		return sjnscl;
	}

	public void setSjnscl(Integer sjnscl) {
		this.sjnscl = sjnscl;
	}

	public Integer getSdnscl() {
		return sdnscl;
	}

	public void setSdnscl(Integer sdnscl) {
		this.sdnscl = sdnscl;
	}

	public Integer getSjnscl2() {
		return sjnscl2;
	}

	public void setSjnscl2(Integer sjnscl2) {
		this.sjnscl2 = sjnscl2;
	}

	public String getNsclbfb() {
		return nsclbfb;
	}

	public void setNsclbfb(String nsclbfb) {
		this.nsclbfb = nsclbfb;
	}
    
	public Float getScxlyz() {
		return scxlyz;
	}

	public void setScxlyz(Float scxlyz) {
		this.scxlyz = scxlyz;
	}

	public Integer getJhsjdw() {
		return jhsjdw;
	}

	public void setJhsjdw(Integer jhsjdw) {
		this.jhsjdw = jhsjdw;
	}

	public Integer getJhsjzq() {
		return jhsjzq;
	}

	public void setJhsjzq(Integer jhsjzq) {
		this.jhsjzq = jhsjzq;
	}

	public Integer getZxsq() {
		return zxsq;
	}

	public void setZxsq(Integer zxsq) {
		this.zxsq = zxsq;
	}

	public Integer getXqsq() {
		return xqsq;
	}

	public void setXqsq(Integer xqsq) {
		this.xqsq = xqsq;
	}

	public Integer getGhsq() {
		return ghsq;
	}

	public void setGhsq(Integer ghsq) {
		this.ghsq = ghsq;
	}

	public Integer getYcsq() {
		return ycsq;
	}

	public void setYcsq(Integer ycsq) {
		this.ycsq = ycsq;
	}

	public Integer getSctqq() {
		return sctqq;
	}

	public void setSctqq(Integer sctqq) {
		this.sctqq = sctqq;
	}

	public Float getCbpsjnscl() {
		return cbpsjnscl;
	}

	public void setCbpsjnscl(Float cbpsjnscl) {
		this.cbpsjnscl = cbpsjnscl;
	}

	public Float getCbpsjnscl2() {
		return cbpsjnscl2;
	}

	public void setCbpsjnscl2(Float cbpsjnscl2) {
		this.cbpsjnscl2 = cbpsjnscl2;
	}

	public Float getCbpsdnscl() {
		return cbpsdnscl;
	}

	public void setCbpsdnscl(Float cbpsdnscl) {
		this.cbpsdnscl = cbpsdnscl;
	}

	public String getCbpclbfb() {
		return cbpclbfb;
	}

	public void setCbpclbfb(String cbpclbfb) {
		this.cbpclbfb = cbpclbfb;
	}

	public Float getCbpsjjscl() {
		return cbpsjjscl;
	}

	public void setCbpsjjscl(Float cbpsjjscl) {
		this.cbpsjjscl = cbpsjjscl;
	}

	public Float getCbpsdjscl() {
		return cbpsdjscl;
	}

	public void setCbpsdjscl(Float cbpsdjscl) {
		this.cbpsdjscl = cbpsdjscl;
	}

	public Float getCbpsjjscl2() {
		return cbpsjjscl2;
	}

	public void setCbpsjjscl2(Float cbpsjjscl2) {
		this.cbpsjjscl2 = cbpsjjscl2;
	}

	public Float getCbpsjyscl() {
		return cbpsjyscl;
	}

	public void setCbpsjyscl(Float cbpsjyscl) {
		this.cbpsjyscl = cbpsjyscl;
	}

	public Float getCbpsdyscl() {
		return cbpsdyscl;
	}

	public void setCbpsdyscl(Float cbpsdyscl) {
		this.cbpsdyscl = cbpsdyscl;
	}

	public Float getCbpsjyscl2() {
		return cbpsjyscl2;
	}

	public void setCbpsjyscl2(Float cbpsjyscl2) {
		this.cbpsjyscl2 = cbpsjyscl2;
	}

	public Float getCbpsjzscl() {
		return cbpsjzscl;
	}

	public void setCbpsjzscl(Float cbpsjzscl) {
		this.cbpsjzscl = cbpsjzscl;
	}

	public Float getCbpsjzscl2() {
		return cbpsjzscl2;
	}

	public void setCbpsjzscl2(Float cbpsjzscl2) {
		this.cbpsjzscl2 = cbpsjzscl2;
	}

	public Float getCbpsdzscl() {
		return cbpsdzscl;
	}

	public void setCbpsdzscl(Float cbpsdzscl) {
		this.cbpsdzscl = cbpsdzscl;
	}

	public Float getCbpsjrscl() {
		return cbpsjrscl;
	}

	public void setCbpsjrscl(Float cbpsjrscl) {
		this.cbpsjrscl = cbpsjrscl;
	}

	public Float getCbpsdrscl() {
		return cbpsdrscl;
	}

	public void setCbpsdrscl(Float cbpsdrscl) {
		this.cbpsdrscl = cbpsdrscl;
	}

	public Float getCbpsjrscl2() {
		return cbpsjrscl2;
	}

	public void setCbpsjrscl2(Float cbpsjrscl2) {
		this.cbpsjrscl2 = cbpsjrscl2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}


