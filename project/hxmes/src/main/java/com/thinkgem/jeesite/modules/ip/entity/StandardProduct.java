/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 标准产品Entity
 * @author lucl
 * @version 2016-08-16
 */
@Entity
@Table(name = "IP_CJBZCP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StandardProduct extends IdEntity<StandardProduct> {
	
	private static final long serialVersionUID = 1L;
	
	private String cpbh;     //产品编号
	private String cpmc;     //产品名称
	private String bzsj;     //标准时间
	private String bzpldw;    //标准批量单位
	public StandardProduct() {
		super();
	}

	public StandardProduct(String id){
		this();
		this.id = id;
	}

	public String getCpbh() {
		return cpbh;
	}

	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}

	public String getCpmc() {
		return cpmc;
	}

	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}

	public String getBzsj() {
		return bzsj;
	}

	public void setBzsj(String bzsj) {
		this.bzsj = bzsj;
	}

	public String getBzpldw() {
		return bzpldw;
	}

	public void setBzpldw(String bzpldw) {
		this.bzpldw = bzpldw;
	}
	
	
}


