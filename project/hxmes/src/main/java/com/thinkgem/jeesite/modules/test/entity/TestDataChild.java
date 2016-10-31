/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 子表参考用例Entity
 * @author LiuBJ
 * @version 2016-07-21
 */
@Entity
@Table(name = "test_data_child")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestDataChild extends IdEntity<TestDataChild> {
	
	private static final long serialVersionUID = 1L;
	//private String testDataMainId;
	private TestDataMain testDataMain;		// 业务主表 父类
	private String name;		// 名称

	public TestDataChild() {
		super();
	}

	public TestDataChild(String id){
		this();
		this.id = id;
	}
	 
	@ManyToOne
	@JoinColumn(name="test_data_main_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public TestDataMain getTestDataMain() {
		return testDataMain;
	}

	public void setTestDataMain(TestDataMain testDataMain) {
		this.testDataMain = testDataMain;
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*
	public String getTestDataMainId() {
		return testDataMainId;
	}

	public void setTestDataMainId(String testDataMainId) {
		this.testDataMainId = testDataMainId;
	}
*/
}


