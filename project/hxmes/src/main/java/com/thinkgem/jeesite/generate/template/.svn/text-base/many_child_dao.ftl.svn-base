/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.dao<#if subModuleName != "">.${subModuleName}</#if>;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import ${packageName}.${moduleName}.entity${subModuleName}.${ClassName_child};

/**
 * ${functionName}DAO接口
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Repository
public class ${ClassName_child}Dao extends BaseDao<${ClassName_child}> {

	public List<${ClassName_child}> findList(String mainId){
		return find("from ${ClassName_child} where delFlag = :p1 and ${className}.id=:p2", new Parameter(${ClassName_child}.DEL_FLAG_NORMAL,mainId));
	}
	
}
