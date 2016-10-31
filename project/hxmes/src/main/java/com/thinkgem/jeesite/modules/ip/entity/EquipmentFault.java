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
 * 故障树Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBGZS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentFault extends IdEntity<EquipmentFault> {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentModel equipmentModel; 	// 名称
	@NotNull
	@Length(min=1,max=30)
	String gzbm;    //故障编码
	Integer gzlb;    //故障类别
	Integer gzjbabc;    //故障级别ABC
	@Length(min=0,max=30)
	String xxmsgjc;    //现象描述关键词
	@Length(min=0,max=100)
	String gzyspwjm;    //音视频文件名
	@Length(min=0,max=4000)
	String gzpcznwjm;    //故障排除指南
	@Length(min=0,max=30)
	String gzpcyspwjm;    //故障排除音视频文件名
	private String gzxx;//故障现象
	private String bz;
	private Integer sfqy;
	private Integer xssx;
	
	
	
	public EquipmentFault() {
		super();
	}
	public EquipmentFault(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="SBGGXH_ID")
	@ManyToOne
	@JoinColumn(name="SBGGXH_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="equipmentModel.id", align=1, sort=20)
	public EquipmentModel getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(EquipmentModel equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	public String getGzbm() {
		return gzbm;
	}
	public void setGzbm(String gzbm) {
		this.gzbm = gzbm;
	}
	public Integer getGzlb() {
		return gzlb;
	}
	public void setGzlb(Integer gzlb) {
		this.gzlb = gzlb;
	}
	public Integer getGzjbabc() {
		return gzjbabc;
	}
	public void setGzjbabc(Integer gzjbabc) {
		this.gzjbabc = gzjbabc;
	}
	public String getXxmsgjc() {
		return xxmsgjc;
	}
	public void setXxmsgjc(String xxmsgjc) {
		this.xxmsgjc = xxmsgjc;
	}
	public String getGzpcyspwjm() {
		return gzpcyspwjm;
	}
	public void setGzpcyspwjm(String gzpcyspwjm) {
		this.gzpcyspwjm = gzpcyspwjm;
	}
	public String getGzyspwjm() {
		return gzyspwjm;
	}
	public void setGzyspwjm(String gzyspwjm) {
		this.gzyspwjm = gzyspwjm;
	}
	public String getGzpcznwjm() {
		return gzpcznwjm;
	}
	public void setGzpcznwjm(String gzpcznwjm) {
		this.gzpcznwjm = gzpcznwjm;
	}
	public String getGzxx() {
		return gzxx;
	}
	public void setGzxx(String gzxx) {
		this.gzxx = gzxx;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
	
}


