/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.dao<#if subModuleName != "">.${subModuleName}</#if>;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import ${packageName}.${moduleName}.entity${subModuleName}.${ClassName};

/**
 * ${functionName}DAO接口
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Repository
public class ${ClassName}Dao extends BaseDao<${ClassName}> {

	public List<${ClassName}> findList(){
		return find("from ${ClassName} where delFlag = :p1 ", new Parameter(${ClassName}.DEL_FLAG_NORMAL));
	}
	
}
