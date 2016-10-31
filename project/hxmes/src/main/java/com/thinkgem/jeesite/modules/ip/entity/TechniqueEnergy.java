/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 能源定额Entity
 * @author WuWB
 * @version 2016-06-27
 */
@Entity
@Table(name = "ip_gynyde")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueEnergy extends IdEntity<TechniqueEnergy> {
	
	private static final long serialVersionUID = 1L;
	private String id; 		// 编号
	private String gygzzx_id; 	// 工艺工作中心ID
	private String jldw_id;
	private Integer nylx;
	private Float sl;
	private Integer sfqy;
	private Integer xssx;
	private String bz;
	
	public Float getSl() {
		return sl;
	}

	public void setSl(Float sl) {
		this.sl = sl;
	}

	public String getGygzzx_id() {
		return gygzzx_id;
	}

	public void setGygzzx_id(String gygzzx_id) {
		this.gygzzx_id = gygzzx_id;
	}

	public Integer getNylx() {
		return nylx;
	}

	public void setNylx(Integer nylx) {
		this.nylx = nylx;
	}

	public String getJldw_id() {
		return jldw_id;
	}

	public void setJldw_id(String jldw_id) {
		this.jldw_id = jldw_id;
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

	public TechniqueEnergy() {
		super();
	}

	public TechniqueEnergy(String id){
		this();
		this.id = id;
	}
}


