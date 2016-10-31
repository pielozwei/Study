/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 工厂日历明细Entity
 * 
 * @author lzq
 * @version 2016-08-16
 */
@Entity
@Table(name = "IP_GCRLMX")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CalendarDetail extends IdEntity<CalendarDetail> {

	private static final long serialVersionUID = 1L;
	private String id; // 编号
	private String gcrlfa_id; // 工厂日历方案_id
	private Date rq;
	private Integer gzrlx;
	private Integer sfsb;
	private String bz;

	public CalendarDetail() {
		super();
	}

	public CalendarDetail(String id) {
		this();
		this.id = id;
	}

	@Length(min = 1, max = 200)
	public String getGcrlfa_id() {
		return gcrlfa_id;
	}

	public void setGcrlfa_id(String gcrlfa_id) {
		this.gcrlfa_id = gcrlfa_id;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
