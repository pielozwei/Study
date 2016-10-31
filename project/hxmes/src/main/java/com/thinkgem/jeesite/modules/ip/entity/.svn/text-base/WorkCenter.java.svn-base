/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.io.Serializable;

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
 * 工作中心维护Entity
 * @author Lucl
 * @version 2016-06-20
 */
@Entity
@Table(name = "ip_gzzx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkCenter extends IdEntity<WorkCenter> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//private String id; 		// 编号
	private String name; 	// 名称
	private WorkCenter sjgzzxid;    //上级工作中心id
	private String gzzxbm;    //工作中心编码
	private String gzzxmc;    //工作中心名称
	private String gzzxjc;    //工作中心简称
	private Integer gzzxzt;    //工作中心状态
	private String lxdh;    //联系电话
	private String zbyddh;    //值班移动电话
	private Integer sfqjxdw;    //是否全局性单位
	private Integer sfsstdw;    //是否是实体单位
	private String xqsjsjdw;    //需求时界时间单位
	private Integer xysjsjz;    //需要时界时间值
	private String jhsjsjdw;    //计划时界时间单位
	private Integer jhsjsjz;    //计划时界时间值
	private Integer sfzcnxpc;    //是否支持逆向排程
	private Integer sfzczxpc;    //是否支持正向排程
	private Integer sfzczhpc;    //是否支持综合排程
	private Integer jhzpsjld;    //计划重排时间粒度
	private String wz;    //网址
	private String yx;    //邮箱
	private String dz;    //地址
	private Float jd;    //经度
	private Float wd;    //维度
	private String xzzgldgwmc;    //行政主管领导岗位名称
	private String dwzgldgwmc;    //党务主管领导岗位名称
	private String bmzgldgwmc;    //保密主管领导岗位名称
	private String aqzgldgwmc;    //安全主管领导岗位名称
	private String jj;    //简介
	private Integer sfyfz;    //是否有分址
	
	private String jhsjsm;    //计划时界说明
	private String xqsjsm;    //需求时界说明
	private String sfqy;   //是否启用
	private String xssx;   //显示顺序
	private String bz;   //备注
	private WorkShift gzbzId;    //班制

	public WorkCenter() {
		super();
	}

	public WorkCenter(String id){
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
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public WorkCenter getSjgzzxid() {
		return sjgzzxid;
	}

	public void setSjgzzxid(WorkCenter sjgzzxid) {
		this.sjgzzxid = sjgzzxid;
	}

	public String getGzzxbm() {
		return gzzxbm;
	}

	public void setGzzxbm(String gzzxbm) {
		this.gzzxbm = gzzxbm;
	}

	public String getGzzxmc() {
		return gzzxmc;
	}

	public void setGzzxmc(String gzzxmc) {
		this.gzzxmc = gzzxmc;
	}

	public String getGzzxjc() {
		return gzzxjc;
	}

	public void setGzzxjc(String gzzxjc) {
		this.gzzxjc = gzzxjc;
	}

	public Integer getGzzxzt() {
		return gzzxzt;
	}

	public void setGzzxzt(Integer gzzxzt) {
		this.gzzxzt = gzzxzt;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getZbyddh() {
		return zbyddh;
	}

	public void setZbyddh(String zbyddh) {
		this.zbyddh = zbyddh;
	}

	public Integer getSfqjxdw() {
		return sfqjxdw;
	}

	public void setSfqjxdw(Integer sfqjxdw) {
		this.sfqjxdw = sfqjxdw;
	}

	public Integer getSfsstdw() {
		return sfsstdw;
	}

	public void setSfsstdw(Integer sfsstdw) {
		this.sfsstdw = sfsstdw;
	}

	public String getXqsjsjdw() {
		return xqsjsjdw;
	}

	public void setXqsjsjdw(String xqsjsjdw) {
		this.xqsjsjdw = xqsjsjdw;
	}

	public Integer getXysjsjz() {
		return xysjsjz;
	}

	public void setXysjsjz(Integer xysjsjz) {
		this.xysjsjz = xysjsjz;
	}

	public String getJhsjsjdw() {
		return jhsjsjdw;
	}

	public void setJhsjsjdw(String jhsjsjdw) {
		this.jhsjsjdw = jhsjsjdw;
	}

	public Integer getJhsjsjz() {
		return jhsjsjz;
	}

	public void setJhsjsjz(Integer jhsjsjz) {
		this.jhsjsjz = jhsjsjz;
	}

	public Integer getSfzcnxpc() {
		return sfzcnxpc;
	}

	public void setSfzcnxpc(Integer sfzcnxpc) {
		this.sfzcnxpc = sfzcnxpc;
	}

	public Integer getSfzczxpc() {
		return sfzczxpc;
	}

	public void setSfzczxpc(Integer sfzczxpc) {
		this.sfzczxpc = sfzczxpc;
	}

	public Integer getSfzczhpc() {
		return sfzczhpc;
	}

	public void setSfzczhpc(Integer sfzczhpc) {
		this.sfzczhpc = sfzczhpc;
	}

	public Integer getJhzpsjld() {
		return jhzpsjld;
	}

	public void setJhzpsjld(Integer jhzpsjld) {
		this.jhzpsjld = jhzpsjld;
	}

	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public Float getJd() {
		return jd;
	}

	public void setJd(Float jd) {
		this.jd = jd;
	}

	public Float getWd() {
		return wd;
	}

	public void setWd(Float wd) {
		this.wd = wd;
	}

	public String getXzzgldgwmc() {
		return xzzgldgwmc;
	}

	public void setXzzgldgwmc(String xzzgldgwmc) {
		this.xzzgldgwmc = xzzgldgwmc;
	}

	public String getDwzgldgwmc() {
		return dwzgldgwmc;
	}

	public void setDwzgldgwmc(String dwzgldgwmc) {
		this.dwzgldgwmc = dwzgldgwmc;
	}

	public String getBmzgldgwmc() {
		return bmzgldgwmc;
	}

	public void setBmzgldgwmc(String bmzgldgwmc) {
		this.bmzgldgwmc = bmzgldgwmc;
	}

	public String getAqzgldgwmc() {
		return aqzgldgwmc;
	}

	public void setAqzgldgwmc(String aqzgldgwmc) {
		this.aqzgldgwmc = aqzgldgwmc;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public Integer getSfyfz() {
		return sfyfz;
	}

	public void setSfyfz(Integer sfyfz) {
		this.sfyfz = sfyfz;
	}
	
	public String getJhsjsm() {
		return jhsjsm;
	}

	public void setJhsjsm(String jhsjsm) {
		this.jhsjsm = jhsjsm;
	}

	public String getXqsjsm() {
		return xqsjsm;
	}

	public void setXqsjsm(String xqsjsm) {
		this.xqsjsm = xqsjsm;
	}
	
	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public WorkShift getGzbzId() {
		return gzbzId;
	}

	public void setGzbzId(WorkShift gzbzId) {
		this.gzbzId = gzbzId;
	}	
}


