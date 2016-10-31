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
import com.thinkgem.jeesite.modules.ip.entity.Organization;

/**
 * 工作中心机构Entity
 * @author lucl
 * @version 2016-06-23
 */
@Entity
@Table(name = "IP_GZZXZXJG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkCenterOrganization extends IdEntity<WorkCenterOrganization> {
	
	private static final long serialVersionUID = 1L;
	//private String id; 		// 编号
	private String name; 	// 名称
	private String ipGzzxId;    //工作中心_ID
	private Organization zzjgbm;    //组织机构编码
	

	public WorkCenterOrganization() {
		super();
	}

	public WorkCenterOrganization(String id){
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

	public String getIpGzzxId() {
		return ipGzzxId;
	}

	public void setIpGzzxId(String ipGzzxId) {
		this.ipGzzxId = ipGzzxId;
	}
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public Organization getZzjgbm() {
		return zzjgbm;
	}

	public void setZzjgbm(Organization zzjgbm) {
		this.zzjgbm = zzjgbm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}


