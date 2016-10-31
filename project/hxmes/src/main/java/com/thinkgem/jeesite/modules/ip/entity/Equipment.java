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
 * 基本信息Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_SB")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Equipment extends IdEntity<Equipment> {
	
	private static final long serialVersionUID = 1L;
	private EquipmentField equipmentField; 	// 设备层次id
	
	private String sbbm; 				// 编号
	private EquipmentModel equipmentModel;			// 设备规格型号编码		--非空
	private Integer sbsclx;			// 设备生产类型    			--非空
	private Integer jzabcfl;			// 价值ABC分类		 	--非空
	private Integer sbdj; 				// 设备等级			--非空
	private String sybm;			// 使用部门
//	private Integer dqyxzt;			// 当前运行状态			--非空
//	private Integer dqjszt;			// 当前技术状态			--非空		
	private String ly;				// 来源
	private String azqywz;			// 安装区域位置
//	private Integer dqjdzt;				// 当前鉴定状态			--非空
	private Date lkrq;				// 立卡日期
	private Integer synx;				// 使用年限
	private String ccbh;			// 出厂编号
	private Date csccrq;			// 出生产厂日期
	private Date jbcrq; 			// 进本厂日期
	private Date azrq;				// 安装日期
	private Date tcrq;				// 投产日期
	private Integer sbyz;				// 设备原值
	private Integer nzjl;				// 年折旧率
	private Date bfrq;				// 报废日期
	
	private Integer smzqzt;     //生命周期异常
	private Integer hgxjdzt;    //合格性鉴定状态
	private Integer whxjdzt;    //完好性鉴定状态
	private Integer xssx;    //显示顺序
	private Integer sfqy;    //是否启用
	private String bz;    //备注
	
	public Equipment() {
		super();
	}
	public Equipment(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="IP_SBCC_ID")
	@ManyToOne
	@JoinColumn(name="IP_SBCC_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="equipmentField.id", align=1, sort=20)
	public EquipmentField getEquipmentField() {
		return equipmentField;
	}
	public void setEquipmentField(EquipmentField equipmentField) {
		this.equipmentField = equipmentField;
	}
	@NotNull
	@Length(min=1,max=100)
	@ExcelField(title="编码",align=1, sort=25)
	public String getSbbm() {
		return sbbm;
	}
	public void setSbbm(String sbbm) {
		this.sbbm = sbbm;
	}
	@ManyToOne(targetEntity = EquipmentModel.class)
    @JoinColumn(name = "IP_SBGGXH_ID", referencedColumnName = "id")
	@NotNull
	public EquipmentModel getEquipmentModel() {
		return equipmentModel;
	}
	public void setEquipmentModel(EquipmentModel equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	@NotNull
	@ExcelField(title="设备生产类型", align=1, sort=40)
	public Integer getSbsclx() {
		return sbsclx;
	}
	public void setSbsclx(Integer sbsclx) {
		this.sbsclx = sbsclx;
	}
	@NotNull
	@ExcelField(title="价值ABC分类", align=1, sort=45)
	public Integer getJzabcfl() {
		return jzabcfl;
	}
	public void setJzabcfl(Integer jzabcfl) {
		this.jzabcfl = jzabcfl;
	}
	@NotNull
	@ExcelField(title="设备等级", align=1, sort=50)
	public Integer getSbdj() {
		return sbdj;
	}
	public void setSbdj(Integer sbdj) {
		this.sbdj = sbdj;
	}
	@Length(min=0,max=100)
	@ExcelField(title="使用部门", align=1, sort=55)
	public String getSybm() {
		return sybm;
	}
	public void setSybm(String sybm) {
		this.sybm = sybm;
	}
	@Length(min=0,max=30)
	@ExcelField(title="来源", align=1, sort=75)
	public String getLy() {
		return ly;
	}
	public void setLy(String ly) {
		this.ly = ly;
	}
	@Length(min=0,max=100)
	@ExcelField(title="安装区域位置", align=1, sort=80)
	public String getAzqywz() {
		return azqywz;
	}
	public void setAzqywz(String azqywz) {
		this.azqywz = azqywz;
	}
	@ExcelField(title="立卡日期", align=1, sort=85)
	public Date getLkrq() {
		return lkrq;
	}
	public void setLkrq(Date lkrq) {
		this.lkrq = lkrq;
	}
	@ExcelField(title="使用年限", align=1, sort=90)
	public Integer getSynx() {
		return synx;
	}
	public void setSynx(Integer synx) {
		this.synx = synx;
	}
	@Length(min=0,max=100)
	@ExcelField(title="出厂编号", align=1, sort=95)
	public String getCcbh() {
		return ccbh;
	}
	public void setCcbh(String ccbh) {
		this.ccbh = ccbh;
	}
	@ExcelField(title="出生产厂日期", align=1, sort=120)
	public Date getCsccrq() {
		return csccrq;
	}
	public void setCsccrq(Date csccrq) {
		this.csccrq = csccrq;
	}
	@ExcelField(title="进本厂日期", align=1, sort=125)
	public Date getJbcrq() {
		return jbcrq;
	}
	public void setJbcrq(Date jbcrq) {
		this.jbcrq = jbcrq;
	}
	@ExcelField(title="安装日期", align=1, sort=130)
	public Date getAzrq() {
		return azrq;
	}
	public void setAzrq(Date azrq) {
		this.azrq = azrq;
	}
	@ExcelField(title="投产日期", align=1, sort=135)
	public Date getTcrq() {
		return tcrq;
	}
	public void setTcrq(Date tcrq) {
		this.tcrq = tcrq;
	}
	@ExcelField(title="设备原值", align=1, sort=140)
	public Integer getSbyz() {
		return sbyz;
	}
	public void setSbyz(Integer sbyz) {
		this.sbyz = sbyz;
	}
	@ExcelField(title="年折旧率", align=1, sort=145)
	public Integer getNzjl() {
		return nzjl;
	}
	public void setNzjl(Integer nzjl) {
		this.nzjl = nzjl;
	}
	@ExcelField(title="报废日期", align=1, sort=150)
	public Date getBfrq() {
		return bfrq;
	}
	public void setBfrq(Date bfrq) {
		this.bfrq = bfrq;
	}
	public Integer getSmzqzt() {
		return smzqzt;
	}
	public void setSmzqzt(Integer smzqzt) {
		this.smzqzt = smzqzt;
	}
	public Integer getHgxjdzt() {
		return hgxjdzt;
	}
	public void setHgxjdzt(Integer hgxjdzt) {
		this.hgxjdzt = hgxjdzt;
	}
	public Integer getWhxjdzt() {
		return whxjdzt;
	}
	public void setWhxjdzt(Integer whxjdzt) {
		this.whxjdzt = whxjdzt;
	}
	public Integer getXssx() {
		return xssx;
	}
	public void setXssx(Integer xssx) {
		this.xssx = xssx;
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
}


