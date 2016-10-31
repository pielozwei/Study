/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.entity${subModuleName_};

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
public class ${ClassName} extends IdEntity<${ClassName}> {
	
	private static final long serialVersionUID = 1L;
	//private String id; 		// 编号
	private String name; 	// 名称
	private ${ClassName} parent; //上级id 不为空
	public ${ClassName}() {
		super();
	}
	public ${ClassName}(String id){
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="${upFiled}")
	@NotFound(action = NotFoundAction.IGNORE)
	@NotNull
	@ExcelField(title="父级id",value="parent.id", align=1, sort=20)
	public ${ClassName} getParent() {
		return parent;
	}
	public void setParent(${ClassName} parent) {
		this.parent = parent;
	}
}


