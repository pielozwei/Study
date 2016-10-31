/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.service<#if subModuleName != "">.${subModuleName}</#if>;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import ${packageName}.${moduleName}.entity<#if subModuleName != "">.${subModuleName}</#if>.${ClassName};
import ${packageName}.${moduleName}.dao<#if subModuleName != "">.${subModuleName}</#if>.${ClassName}Dao;

/**
 * ${functionName}Service
 * @author ${functionAuthor}
 * @version ${functionVersion}
 */
@Component
@Transactional(readOnly = true)
public class ${ClassName}Service extends BaseService {

	@Autowired
	private ${ClassName}Dao ${className}Dao;
	
	public ${ClassName} get(String id) {
		return ${className}Dao.get(id);
	}
	
	public Page<${ClassName}> find(Page<${ClassName}> page, ${ClassName} ${className}) {
		DetachedCriteria dc = ${className}Dao.createDetachedCriteria();
		<#list table.columnList as c>
			<#if c.isQuery?? && c.isQuery == "1">
				<#if c.queryType == "like">
		if (StringUtils.isNotEmpty(${className}.get${c.simpleJavaField?cap_first}())){
			dc.add(Restrictions.like("${c.simpleJavaField}", "%"+${className}.get${c.simpleJavaField?cap_first}()+"%"));
		}
				</#if>				
				<#if c.queryType == "=">
		if (StringUtils.isNotEmpty(${className}.get${c.simpleJavaField?cap_first}())){
			dc.add(Restrictions.eq("${c.simpleJavaField}", ${className}.get${c.simpleJavaField?cap_first}()));
		}
				</#if>				
			</#if>
		</#list>
		
		dc.add(Restrictions.eq(${ClassName}.FIELD_DEL_FLAG, ${ClassName}.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return ${className}Dao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(${ClassName} ${className}) {
		${className}Dao.save(${className});
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		${className}Dao.deleteById(id);
	}
	
}
