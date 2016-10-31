/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;

/**
 * 代码生成器Entity
 * @author LiuBaoJ
 * @version 2016-05-23
 */
@Entity
@Table(name = "gen_table")
@DynamicInsert @DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GenTable extends IdEntity<GenTable> {
	
	private static final long serialVersionUID = 1L;
//	private String id; 		// 编号
	private String name; 	// 名称
	private String comments;		// 描述
	private String className;		// 实体类名称
	private String parentTable;		// 关联父表
	private GenTable parent;		// 父级编号
	private String nameLike; 	// 按名称模糊查询
	//private String parentIds; // 所有父级编号
	private String parentTableFk;		// 关联父表外键

	private List<String> pkList; // 当前表主键列表
	private List<GenTable> childList = Lists.newArrayList();	// 子表列表
	private List<GenTableColumn> columnList = Lists.newArrayList();	// 表列
	
	public GenTable() {
		super();
	}

	public GenTable(String id){
		this();
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	//@NotNull
	public GenTable getParent() {
		return parent;
	}

	public void setParent(GenTable parent) {
		this.parent = parent;
	}	 
	@Length(min=1, max=200)
	public String getName() {
		return StringUtils.lowerCase(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * 获取列名和说明
	 * @return
	 */
	@Transient
	public String getNameAndComments() {
		return getName() + (comments == null ? "" : "  :  " + comments);
	}
	
	@Transient
	public List<String> getPkList() {
		return pkList;
	}

	public void setPkList(List<String> pkList) {
		this.pkList = pkList;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentTable() {
		return StringUtils.lowerCase(parentTable);
	}

	public void setParentTable(String parentTable) {
		this.parentTable = parentTable;
	}

	public String getParentTableFk() {
		return StringUtils.lowerCase(parentTableFk);
	}

	public void setParentTableFk(String parentTableFk) {
		this.parentTableFk = parentTableFk;
	}

	@OneToMany(mappedBy = "parent", fetch=FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy(value="code") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<GenTable> getChildList() {
		return childList;
	}

	public void setChildList(List<GenTable> childList) {
		this.childList = childList;
	}

	@Transient
	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	@OneToMany(mappedBy = "genTable", fetch=FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy(value="id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<GenTableColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<GenTableColumn> columnList) {
		this.columnList = columnList;
	}

	
}


