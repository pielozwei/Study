/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.entity${subModuleName_};

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.IdEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;


/**
 * ${functionName}Entity
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Entity
@Table(name = "${tableName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${ClassName3} extends IdEntity<${ClassName3}> {
	
	private static final long serialVersionUID = 1L;
	//private String id; 		// 编号
	private String name; 	// 名称
	private ${ClassName} ${className}; 	// 名称
	
	public ${ClassName3}() {
		super();
	}
	public ${ClassName3}(String id){
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
	@IndexColumn(name="${FKField}")
	@ManyToOne
	@JoinColumn(name="${FKField}")
	@NotFound(action = NotFoundAction.IGNORE)
	@IndexedEmbedded
	@ExcelField(title="设备规格型号编码",value="${className}.id", align=1, sort=20)
	public ${ClassName} get${ClassName}() {
		return ${className};
	}
	public void set${ClassName}(${ClassName} ${className}) {
		this.${className} = ${className};
	}
}


