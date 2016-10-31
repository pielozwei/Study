/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 人员业务类别Entity
 * 
 * @author yrd
 * @version 2016-09-01
 */
@Entity
@Table(name = "IP_RYYWLB")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmployeePositionRankDirector extends
		IdEntity<EmployeePositionRankDirector> {

	private static final long serialVersionUID = 1L;
	private String id; // 编号
	private String zg_id; // 职工_ID
	private String ywfl_id; // 业务分类_ID
	private String bz; // 备注

	public EmployeePositionRankDirector() {
		super();
	}

	public EmployeePositionRankDirector(String id) {
		this();
		this.id = id;
	}
	@Column(name="IP_ZGGRXX_ID")
	@Length(min = 1, max = 200)
	public String getZg_id() {
		return zg_id;
	}

	public void setZg_id(String zg_id) {
		this.zg_id = zg_id;
	}

	public String getYwfl_id() {
		return ywfl_id;
	}

	public void setYwfl_id(String ywfl_id) {
		this.ywfl_id = ywfl_id;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
