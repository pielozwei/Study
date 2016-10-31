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
 * 工艺参数Entity
 * @author zzc
 * @version 2016-06-28
 */
@Entity
@Table(name = "ip_gycs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueParameter extends IdEntity<TechniqueParameter> {
	
	private static final long serialVersionUID = 1L;
	// private String id; 		// ID
	//private String gylx_id; // 工艺路线_ID
	private String gycsdw; 	// 计量单位
	private String gycsbm;	//工艺参数编码
	private String gycsmc;	//工艺参数名称
	private float gycssxz;	//工艺参数上限值
	private float gycsxxz;	//工艺参数下限值
	private float gycsbzz;	//工艺参数标准值	
	private Integer sfqy;	//是否启用	
	private Integer xssx;	//显示顺序
	private String bz; 		// 备注
	
	private Technique technique;
	
	@ManyToOne
	@JoinColumn(name="IP_GYLX_ID")
	public Technique getTechnique() {
		return technique;
	}

	public void setTechnique(Technique technique) {
		this.technique = technique;
	}

	public TechniqueParameter() {
		super();
	}

	public TechniqueParameter(String id){
		this();
		this.id = id;
	}

	
	
//	public String getGylx_id() {
//		return gylx_id;
//	}
//
//	public void setGylx_id(String gylx_id) {
//		this.gylx_id = gylx_id;
//	}


	public String getGycsdw() {
		return gycsdw;
	}

	public void setGycsdw(String gycsdw) {
		this.gycsdw = gycsdw;
	}

	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}

	public String getGycsbm() {
		return gycsbm;
	}

	public void setGycsbm(String gycsbm) {
		this.gycsbm = gycsbm;
	}

	public String getGycsmc() {
		return gycsmc;
	}

	public void setGycsmc(String gycsmc) {
		this.gycsmc = gycsmc;
	}

	public float getGycssxz() {
		return gycssxz;
	}

	public void setGycssxz(float gycssxz) {
		this.gycssxz = gycssxz;
	}

	public float getGycsxxz() {
		return gycsxxz;
	}

	public void setGycsxxz(float gycsxxz) {
		this.gycsxxz = gycsxxz;
	}

	public float getGycsbzz() {
		return gycsbzz;
	}

	public void setGycsbzz(float gycsbzz) {
		this.gycsbzz = gycsbzz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	
}


