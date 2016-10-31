/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
 * 操作规程Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_sbczgc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentOpsSpec extends IdEntity<EquipmentOpsSpec> {
	
	private static final long serialVersionUID = 1L;

	private EquipmentModel equipmentModel; 	// 名称

	private Integer sfqy;    
	private Integer xssx;    
	private Integer gclb;    //规范类别
	@Length(min=0,max=4000)
	private String gcms;    //规范描述
	private String bz;    
	private String gcmc;
	
	
	public EquipmentOpsSpec() {
		super();
	}
	public EquipmentOpsSpec(String id){
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
	public String getGcmc() {
		return gcmc;
	}
	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}
	
}


