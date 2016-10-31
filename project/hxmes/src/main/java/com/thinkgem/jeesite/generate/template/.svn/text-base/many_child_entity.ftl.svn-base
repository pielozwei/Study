/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.entity<#if subModuleName != "">.${subModuleName}</#if>;

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
 * ${functionName}Entity
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Entity
@Table(name = "${table_child.name}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${ClassName_child} extends IdEntity<${ClassName_child}> {
	
	private static final long serialVersionUID = 1L;
	<#-- 生成字段属性 -->
	private ${ClassName} ${className};		// 业务主表 父类
	<#list table_child.columnList as c>
		<#-- 如果不是基类属性 -->
		<#if c.isNotBaseField && c.simpleJavaField!=parentTableFk>
	private ${c.simpleJavaType} ${c.simpleJavaField};		<#if c.comments??>// ${c.comments}</#if>
		</#if>
	</#list>
	<#-- 范围条件字段 -->
	<#list table_child.columnList as c>
		<#if c.isQuery?? && c.isQuery == "1" && c.queryType == "between">
	private ${c.simpleJavaType} begin${c.simpleJavaField?cap_first};		<#if c.comments??>// 开始 ${c.comments}</#if>
	private ${c.simpleJavaType} end${c.simpleJavaField?cap_first};		<#if c.comments??>// 结束 ${c.comments}</#if>
		</#if>
	</#list>

	public ${ClassName_child}() {
		super();
	}

	public ${ClassName_child}(String id){
		this();
		this.id = id;
	}
	 
	<#-- 生成get和set方法 -->
	@ManyToOne
	@JoinColumn(name="${parent_table_fk}")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public ${ClassName} get${ClassName}() {
		return ${className};
	}
	public void set${ClassName}(${ClassName} ${className}) {
		this.${className} = ${className};
	}	
	<#list table_child.columnList as c>	
		<#-- 如果不是基类属性 -->
		<#if c.isNotBaseField && c.simpleJavaField!=parentTableFk>
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

	<#-- 范围条件字段get和set方法 -->
	<#list table_child.columnList as c>
		<#if c.isQuery?? && c.isQuery == "1" && c.queryType == "between">
	public ${c.simpleJavaType} getBegin${c.simpleJavaField?cap_first}() {
		return begin${c.simpleJavaField?cap_first};
	}

	public void setBegin${c.simpleJavaField?cap_first}(${c.simpleJavaType} begin${c.simpleJavaField?cap_first}) {
		this.begin${c.simpleJavaField?cap_first} = begin${c.simpleJavaField?cap_first};
	}d
	
	public ${c.simpleJavaType} getEnd${c.simpleJavaField?cap_first}() {
		return end${c.simpleJavaField?cap_first};
	}

	public void setEnd${c.simpleJavaField?cap_first}(${c.simpleJavaType} end${c.simpleJavaField?cap_first}) {
		this.end${c.simpleJavaField?cap_first} = end${c.simpleJavaField?cap_first};
	}
		
		</#if>
	</#list>	
}


