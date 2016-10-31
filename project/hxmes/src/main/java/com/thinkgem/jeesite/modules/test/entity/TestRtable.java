/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 库房管理Entity
 * @author LiuBJ
 * @version 2016-07-20
 */
@Entity
@Table(name = "test_rtable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestRtable extends IdEntity<TestRtable> {
	
	private static final long serialVersionUID = 1L;
	private String room;		// 库房
	private String district;		// 库区
	private String shelves;		// 货架
	private String parentid;		// 父节点
	private String toolNo;		// 工器具编号
	private String name;		// 工器具名称
	private Integer toolNumber;		// 工器具数量
	private String toolState;		// 工器具状态

	public TestRtable() {
		super();
	}

	public TestRtable(String id){
		this();
		this.id = id;
	}
	 
	
	
	@Length(min=0, max=8, message="库房长度必须介于 0 和 8 之间")
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	
	@Length(min=0, max=8, message="库区长度必须介于 0 和 8 之间")
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Length(min=0, max=8, message="货架长度必须介于 0 和 8 之间")
	public String getShelves() {
		return shelves;
	}
	public void setShelves(String shelves) {
		this.shelves = shelves;
	}
	
	@Length(min=0, max=8, message="父节点长度必须介于 0 和 8 之间")
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=8, message="工器具编号长度必须介于 0 和 8 之间")
	public String getToolNo() {
		return toolNo;
	}
	public void setToolNo(String toolNo) {
		this.toolNo = toolNo;
	}
	
	@Length(min=0, max=64, message="工器具名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getToolNumber() {
		return toolNumber;
	}
	public void setToolNumber(Integer toolNumber) {
		this.toolNumber = toolNumber;
	}
	
	@Length(min=0, max=1, message="工器具状态长度必须介于 0 和 1 之间")
	public String getToolState() {
		return toolState;
	}
	public void setToolState(String toolState) {
		this.toolState = toolState;
	}
}


