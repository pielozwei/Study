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
 * 存储白名单Entity
 * 
 * @author ks
 * @version 2016-06-28
 */
@Entity
@Table(name = "ip_gyccbmd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueStore extends IdEntity<TechniqueStore> {

	private static final long serialVersionUID = 1L;
	// private String id; // 编号
	private String gygzzx_id; // 工艺工作中心_ID
	private String wl_id;// 物料_ID
	private String ccck_id;// 存储仓库_ID
	private Integer sfqy;// 是否启用
	private Integer xssx;// 显示顺序
	private String bz;// 备注

	public TechniqueStore() {
		super();
	}

	public TechniqueStore(String id) {
		this();
		this.id = id;
	}

	@Column(name="IP_GYGZZX_ID")
	public String getGygzzx_id() {
		return gygzzx_id;
	}

	public String getWl_id() {
		return wl_id;
	}

	public void setWl_id(String wl_id) {
		this.wl_id = wl_id;
	}

	public String getCcck_id() {
		return ccck_id;
	}

	public void setCcck_id(String ccck_id) {
		this.ccck_id = ccck_id;
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

	public void setGygzzx_id(String gygzzx_id) {
		this.gygzzx_id = gygzzx_id;
	}
}
