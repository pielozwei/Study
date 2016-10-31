/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

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
import com.thinkgem.jeesite.modules.ip.entity.Store;
import com.thinkgem.jeesite.modules.ip.entity.ToolCategory;
import com.thinkgem.jeesite.modules.ip.dao.ToolCategoryDao;
import com.thinkgem.jeesite.modules.ip.entity.Tool;
import com.thinkgem.jeesite.modules.ip.dao.ToolDao;

/**
 * 工器具分类Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class ToolCategoryService extends BaseService {

	@Autowired
	private ToolCategoryDao toolCategoryDao;
	@Autowired
	private ToolDao toolDao;
	
	public ToolCategory get(String id) {
		return toolCategoryDao.get(id);
	}
	
	public Page<ToolCategory> find(Page<ToolCategory> page, ToolCategory toolCategory, String nodeId) {
		DetachedCriteria dc = toolCategoryDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(toolCategory.getLbmc())){
			dc.add(Restrictions.like("lbmc", "%"+toolCategory.getLbmc()+"%"));
		}
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("parent.id", nodeId));
		}
		dc.add(Restrictions.eq(ToolCategory.FIELD_DEL_FLAG, ToolCategory.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return toolCategoryDao.find(page, dc);
	}
	
	/*只找子节点*/
	public List<ToolCategory> findSun(String nodeId){
		List<ToolCategory> list=new ArrayList<ToolCategory>();
		DetachedCriteria dc = toolCategoryDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", ToolCategory.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("parent.id", nodeId));
		}else return list;
		list = toolCategoryDao.find(dc);
		return list;
	}
	
	/*只找关联信息*/
	public List<Tool> findInfo(String nodeId){
		List<Tool> list=new ArrayList<Tool>();
		DetachedCriteria dc = toolDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", Tool.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("toolCategory.id", nodeId));
		}else return list;
		list = toolDao.find(dc);
		return list;
	}
	
	@Transactional(readOnly = false)
	public void save(ToolCategory toolCategory) {
		toolCategoryDao.clear();
		toolCategoryDao.save(toolCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//toolCategoryDao.deleteById(id);
		ToolCategory toolCategory=toolCategoryDao.get(id);
		toolCategoryDao.getSession().delete(toolCategory);
	}
	
	public List<ToolCategory> findTree(String module) {
		DetachedCriteria dc = toolCategoryDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", ToolCategory.DEL_FLAG_NORMAL));
		List<ToolCategory> list = toolCategoryDao.find(dc);
		// 将没有父节点的节点，找到父节点
		Set<String> parentIdSet = Sets.newHashSet();
		for (ToolCategory e : list){
			if (e.getParent()!=null && StringUtils.isNotBlank(e.getParent().getId())){
				boolean isExistParent = false;
				for (ToolCategory e2 : list){
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
			dc = toolCategoryDao.createDetachedCriteria();
			dc.add(Restrictions.in("id", parentIdSet));
			//dc.add(Restrictions.eq("disabled", false));
			//dc.addOrder(Order.asc("xh"));
			dc.add(Restrictions.eq("delFlag", ToolCategory.DEL_FLAG_NORMAL));
			list.addAll(0, toolCategoryDao.find(dc));
		}
		//UserUtils.putCache(CACHE_TOOLCATEGORY_LIST, list);
		
		return list;
	}
	
	public List<Map<String, Object>> getTreeData() {
		List<ToolCategory> list = findTree(null);
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(ToolCategory toolCategory : list){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", toolCategory.getId());
			ToolCategory parent=toolCategory.getParent();
			map.put("pId",parent==null?'0':parent.getId());
			map.put("name", toolCategory.getLbmc());
			map.put("module", "toolCategory");
			listMap.add(map);
		}
		return listMap;
	}
	
	public List<ToolCategory> findAll() {
		return toolCategoryDao.find(toolCategoryDao.createDetachedCriteria());
	}

	public void updateStateList(String id, String state) {
		ToolCategory toolCategory=toolCategoryDao.get(id);
		toolCategory.setSfqy(Integer.parseInt(state));
		toolCategoryDao.getSession().save(toolCategory);
		
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_gzqjlb where lbbh ='"+val1+"'";
			List<ToolCategory> list=toolCategoryDao.getSession().createSQLQuery(sql).addEntity(ToolCategory.class).list();
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
}
