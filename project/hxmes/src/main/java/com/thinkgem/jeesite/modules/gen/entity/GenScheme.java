/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;

/**
 * 代码生成器Entity
 * @author LiuBaoJ
 * @version 2016-05-19
 */
@Entity
@Table(name = "gen_scheme")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GenScheme extends IdEntity<GenScheme> {
	
	private static final long serialVersionUID = 1L;
	private String name; 	// 名称
	private String category;		// 分类
	private String packageName;		// 生成包路径
	private String moduleName;		// 生成模块名
	private String subModuleName;		// 生成子模块名
	private String functionName;		// 生成功能名
	private String functionNameSimple;		// 生成功能名（简写）
	private String functionAuthor;		// 生成功能作者
	//private GenTable genTable;		// 业务表名
	private String genTableId;		// 业务表名
	private String menuFlag;	    //菜单生成标记
	private String flag; 	// 0：保存方案； 1：保存方案并生成代码
	
	private Boolean replaceFile;	// 是否替换现有文件    0：不替换；1：替换文件

	public GenScheme() {
		super();
		menuFlag="0";
	}

	public GenScheme(String id){
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionNameSimple() {
		return functionNameSimple;
	}

	public void setFunctionNameSimple(String functionNameSimple) {
		this.functionNameSimple = functionNameSimple;
	}

	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}
	/**
	public GenTable getGenTable() {
		return genTable;
	}

	public void setGenTable(GenTable genTable) {
		this.genTable = genTable;
	}
**/
	public String getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}
	
	@Transient
	public String getFlag() {
		return flag;
	}

	public String getGenTableId() {
		return genTableId;
	}

	public void setGenTableId(String genTableId) {
		this.genTableId = genTableId;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Transient
	public Boolean getReplaceFile() {
		return replaceFile;
	}

	public void setReplaceFile(Boolean replaceFile) {
		this.replaceFile = replaceFile;
	}
	
}


