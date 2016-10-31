/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 主子表Entity
 * @author LiuBJ
 * @version 2016-07-28
 */
@Entity
@Table(name = "test_main")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TestMain extends IdEntity<TestMain> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String sex;		// 性别
	private Date inDate;		// 加入日期
	private List<TestChild> testChildList = Lists.newArrayList();		// 子表列表

	public TestMain() {
		super();
	}

	public TestMain(String id){
		this();
		this.id = id;
	}
	 
	
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	
	
	
	
	
	@OneToMany(mappedBy = "testMain", fetch=FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy(value="id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<TestChild> getTestChildList() {
		return testChildList;
	}
	public void setTestChildList(List<TestChild> testChildList) {
		this.testChildList = testChildList;
	}
}


