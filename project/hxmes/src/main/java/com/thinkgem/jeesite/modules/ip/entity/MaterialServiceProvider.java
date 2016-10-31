/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 物料服务供应商Entity
 * @author ZhangHD
 * @version 2016-06-15
 */
@Entity
@Table(name = "ip_wlfwgys")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MaterialServiceProvider extends IdEntity<MaterialServiceProvider> {
	private static final long serialVersionUID = 1L;
	private Material wlbm;// 物料编码_物料编码
	private Provider provider;// 供应商_供应商编码
	private Integer fwlx;// 服务类型
	private String bz;// 备注


	public MaterialServiceProvider() {
		super();
	}


	public MaterialServiceProvider(String id) {
		this();
		this.id = id;
	}


	@ManyToOne
	@JoinColumn(name = "GYWL_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public Material getWlbm() {
		return wlbm;
	}


	public void setWlbm(Material wlbm) {
		this.wlbm = wlbm;
	}


	@ManyToOne
	@JoinColumn(name = "GYS_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	public Provider getProvider() {
		return provider;
	}


	public void setProvider(Provider provider) {
		this.provider = provider;
	}


	public Integer getFwlx() {
		return fwlx;
	}


	public void setFwlx(Integer fwlx) {
		this.fwlx = fwlx;
	}


	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}
}
