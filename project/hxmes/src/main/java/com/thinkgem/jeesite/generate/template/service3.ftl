/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.service${subModuleName_};

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.${moduleName}.entity${subModuleName_}.${ClassName3};
import com.thinkgem.jeesite.modules.${moduleName}.dao${subModuleName_}.${ClassName}Dao;
import com.thinkgem.jeesite.modules.${moduleName}.dao${subModuleName_}.${ClassName3}Dao;

/**
 * ${functionName}Controller
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Component
@Transactional(readOnly = true)
public class ${ClassName3}Service extends BaseService {

	@Autowired
	private ${ClassName3}Dao ${className3}Dao;
	@Autowired
	private ${ClassName}Dao ${className}Dao;
	public ${ClassName3} get(String id) {
		return ${className3}Dao.get(id);
	}
	
	public Page<${ClassName3}> find(Page<${ClassName3}> page, ${ClassName3} ${className3},String ${className}Id) {
		DetachedCriteria dc = ${className3}Dao.createDetachedCriteria();
		dc.createAlias("${className}", "${className}");
		if (StringUtils.isNotEmpty(${className}Id)){
			dc.add(Restrictions.eq("${className}.id", ${className}Id));
		}
		if (StringUtils.isNotEmpty(${className3}.getName())){
			dc.add(Restrictions.like("name", "%"+${className3}.getName()+"%"));
		}
		dc.add(Restrictions.eq(${ClassName3}.FIELD_DEL_FLAG, ${ClassName3}.DEL_FLAG_NORMAL));
		//dc.addOrder(Order.desc("csdm"));
		return ${className3}Dao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(${ClassName3} ${className3}) {
		${className3}Dao.save(${className3});
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		${className3}Dao.deleteById(id);
	}
	
	public List<${ClassName3}> findBy${ClassName}Id(String ${className}Id) {
		DetachedCriteria dc = ${className3}Dao.createDetachedCriteria();
		dc.add(Restrictions.eq(${ClassName3}.FIELD_DEL_FLAG, ${ClassName3}.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("${className}.id", ${className}Id));
		return ${className3}Dao.find(dc);
	}
	public List<${ClassName3}> findAll() {
		return ${className3}Dao.find(${className3}Dao.createDetachedCriteria());
	}
}
