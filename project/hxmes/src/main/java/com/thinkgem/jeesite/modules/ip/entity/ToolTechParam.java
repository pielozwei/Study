/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;


/**
 * 关键技术参数Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_GZQJJSCS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ToolTechParam extends IdEntity<ToolTechParam> {
	
	private static final long serialVersionUID = 1L;

	private Tool tool; 	// 名称
	private String csdm;//参数代码	VARCHAR2	30	0	False
	private String csmc;//参数名称	VARCHAR2	30	0	False
	private Integer cszlx;//参数值类型	NUMBER	0	0	False
	private String ckbm;//仓库编码	VARCHAR2	30	0	False
	private String cssm;//参数说明 200
//	private String csjldw;//参数计量单位 100
	private Float bzz;//标准值FLOAT	126	0	False
	private Float sxz;//上限值	FLOAT	126	0	False
	private Float xxz;//下限值	FLOAT	126	0	False
	
	private Jldw jldwId;    //计量单位ID
	private Integer sfqy;    //是否启用
	private Integer xssx;    //显示顺序
	private String csjc;    //参数简称
	private String bz;    //备注
	
	public ToolTechParam() {
		super();
	}
	public ToolTechParam(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="IP_GZQJ_ID")
	@ManyToOne
	@JoinColumn(name="IP_GZQJ_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="tool.id", align=1, sort=20)
	public Tool getTool() {
		return tool;
	}
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	@NotNull
	@Length(min=1,max=30)
	@ExcelField(title="参数代码",align=1, sort=25)
	public String getCsdm() {
		return csdm;
	}
	public void setCsdm(String csdm) {
		this.csdm = csdm;
	}
	@Length(min=0,max=30)
	@ExcelField(title="参数名称",align=1, sort=30)
	public String getCsmc() {
		return csmc;
	}
	public void setCsmc(String csmc) {
		this.csmc = csmc;
	}
	@ExcelField(title="参数值类型",dictType="d_qzqjcszlx",align=1, sort=35)
	public Integer getCszlx() {
		return cszlx;
	}
	public void setCszlx(Integer cszlx) {
		this.cszlx = cszlx;
	}
	@Length(min=0,max=30)
	@ExcelField(title="仓库编码",align=1, sort=40)
	public String getCkbm() {
		return ckbm;
	}
	public void setCkbm(String ckbm) {
		this.ckbm = ckbm;
	}
	@Length(min=0,max=200)
	@ExcelField(title="参数说明",align=1, sort=45)
	public String getCssm() {
		return cssm;
	}
	public void setCssm(String cssm) {
		this.cssm = cssm;
	}
	/*@Length(min=0,max=100)
	@ExcelField(title="参数计量单位",align=1, sort=50)
	public String getCsjldw() {
		return csjldw;
	}
	public void setCsjldw(String csjldw) {
		this.csjldw = csjldw;
	}*/
	@ExcelField(title="标准值",align=1, sort=30)
	public Float getBzz() {
		return bzz;
	}
	public void setBzz(Float bzz) {
		this.bzz = bzz;
	}
	@ExcelField(title="上限值",align=1, sort=30)
	public Float getSxz() {
		return sxz;
	}
	public void setSxz(Float sxz) {
		this.sxz = sxz;
	}
	@ExcelField(title="下限值",align=1, sort=30)
	public Float getXxz() {
		return xxz;
	}
	public void setXxz(Float xxz) {
		this.xxz = xxz;
	}
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	public Jldw getJldwId() {
		return jldwId;
	}
	public void setJldwId(Jldw jldwId) {
		this.jldwId = jldwId;
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
	public String getCsjc() {
		return csjc;
	}
	public void setCsjc(String csjc) {
		this.csjc = csjc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}


