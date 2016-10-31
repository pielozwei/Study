/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 人员业务分类管理Entity
 * @author tianjingyi,yangsu
 * @version 2016-06-27
 */
@Entity
@Table(name = "IP_GYWJ")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueDocument extends IdEntity<TechniqueDocument> {
	
	private static final long serialVersionUID = 1L;
	// private String id; 		// 编号
	
	//private String gylx_id; //工艺路线ID
	private Integer wjlb;   //文件类别
	private String wjbm;    //文件编码
	private String wjbtmc;  //文件标题名称
	private String bbh;     //版本号
	private String fjwjm;     //附件文件名
	private String bzr;
	private Date bzrq;
	private String shr;
	private Date shrq;
	private String fbdw;
	private Date fbrq;
	private Date ssrq;
	private String ggbc;
	private Date ggrq;
	private String ggsm;
	private String ggnr;
	private String fwxh;    //发文序号
	private String fwdw;    //发文单位
	private Date fwrq;      //发文日期
	private Integer sfqy;   //是否启用
	//private String BZ;
	
	private Technique technique;
	
	public TechniqueDocument() {
		super();
	}

	public TechniqueDocument(String id){
		this();
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="IP_GYLX_ID")
	public Technique getTechnique() {
		return technique;
	}

	public void setTechnique(Technique technique) {
		this.technique = technique;
	}
	
//	@Column(name="IP_GYLX_ID")
//	public String getGylx_id() {
//		return gylx_id;
//	}
//
//	public void setGylx_id(String gylx_id) {
//		this.gylx_id = gylx_id;
//	}

	public Integer getWjlb() {
		return wjlb;
	}

	public void setWjlb(Integer wjlb) {
		this.wjlb = wjlb;
	}

	public String getWjbm() {
		return wjbm;
	}

	public void setWjbm(String wjbm) {
		this.wjbm = wjbm;
	}

	public String getWjbtmc() {
		return wjbtmc;
	}

	public void setWjbtmc(String wjbtmc) {
		this.wjbtmc = wjbtmc;
	}

	public String getBbh() {
		return bbh;
	}

	public void setBbh(String bbh) {
		this.bbh = bbh;
	}

	public String getFjwjm() {
		return fjwjm;
	}

	public void setFjwjm(String fjwjm) {
		this.fjwjm = fjwjm;
	}

	public String getBzr() {
		return bzr;
	}

	public void setBzr(String bzr) {
		this.bzr = bzr;
	}

	public Date getBzrq() {
		return bzrq;
	}

	public void setBzrq(Date bzrq) {
		this.bzrq = bzrq;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public Date getShrq() {
		return shrq;
	}

	public void setShrq(Date shrq) {
		this.shrq = shrq;
	}

	public String getFbdw() {
		return fbdw;
	}

	public void setFbdw(String fbdw) {
		this.fbdw = fbdw;
	}

	public Date getFbrq() {
		return fbrq;
	}

	public void setFbrq(Date fbrq) {
		this.fbrq = fbrq;
	}

	public Date getSsrq() {
		return ssrq;
	}

	public void setSsrq(Date ssrq) {
		this.ssrq = ssrq;
	}

	public String getGgbc() {
		return ggbc;
	}

	public void setGgbc(String ggbc) {
		this.ggbc = ggbc;
	}

	public Date getGgrq() {
		return ggrq;
	}

	public void setGgrq(Date ggrq) {
		this.ggrq = ggrq;
	}

	public String getGgsm() {
		return ggsm;
	}

	public void setGgsm(String ggsm) {
		this.ggsm = ggsm;
	}

	public String getGgnr() {
		return ggnr;
	}

	public void setGgnr(String ggnr) {
		this.ggnr = ggnr;
	}

	public String getFwxh() {
		return fwxh;
	}

	public void setFwxh(String fwxh) {
		this.fwxh = fwxh;
	}

	public String getFwdw() {
		return fwdw;
	}

	public void setFwdw(String fwdw) {
		this.fwdw = fwdw;
	}

	public Date getFwrq() {
		return fwrq;
	}

	public void setFwrq(Date fwrq) {
		this.fwrq = fwrq;
	}

	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}
	 
	
	
	
}


