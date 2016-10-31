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
 * 副产品信息Entity
 * @author WuWB
 * @version 2016-06-27
 */
@Entity
@Table(name = "ip_gyfcp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueOutput extends IdEntity<TechniqueOutput> {
	
	private static final long serialVersionUID = 1L;
	// private String id; 
	// 编号
	private String jldw;
	private String ip_gygzzx_id; 	// 工艺工作中心ID
	private String ip_wl_id;
	private Integer ip_fcplb;
	public String getIp_gygzzx_id() {
		return ip_gygzzx_id;
	}

	public void setIp_gygzzx_id(String ip_gygzzx_id) {
		this.ip_gygzzx_id = ip_gygzzx_id;
	}

	private Float sl;
	private Integer sfqy;
	private Integer xssx;
	private String bz;




	public String getJldw() {
		return jldw;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public String getip_wl_id() {
		return ip_wl_id;
	}

	public void setip_wl_id(String ip_wl_id) {
		this.ip_wl_id = ip_wl_id;
	}

	public Integer getip_fcplb() {
		return ip_fcplb;
	}

	public void setip_fcplb(Integer ip_fcplb) {
		this.ip_fcplb = ip_fcplb;
	}

	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}
	
	public Float getSl(){
		return sl;
	}

	public void setSl(Float sl) {
		this.sl = sl;
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

	public TechniqueOutput() {
		super();
	}

	public TechniqueOutput(String id){
		this();
		this.id = id;
	}
}


