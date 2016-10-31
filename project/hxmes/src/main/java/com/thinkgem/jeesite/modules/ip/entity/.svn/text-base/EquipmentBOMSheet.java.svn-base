/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

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
 * 设备BOMEntity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBBOMD")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentBOMSheet extends IdEntity<EquipmentBOMSheet> {
	
	private static final long serialVersionUID = 1L;
	private EquipmentModel equipmentModel; 	// 名称
	@NotNull
	@Length(min=1,max=100)
	@ExcelField(title="设备BOM编号",align=1, sort=25)
	private String sbbomdbh;	//设备BOM编号 VARCHAR2	100	0	True
	@Length(min=0,max=30)
	@ExcelField(title="版本号",align=1, sort=30)
	private String bbh;	//版本号VARCHAR2	30	0	False
	@ExcelField(title="设备BOM单状态",dictType="d_shiyong",align=1, sort=35)
	private Integer sbbomdzt;	//设备BOM单状态NUMBER	0	0	False
	@Length(min=0,max=30)
	@ExcelField(title="创建人",align=1, sort=40)
	private String cjr;	//创建人VARCHAR2	30	0	False
	@ExcelField(title="创建日期",align=1, sort=45)
	private Date cjrq;//创建日期	DATE	7	0	False
	@Length(min=0,max=30)
	@ExcelField(title="审核人",align=1, sort=50)
	private String shr;	//审核人VARCHAR2	30	0	False
	@ExcelField(title="审核日期",align=1, sort=55)
	private Date shrq;//审核日期	DATE	7	0	False
	//ZHD增加字段
	private Integer sfqy;
	private Integer xssx;
	private String bz;

	public EquipmentBOMSheet() {
		super();
	}
	public EquipmentBOMSheet(String id){
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
	public String getBbh() {
		return bbh;
	}
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	public Integer getSbbomdzt() {
		return sbbomdzt;
	}
	public void setSbbomdzt(Integer sbbomdzt) {
		this.sbbomdzt = sbbomdzt;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Date getCjrq() {
		return cjrq;
	}
	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
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
	public String getSbbomdbh() {
		return sbbomdbh;
	}
	public void setSbbomdbh(String sbbomdbh) {
		this.sbbomdbh = sbbomdbh;
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


