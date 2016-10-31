/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 主子表Entity
 * @author LiuBJ
 * @version 2016-07-28
 */
@Entity
@Table(name = "test_child")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestChild extends IdEntity<TestChild> {
	
	private static final long serialVersionUID = 1L;
	private TestMain testMain;		// 业务主表 父类
	private String name;		// 名称

	public TestChild() {
		super();
	}

	public TestChild(String id){
		this();
		this.id = id;
	}
	 
	@ManyToOne
	@JoinColumn(name="test_main_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public TestMain getTestMain() {
		return testMain;
	}
	public void setTestMain(TestMain testMain) {
		this.testMain = testMain;
	}	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}


