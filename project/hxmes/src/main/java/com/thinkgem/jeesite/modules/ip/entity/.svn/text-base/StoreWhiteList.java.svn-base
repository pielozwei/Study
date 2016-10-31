/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 仓库白名单Entity
 * @author Lucl
 * @version 2016-06-15
 */
@Entity
@Table(name = "ip_ckbmd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StoreWhiteList extends IdEntity<StoreWhiteList> {
	
	private static final long serialVersionUID = 1L;
	private String name; 	// 名称
	private Material ipWlbmWlbm;   //物料编码
	private Store ipCkCkbh;    //仓库编号
	private Integer zdccts;    //最大存储天数
	private Integer aqkc;    //安全库存
	private Integer zdkcxe;    //最大库存限额
	private Integer xhpdjg;    //循环盘点间隔
	private Date gydxsj;    //供应对象时间
	private Integer xhpdjgsjdw;   //循环盘点间隔时间单位
	
	public StoreWhiteList() {
		super();
	}

	public StoreWhiteList(String id){
		this();
		this.id = id;
	}
	
	@Transient
	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	@Transient
	public static boolean isRoot(String id){
		return id != null && id.equals("0");
	}
	 
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public Material getIpWlbmWlbm() {
		return ipWlbmWlbm;
	}
	
	public void setIpWlbmWlbm(Material ipWlbmWlbm) {
		this.ipWlbmWlbm = ipWlbmWlbm;
	}
	@NotNull
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	public Store getIpCkCkbh() {
		return ipCkCkbh;
	}

	public void setIpCkCkbh(Store ipCkCkbh) {
		this.ipCkCkbh = ipCkCkbh;
	}

	public Integer getZdccts() {
		return zdccts;
	}

	public void setZdccts(Integer zdccts) {
		this.zdccts = zdccts;
	}

	public Integer getAqkc() {
		return aqkc;
	}

	public void setAqkc(Integer aqkc) {
		this.aqkc = aqkc;
	}

	public Integer getZdkcxe() {
		return zdkcxe;
	}

	public void setZdkcxe(Integer zdkcxe) {
		this.zdkcxe = zdkcxe;
	}

	public Integer getXhpdjg() {
		return xhpdjg;
	}

	public void setXhpdjg(Integer xhpdjg) {
		this.xhpdjg = xhpdjg;
	}

	public Date getGydxsj() {
		return gydxsj;
	}

	public void setGydxsj(Date gydxsj) {
		this.gydxsj = gydxsj;
	}
	
	public Integer getXhpdjgsjdw() {
		return xhpdjgsjdw;
	}

	public void setXhpdjgsjdw(Integer xhpdjgsjdw) {
		this.xhpdjgsjdw = xhpdjgsjdw;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}


