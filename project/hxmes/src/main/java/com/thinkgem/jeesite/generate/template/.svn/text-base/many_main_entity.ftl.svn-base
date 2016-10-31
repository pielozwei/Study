/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.entity<#if subModuleName != "">.${subModuleName}</#if>;

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
 * ${functionName}Entity
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Entity
@Table(name = "${table.name}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${ClassName} extends IdEntity<${ClassName}> {
	
	private static final long serialVersionUID = 1L;
	<#-- 生成字段属性 -->
	<#list table.columnList as c>
		<#-- 如果不是基类属性 -->
		<#if c.isNotBaseField>
	private ${c.simpleJavaType} ${c.simpleJavaField};		<#if c.comments??>// ${c.comments}</#if>
		</#if>
	</#list>
	<#-- 范围条件字段 -->
	<#list table.columnList as c>
		<#if c.isQuery?? && c.isQuery == "1" && c.queryType == "between">
	private ${c.simpleJavaType} begin${c.simpleJavaField?cap_first};		<#if c.comments??>// 开始 ${c.comments}</#if>
	private ${c.simpleJavaType} end${c.simpleJavaField?cap_first};		<#if c.comments??>// 结束 ${c.comments}</#if>
		</#if>
	</#list>
	<#-- 子表列表字段 -->
	private List<${ClassName_child}> ${className_child}List = Lists.newArrayList();		// 子表列表

	<#-- 构造方法 -->
	public ${ClassName}() {
		super();
	}

	public ${ClassName}(String id){
		this();
		this.id = id;
	}
	 
	<#-- 生成get和set方法 -->
	<#list table.columnList as c>
	
		<#-- 如果不是基类属性 -->
		<#if c.isNotBaseField>
			<#list c.simpleAnnotationList as a>
	@${a}
			</#list>			
	public ${c.simpleJavaType} get${c.simpleJavaField?cap_first}() {
		return ${c.simpleJavaField};
	}
	public void set${c.simpleJavaField?cap_first}(${c.simpleJavaType} ${c.simpleJavaField}) {
		this.${c.simpleJavaField} = ${c.simpleJavaField};
	}
		</#if>
	</#list>
	@OneToMany(mappedBy = "${className}", fetch=FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy(value="id") @Fetch(FetchMode.SUBSELECT)
	@NotFound(action = NotFoundAction.IGNORE)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<${ClassName_child}> get${ClassName_child}List() {
		return ${className_child}List;
	}
	public void set${ClassName_child}List(List<${ClassName_child}> ${className_child}List) {
		this.${className_child}List = ${className_child}List;
	}
	<#-- 范围条件字段get和set方法 -->
	<#list table.columnList as c>
		<#if c.isQuery?? && c.isQuery == "1" && c.queryType == "between">
	public ${c.simpleJavaType} getBegin${c.simpleJavaField?cap_first}() {
		return begin${c.simpleJavaField?cap_first};
	}

	public void setBegin${c.simpleJavaField?cap_first}(${c.simpleJavaType} begin${c.simpleJavaField?cap_first}) {
		this.begin${c.simpleJavaField?cap_first} = begin${c.simpleJavaField?cap_first};
	}
	
	public ${c.simpleJavaType} getEnd${c.simpleJavaField?cap_first}() {
		return end${c.simpleJavaField?cap_first};
	}

	public void setEnd${c.simpleJavaField?cap_first}(${c.simpleJavaType} end${c.simpleJavaField?cap_first}) {
		this.end${c.simpleJavaField?cap_first} = end${c.simpleJavaField?cap_first};
	}
		
		</#if>
	</#list>	
}


