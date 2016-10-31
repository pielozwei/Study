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
 * 服务商Entity
 * @author LiHR
 * @version 2016-07-06
 */
@Entity
@Table(name = "IP_sbfwgys")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EquipmentServiceProvider extends IdEntity<EquipmentServiceProvider> {
	
	private static final long serialVersionUID = 1L;
	
	private Equipment equipment; 	// 名称
	private Provider provider;
	private String ip_gys_mc;
	private Integer fwlx;//服务类型-d_fwlx	NUMBER	0	0	False
	private String lxr;//联系人	VARCHAR2	30	0	False
	private String lxdh;//联系电话	VARCHAR2	30	0	False
	private String bz;
	
	public EquipmentServiceProvider() {
		super();
	}
	public EquipmentServiceProvider(String id){
		this();
		this.id = id;
	}
	@IndexColumn(name="IP_SB_ID")
	@ManyToOne
	@JoinColumn(name="IP_SB_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="equipment.id", align=1, sort=20)
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	@IndexColumn(name="IP_GYS_ID")
	@ManyToOne
	@JoinColumn(name="IP_GYS_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="供应商_编码",value="provider.gysbm", align=1, sort=25)
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	@ExcelField(title="供应商_名称",value="provider.gysmc", align=1, sort=25)
	public String getIp_gys_mc() {
		return ip_gys_mc;
	}
	public void setIp_gys_mc(String ip_gys_mc) {
		this.ip_gys_mc = ip_gys_mc;
	}
	@ExcelField(title="服务类型",dictType="d_fwlx", align=1, sort=30)
	public Integer getFwlx() {
		return fwlx;
	}
	public void setFwlx(Integer fwlx) {
		this.fwlx = fwlx;
	}
	@Length(min=0,max=30)
	@ExcelField(title="联系人",align=1, sort=35)
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	@Length(min=0,max=30)
	@ExcelField(title="联系电话",align=1, sort=40)
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	@Length(min=0,max=100)
	@ExcelField(title="备注",align=1, sort=45)
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}


