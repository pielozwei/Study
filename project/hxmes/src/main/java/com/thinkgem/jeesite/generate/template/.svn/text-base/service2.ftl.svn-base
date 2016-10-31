/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${packageName}.${moduleName}.service${subModuleName_};

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.${moduleName}.entity${subModuleName_}.${ClassName};
import com.thinkgem.jeesite.modules.${moduleName}.dao${subModuleName_}.${ClassName}Dao;
import com.thinkgem.jeesite.modules.${moduleName}.entity${subModuleName_}.${ClassName2};
import com.thinkgem.jeesite.modules.${moduleName}.dao${subModuleName_}.${ClassName2}Dao;

/**
 * ${functionName}Controller
 * @author ${classAuthor}
 * @version ${classVersion}
 */
@Component
@Transactional(readOnly = true)
public class ${ClassName}Service extends BaseService {

	@Autowired
	private ${ClassName}Dao ${className}Dao;
	@Autowired
	private ${ClassName2}Dao ${className2}Dao;
	
	public ${ClassName} get(String id) {
		return ${className}Dao.get(id);
	}
	
	public Page<${ClassName}> find(Page<${ClassName}> page, ${ClassName} ${className}, String nodeId) {
		DetachedCriteria dc = ${className}Dao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(${className}.getName())){
			dc.add(Restrictions.like("name", "%"+${className}.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("parent.id", nodeId));
		}
		dc.add(Restrictions.eq(${ClassName}.FIELD_DEL_FLAG, ${ClassName}.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return ${className}Dao.find(page, dc);
	}
	
	/*只找子节点*/
	public List<${ClassName}> findSun(String nodeId){
		List<${ClassName}> list=new ArrayList<${ClassName}>();
		DetachedCriteria dc = ${className}Dao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", ${ClassName}.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("parent.id", nodeId));
		}else return list;
		list = ${className}Dao.find(dc);
		return list;
	}
	
	/*只找关联信息*/
	public List<${ClassName2}> findInfo(String nodeId){
		List<${ClassName2}> list=new ArrayList<${ClassName2}>();
		DetachedCriteria dc = ${className2}Dao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", ${ClassName2}.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("${className}.id", nodeId));
		}else return list;
		list = ${className2}Dao.find(dc);
		return list;
	}
	
	@Transactional(readOnly = false)
	public void save(${ClassName} ${className}) {
		${className}Dao.clear();
		${className}Dao.save(${className});
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		${className}Dao.deleteById(id);
	}
	
	public List<${ClassName}> findTree(String module) {
		DetachedCriteria dc = ${className}Dao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", ${ClassName}.DEL_FLAG_NORMAL));
		List<${ClassName}> list = ${className}Dao.find(dc);
		// 将没有父节点的节点，找到父节点
		Set<String> parentIdSet = Sets.newHashSet();
		for (${ClassName} e : list){
			if (e.getParent()!=null && StringUtils.isNotBlank(e.getParent().getId())){
				boolean isExistParent = false;
				for (${ClassName} e2 : list){
					if (e.getParent().getId().equals(e2.getId())){
						isExistParent = true;
						break;
					}
				}
				if (!isExistParent){
					parentIdSet.add(e.getParent().getId());
				}
			}
		}
		if (parentIdSet.size() > 0){
			dc = ${className}Dao.createDetachedCriteria();
			dc.add(Restrictions.in("id", parentIdSet));
			//dc.add(Restrictions.eq("disabled", false));
			//dc.addOrder(Order.asc("xh"));
			dc.add(Restrictions.eq("delFlag", ${ClassName}.DEL_FLAG_NORMAL));
			list.addAll(0, ${className}Dao.find(dc));
		}
		//UserUtils.putCache(CACHE_TOOLCATEGORY_LIST, list);
		
		return list;
	}
	
	public List<Map<String, Object>> getTreeData() {
		List<${ClassName}> list = findTree(null);
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(${ClassName} ${className} : list){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", ${className}.getId());
			${ClassName} parent=${className}.getParent();
			map.put("pId",parent==null?'0':parent.getId());
			map.put("name", ${className}.getName());
			map.put("module", "${className}");
			listMap.add(map);
		}
		return listMap;
	}
	
	public List<${ClassName}> findAll() {
		return ${className}Dao.find(${className}Dao.createDetachedCriteria());
	}
}
