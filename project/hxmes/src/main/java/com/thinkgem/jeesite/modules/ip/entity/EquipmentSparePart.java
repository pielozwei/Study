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
 * 备品备件Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SBBJ")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentSparePart extends IdEntity<EquipmentSparePart> {
	
	private static final long serialVersionUID = 1L;
	
	private EquipmentModel equipmentModel; 	// 名称
	private String bpbjfj;
	@ExcelField(title = "备件物料编码", align = 1, sort = 30)
	private Material bjwlbm; // 备件物料编码 最大长度30 不为空 ---可当作备件编码用
	@ExcelField(title="数量",align=1, sort=50)
	
	private Integer sl;		//数量
	
	private String bz;
	public EquipmentSparePart() {
		super();
	}
	public EquipmentSparePart(String id){
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


	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	@ManyToOne
	@JoinColumn(name="BPBJWL_ID")
	@IndexedEmbedded
	@ExcelField(title="备品备件名称",value="bjwlbm.id", align=1, sort=20)
	public Material getBjwlbm() {
		return bjwlbm;
	}
	public void setBjwlbm(Material bjwlbm) {
		this.bjwlbm = bjwlbm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getBpbjfj() {
		return bpbjfj;
	}
	public void setBpbjfj(String bpbjfj) {
		this.bpbjfj = bpbjfj;
	}
	
}


