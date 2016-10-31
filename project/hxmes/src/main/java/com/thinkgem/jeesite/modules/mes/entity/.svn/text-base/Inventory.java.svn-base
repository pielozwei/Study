/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.mes.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 仓库Entity
 * @author LiuBaoJ
 * @version 2016-05-19
 */
@Entity
@Table(name = "mes_inventory")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inventory extends IdEntity<Inventory> {
	
	private static final long serialVersionUID = 1L;
	private String id; 		// 编号
	private String name; 	// 名称

	public Inventory() {
		super();
	}

	public Inventory(String id){
		this();
		this.id = id;
	}
	 
	@Length(min=1, max=200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}


