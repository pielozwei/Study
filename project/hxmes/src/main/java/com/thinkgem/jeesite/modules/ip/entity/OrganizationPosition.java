/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 岗位信息管理Entity
 * @author Iris
 * @version 2016-06-21
 */
@Entity
@Table(name = "IP_GW")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganizationPosition extends IdEntity<OrganizationPosition> {
	
	private static final long serialVersionUID = 1L;
//	private String zzjgbm_id; 		// 组织机构部门id
//	private String ywlb_id; 	// 业务类别id
	private	String gwbm;
	private Integer	sfqy;
	private String gwmc;
	private String gwms;
	private Integer xssx;
	private String bz;

	private OrganizationPositionRank organizationposintionrank;
	private Organization organization;
	
	public OrganizationPosition() {
		super();
	}

	public OrganizationPosition(String id){
		this();
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="YWLB_ID")
	public OrganizationPositionRank getOrganizationposintionrank() {
		return organizationposintionrank;
	}

	public void setOrganizationposintionrank(
			OrganizationPositionRank organizationposintionrank) {
		this.organizationposintionrank = organizationposintionrank;
	}

	@ManyToOne
	@JoinColumn(name="ZZJGBM_ID")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
//	public String getZzjgbm_id() {
//		return zzjgbm_id;
//	}
//
//	public void setZzjgbm_id(String zzjgbm_id) {
//		this.zzjgbm_id = zzjgbm_id;
//	}

//	public String getYwlb_id() {
//		return ywlb_id;
//	}
//
//	public void setYwlb_id(String ywlb_id) {
//		this.ywlb_id = ywlb_id;
//	}

	

	public String getGwbm() {
		return gwbm;
	}

	public void setGwbm(String gwbm) {
		this.gwbm = gwbm;
	}

	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}

	public String getGwmc() {
		return gwmc;
	}

	public void setGwmc(String gwmc) {
		this.gwmc = gwmc;
	}

	public String getGwms() {
		return gwms;
	}

	public void setGwms(String gwms) {
		this.gwms = gwms;
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


