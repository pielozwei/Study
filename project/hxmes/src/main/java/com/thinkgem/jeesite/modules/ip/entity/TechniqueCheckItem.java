/**
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 质量要求Entity
 * 
 * @author ls
 * @version 2016-06-27
 */
@Entity
@Table(name = "ip_gyzljcx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TechniqueCheckItem extends IdEntity<TechniqueCheckItem> {

	private static final long serialVersionUID = 1L;
	// private String id; // 编号
	//private String wl_id; // 物料_ID
	//private String gylx_id; // 工艺路线_ID
	private String jyxmdw; // 计量单位_ID
	
	private String jyxmbh;// 检验项目编号
	//private String jyxmlb;// 检验项目类别
	private String jyxmmc;// 检验项目名称
	private String jyxmjc;// 检验项目简称
	
	private Integer jyxmsxz;// 检验项目上限值
	private Integer jyxmxxz;// 检验项目下限值
	private Integer jyxmbzz;// 检验项目标准值
	
	private Integer jyff;// 检验方法	
	private String jyqj;// 检验器具
	private Integer jyfs;// 检验方式
	private Integer sfzj;// 是否自检
	private String jypc;// 检验频次
	private String cjczbzh;//抽检参照标准号
	private String bfhxdclfs;// 不符合项的处理方式
	private Integer sfjsz;// 是否计算值
	private String jsgs;// 计算公式
    private Integer sfqy;//是否启用
    private Integer xssx; // 显示顺序
	private String bz;// 备注
	
	private Technique technique;

	public TechniqueCheckItem() {
		super();
	}

	public TechniqueCheckItem(String id) {
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

	public Integer getXssx() {
		return xssx;
	}

	public void setXssx(Integer xssx) {
		this.xssx = xssx;
	}

	public String getJyxmbh() {
		return jyxmbh;
	}

	public void setJyxmbh(String jyxmbh) {
		this.jyxmbh = jyxmbh;
	}

	public String getJyxmmc() {
		return jyxmmc;
	}

	public void setJyxmmc(String jyxmmc) {
		this.jyxmmc = jyxmmc;
	}

	public String getJyxmjc() {
		return jyxmjc;
	}

	public void setJyxmjc(String jyxmjc) {
		this.jyxmjc = jyxmjc;
	}

//	public String getJyxmlb() {
//		return jyxmlb;
//	}
//
//	public void setJyxmlb(String jyxmlb) {
//		this.jyxmlb = jyxmlb;
//	}

	public Integer getJyxmsxz() {
		return jyxmsxz;
	}

	public void setJyxmsxz(Integer jyxmsxz) {
		this.jyxmsxz = jyxmsxz;
	}

	public Integer getJyxmxxz() {
		return jyxmxxz;
	}

	public void setJyxmxxz(Integer jyxmxxz) {
		this.jyxmxxz = jyxmxxz;
	}

	public Integer getJyxmbzz() {
		return jyxmbzz;
	}

	public void setJyxmbzz(Integer jyxmbzz) {
		this.jyxmbzz = jyxmbzz;
	}

	public Integer getJyff() {
		return jyff;
	}

	public void setJyff(Integer jyff) {
		this.jyff = jyff;
	}

	public String getJyqj() {
		return jyqj;
	}

	public void setJyqj(String jyqj) {
		this.jyqj = jyqj;
	}

	public Integer getJyfs() {
		return jyfs;
	}

	public void setJyfs(Integer jyfs) {
		this.jyfs = jyfs;
	}

	public Integer getSfzj() {
		return sfzj;
	}

	public void setSfzj(Integer sfzj) {
		this.sfzj = sfzj;
	}

	public String getJypc() {
		return jypc;
	}

	public void setJypc(String jypc) {
		this.jypc = jypc;
	}

	public String getCjczbzh() {
		return cjczbzh;
	}

	public void setCjczbzh(String cjczbzh) {
		this.cjczbzh = cjczbzh;
	}

	public String getBfhxdclfs() {
		return bfhxdclfs;
	}

	public void setBfhxdclfs(String bfhxdclfs) {
		this.bfhxdclfs = bfhxdclfs;
	}

	public Integer getSfjsz() {
		return sfjsz;
	}

	public void setSfjsz(Integer sfjsz) {
		this.sfjsz = sfjsz;
	}

	public String getJsgs() {
		return jsgs;
	}

	public void setJsgs(String jsgs) {
		this.jsgs = jsgs;
	}

	public Integer getSfqy() {
		return sfqy;
	}

	public void setSfqy(Integer sfqy) {
		this.sfqy = sfqy;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getJyxmdw() {
		return jyxmdw;
	}

	public void setJyxmdw(String jyxmdw) {
		this.jyxmdw = jyxmdw;
	}

}
