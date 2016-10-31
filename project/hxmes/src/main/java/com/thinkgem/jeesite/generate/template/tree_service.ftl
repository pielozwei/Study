/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.service<#if subModuleName != "">.${subModuleName}</#if>;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.Order;

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
	
	public ${ClassName} get${ClassName}(String id) {
		return ${className}Dao.get(id);
	}
	
	public List<${ClassName}> findAll${ClassName}(){
		return ${className}Dao.findAllList();
	}
	
	public Page<${ClassName}> find(Page<${ClassName}> page, ${ClassName} ${className}) {
		DetachedCriteria dc = ${className}Dao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(${className}.getName())){
			dc.add(Restrictions.like("name", "%"+${className}.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(${className}.getId())){
			dc.add(Restrictions.or(Restrictions.like("parentIds","%"+${className}.getId()+"%"),Restrictions.eqOrIsNull("id", ${className}.getId())));
		}
		
		dc.add(Restrictions.eq(${ClassName}.FIELD_DEL_FLAG, ${ClassName}.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return ${className}Dao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save${ClassName}(${ClassName} ${className}) {
		${className}.setParent(this.get${ClassName}(${className}.getParent().getId()));
		String oldParentIds = ${className}.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		${className}.setParentIds(${className}.getParent().getParentIds()+${className}.getParent().getId()+",");
		${className}Dao.clear();
		${className}Dao.save(${className});
		// 更新子节点 parentIds
		List<${ClassName}> list = ${className}Dao.findByParentIdsLike("%,"+${className}.getId()+",%");
		for (${ClassName} e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, ${className}.getParentIds()));
		}
		${className}Dao.save(list);
	}

	@Transactional(readOnly = false)
	public void delete${ClassName}(String id) {
		${className}Dao.deleteById(id, "%,"+id+",%");
	}
}
