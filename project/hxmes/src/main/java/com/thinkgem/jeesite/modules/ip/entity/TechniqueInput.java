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
 * 投入物料Entity
 * 
 * @author yrd
 * @version 2016-06-28
 */
@Entity
@Table(name = "IP_GYTRWL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueInput extends IdEntity<TechniqueInput> {

	private static final long serialVersionUID = 1L;
	private String id; // ID
	private String wl_id; // 物料_ID
	private String jldw_id; // 计量单位_ID
	private String gygzzx_id; // 工艺工作中心_ID
	private Integer lyk_id; // 物料来源库
	private Float xhde; // 消耗定额
	private Integer sfqy;
	private Integer xssx;
	private String bz;

	@Length(min = 1, max = 200)
	public TechniqueInput() {
		super();
	}

	public TechniqueInput(String id) {
		this();
		this.id = id;
	}
	
	public String getWl_id() {
		return wl_id;
	}

	public void setWl_id(String wl_id) {
		this.wl_id = wl_id;
	}

	public String getJldw_id() {
		return jldw_id;
	}

	public void setJldw_id(String jldw_id) {
		this.jldw_id = jldw_id;
	}

	public String getGygzzx_id() {
		return gygzzx_id;
	}

	public void setGygzzx_id(String gygzzx_id) {
		this.gygzzx_id = gygzzx_id;
	}

	public Integer getLyk_id() {
		return lyk_id;
	}

	public void setLyk_id(Integer lyk_id) {
		this.lyk_id = lyk_id;
	}

	public Float getXhde() {
		return xhde;
	}

	public void setXhde(Float xhde) {
		this.xhde = xhde;
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
}
