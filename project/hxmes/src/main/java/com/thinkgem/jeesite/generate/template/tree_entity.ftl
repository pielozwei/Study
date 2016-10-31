/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.entity<#if subModuleName != "">.${subModuleName}</#if>;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

/**
 * ${functionName}Entity
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Entity
@Table(name = "${table.name}")
@DynamicInsert @DynamicUpdate
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

	public ${ClassName}() {
		super();
		this.id = id;
	}

	public ${ClassName}(String id){
		this();
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	public ${ClassName} getParent() {
		return parent;
	}

	public void setParent(${ClassName} parent) {
		this.parent = parent;
	}		 
	
	<#-- 生成get和set方法 -->
	<#list table.columnList as c>
	
		<#-- 如果不是基类属性 -->
		<#if c.isNotBaseField && c.simpleJavaField!="parent">
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
	@Transient
	public static void sortList(List<${ClassName}> list, List<${ClassName}> sourcelist, String parentId){
		for (int i=0; i<sourcelist.size(); i++){
			${ClassName} e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					${ClassName} child = sourcelist.get(j);
					if (child.getParent()!=null && child.getParent().getId()!=null
							&& child.getParent().getId().equals(e.getId())){
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}

	@Transient
	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	@Transient
	public static boolean isRoot(String id){
		return id != null && id.equals("1");
	}	
}
