/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 规程要求Entity
 * @author WuWB
 * @version 2016-06-27
 */
@Entity
@Table(name = "ip_gyczgc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueOpsStep extends IdEntity<TechniqueOpsStep> {
	
	private static final long serialVersionUID = 1L;
	// private String id; 		// 编号
	//private String gylx_id; 	// 工艺路线ID
	private String gcmc;
	private Integer gclb;
	private String gcms;
	private Integer sfqy;
	private Integer xssx;
	private String bz;

	private Technique technique;

	@ManyToOne
	@JoinColumn(name="IP_GYLX_ID")
	public Technique getTechnique() {
		return technique;
	}
	
	public void setTechnique(Technique technique) {
		this.technique = technique;
	}

	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

//	@Column(name="IP_GYLX_ID")
//	public String getgylx_id() {
//		return gylx_id;
//	}
//
//	public void setgylx_id(String gylx_id) {
//		this.gylx_id = gylx_id;
//	}
	
	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}

	public Integer getGclb() {
		return gclb;
	}

	public void setGclb(Integer gclb) {
		this.gclb = gclb;
	}

	public String getGcms() {
		return gcms;
	}

	public void setGcms(String gcms) {
		this.gcms = gcms;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public TechniqueOpsStep() {
		super();
	}

	public TechniqueOpsStep(String id){
		this();
		this.id = id;
	}

	public String getGcmc() {
		return gcmc;
	}

	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}
}


