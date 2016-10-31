/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;


/**
 * 工器具分类Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_GZQJLB")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ToolCategory extends IdEntity<ToolCategory> {
	
	private static final long serialVersionUID = 1L;
	
	private ToolCategory parent; //上级id 不为空
	
	@NotNull
	@Length(min=1, max=30)
	@ExcelField(title="类别编码",align=1, sort=25)
	private String lbbh;	//类别编号 	最大长度30	不为空
	@Length(min=0, max=30)
	@ExcelField(title="类别名称",align=1, sort=30)
	private String lbmc;	//类别名称	最大长度30
	@ExcelField(title="层级码",align=1, sort=35)
	private Integer cjm;    //层级吗
	
	private String lbjc;    //工装器具类别简称
	private Integer sfqy;     //是否启用
	private Integer xssx;    //显示顺序
	private String bz;    //备注
	
	public ToolCategory() {
		super();
	}
	public ToolCategory(String id){
		this();
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SJLBID")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	@ExcelField(title="父级id",value="parent.id", align=1, sort=20)
	public ToolCategory getParent() {
		return parent;
	}
	public void setParent(ToolCategory parent) {
		this.parent = parent;
	}
	public String getLbbh() {
		return lbbh;
	}
	public void setLbbh(String lbbh) {
		this.lbbh = lbbh;
	}
	public String getLbmc() {
		return lbmc;
	}
	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
	public Integer getCjm() {
		return cjm;
	}
	public void setCjm(Integer cjm) {
		this.cjm = cjm;
	}
	public String getLbjc() {
		return lbjc;
	}
	public void setLbjc(String lbjc) {
		this.lbjc = lbjc;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}


