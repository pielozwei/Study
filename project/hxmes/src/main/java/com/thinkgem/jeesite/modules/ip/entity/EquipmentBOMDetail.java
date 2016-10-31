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
 * 设备BOM明细Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBBOMMX")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentBOMDetail extends IdEntity<EquipmentBOMDetail> {
	private static final long serialVersionUID = 1L;
	private EquipmentBOMSheet equipmentBOMSheet;
	private EquipmentModel equipmentModel;// LBJBM VARCHAR2(100) N 零部件编码
	private String sjbommxid;
	private String cj; // VARCHAR2(30) Y 层次码
	private Integer sl; // INTEGER Y 消耗定额---数量
	private Integer sbbomzjlb; // d_sbbomzjlb INTEGER N 设备BOM组件类别
	private Boolean sfgjbj; // d_yesno INTEGER N 是否关键备件
	private String th; // VARCHAR2(30) Y 图号
	private String bz; // VARCHAR2(255) Y 备注
	private String sjzbljId;


	public EquipmentBOMDetail() {
		super();
	}


	public EquipmentBOMDetail(String id) {
		this();
		this.id = id;
	}


	@IndexColumn(name = "SBBOMD_ID")
	@ManyToOne
	@JoinColumn(name = "SBBOMD_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title = "设备规格型号编码", value = "equipmentBOMSheet.id", align = 1, sort = 20)
	public EquipmentBOMSheet getEquipmentBOMSheet() {
		return equipmentBOMSheet;
	}


	public void setEquipmentBOMSheet(EquipmentBOMSheet equipmentBOMSheet) {
		this.equipmentBOMSheet = equipmentBOMSheet;
	}


	@ManyToOne
	@JoinColumn(name = "ZBLJ_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title = "部组配件编码", value = "equipmentModel.sbggxhbm", align = 1, sort = 25)
	public EquipmentModel getEquipmentModel() {
		return equipmentModel;
	}


	public void setEquipmentModel(EquipmentModel equipmentModel) {
		this.equipmentModel = equipmentModel;
	}


	@ExcelField(title = "设备BOM组件类别", dictType = "d_sbbomzjlb", align = 1, sort = 45)
	public Integer getSbbomzjlb() {
		return sbbomzjlb;
	}


	public String getSjbommxid() {
		return sjbommxid;
	}


	public void setSjbommxid(String sjbommxid) {
		this.sjbommxid = sjbommxid;
	}


	public void setSbbomzjlb(Integer sbbomzjlb) {
		this.sbbomzjlb = sbbomzjlb;
	}


	@ExcelField(title = "是否关键备件", dictType = "d_yesno", align = 1, sort = 50)
	public Boolean getSfgjbj() {
		return sfgjbj;
	}


	public void setSfgjbj(Boolean sfgjbj) {
		this.sfgjbj = sfgjbj;
	}


	@Length(min = 0, max = 30)
	@ExcelField(title = "图号", align = 1, sort = 55)
	public String getTh() {
		return th;
	}


	public void setTh(String th) {
		this.th = th;
	}


	@Length(min = 0, max = 255)
	@ExcelField(title = "备注", align = 1, sort = 60)
	public String getBz() {
		return bz;
	}


	public void setBz(String bz) {
		this.bz = bz;
	}


	public String getCj() {
		return cj;
	}


	public void setCj(String cj) {
		this.cj = cj;
	}


	public Integer getSl() {
		return sl;
	}


	public void setSl(Integer sl) {
		this.sl = sl;
	}


	public String getSjzbljId() {
		return sjzbljId;
	}


	public void setSjzbljId(String sjzbljId) {
		this.sjzbljId = sjzbljId;
	}
}
