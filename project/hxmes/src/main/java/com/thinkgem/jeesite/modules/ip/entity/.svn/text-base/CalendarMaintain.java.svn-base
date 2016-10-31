/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 工厂日历维护Entity
 * 
 * @author yrd
 * @version 2016-08-16
 */
@Entity
@Table(name = "IP_GCRL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CalendarMaintain extends IdEntity<CalendarMaintain> {

	private static final long serialVersionUID = 1L;
	//private String id; // 编号
	//public String gzzx_id;// 工作中心_ID
	public Date rq;// 日期
	public Integer gzrlx;// 工作日类型
	public Integer sfsb;// 是否上班
	public Integer bc;// 班次
	public String bm;// 班名
	public Integer bcxz;// 班次性质
	public Integer bcsfsb;// 班次是否上班
	public String faly;// 方案来源
	public String bz;// 备注
	
	public WorkCenter workcenter;
	
	public CalendarMaintain() {
		super();
	}

	public CalendarMaintain(String id) {
		this();
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "GZZX_ID")
	public WorkCenter getWorkcenter() {
		return workcenter;
	}

	public void setWorkcenter(WorkCenter workcenter) {
		this.workcenter = workcenter;
	}

	@Temporal(TemporalType.DATE)
	public Date getRq() {
		return rq;
	}

	public void setRq(Date rq) {
		this.rq = rq;
	}

	public Integer getGzrlx() {
		return gzrlx;
	}

	public void setGzrlx(Integer gzrlx) {
		this.gzrlx = gzrlx;
	}

	public Integer getSfsb() {
		return sfsb;
	}

	public void setSfsb(Integer sfsb) {
		this.sfsb = sfsb;
	}

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

	public Integer getBcxz() {
		return bcxz;
	}

	public void setBcxz(Integer bcxz) {
		this.bcxz = bcxz;
	}

	public Integer getBcsfsb() {
		return bcsfsb;
	}

	public void setBcsfsb(Integer bcsfsb) {
		this.bcsfsb = bcsfsb;
	}

	public String getFaly() {
		return faly;
	}

	public void setFaly(String faly) {
		this.faly = faly;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
