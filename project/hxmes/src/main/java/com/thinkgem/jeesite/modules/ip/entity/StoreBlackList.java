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
 * 仓库黑名单Entity
 * @author Lucl
 * @version 2016-06-17
 */
@Entity
@Table(name = "ip_ckhmd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoreBlackList extends IdEntity<StoreBlackList> {
	
	private static final long serialVersionUID = 1L;
	
	private String name; 	// 名称
	private Material ipWlbmWlbm;    //物料编码
	private Store ipCkCkbh;    //仓库编号
	private Date gydxsj;    //供应对象时间

	public StoreBlackList() {
		super();
	}

	public StoreBlackList(String id){
		this();
		this.id = id;
	}
	 
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public Material getIpWlbmWlbm() {
		return ipWlbmWlbm;
	}

	public void setIpWlbmWlbm(Material ipWlbmWlbm) {
		this.ipWlbmWlbm = ipWlbmWlbm;
	}

	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public Store getIpCkCkbh() {
		return ipCkCkbh;
	}

	public void setIpCkCkbh(Store ipCkCkbh) {
		this.ipCkCkbh = ipCkCkbh;
	}

	public Date getGydxsj() {
		return gydxsj;
	}

	public void setGydxsj(Date gydxsj) {
		this.gydxsj = gydxsj;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}


