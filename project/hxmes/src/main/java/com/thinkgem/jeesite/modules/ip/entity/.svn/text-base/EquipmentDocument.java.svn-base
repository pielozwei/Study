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
 * 技术资料Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBWD")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentDocument extends IdEntity<EquipmentDocument> {
	
	private static final long serialVersionUID = 1L;

	private EquipmentModel equipmentModel; 	// 名称
	@NotNull
	@Length(min=1, max=30)
	@ExcelField(title="文档编码",align=1, sort=22)
	private String wdbm;	//文档编码
	@NotNull
	@Length(min=1, max=100)
	@ExcelField(title="文档标题",align=1, sort=23)
	private String wdbt;	//文档标题
	@NotNull
	@ExcelField(title="文档类别",dictType="d_wdfl",align=1, sort=25)
	private Integer wdlb;	//文档分类
	@Length(min=0, max=255)
	@ExcelField(title="文件名",align=1, sort=30)
	private String wjm;		//附件文件名--文件地址
	@Length(min=0, max=2000)
	@ExcelField(title="摘要",align=1, sort=50)
	private String zy;	//摘要
	@NotNull
	@Length(min=0, max=30)
	@ExcelField(title="关键词",align=1, sort=55)
	private String gjc;		//关键词
	private String bz;
	
	public EquipmentDocument() {
		super();
	}
	public EquipmentDocument(String id){
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
	public String getWdbm() {
		return wdbm;
	}
	public void setWdbm(String wdbm) {
		this.wdbm = wdbm;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
	public String getGjc() {
		return gjc;
	}
	public void setGjc(String gjc) {
		this.gjc = gjc;
	}
	public String getWdbt() {
		return wdbt;
	}
	public void setWdbt(String wdbt) {
		this.wdbt = wdbt;
	}
	public Integer getWdlb() {
		return wdlb;
	}
	public void setWdlb(Integer wdlb) {
		this.wdlb = wdlb;
	}
	public String getWjm() {
		return wjm;
	}
	public void setWjm(String wjm) {
		this.wjm = wjm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}


