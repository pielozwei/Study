/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.dao<#if subModuleName != "">.${subModuleName}</#if>;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import ${packageName}.${moduleName}.entity<#if subModuleName != "">.${subModuleName}</#if>.${ClassName};

/**
 * ${functionName}DAO接口
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Repository
public class ${ClassName}Dao extends BaseDao<${ClassName}> {

	public List<${ClassName}> findByParentIdsLike(String parentIds){
		return find("from ${ClassName} where parentIds like :p1", new Parameter(parentIds));
	}

	public List<${ClassName}> findAllList(){
		return find("from ${ClassName} where delFlag=:p1", new Parameter(Dict.DEL_FLAG_NORMAL));
	}	
}
