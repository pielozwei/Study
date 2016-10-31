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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentField;
import com.thinkgem.jeesite.modules.ip.entity.ToolCategory;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentFieldDao;
import com.thinkgem.jeesite.modules.ip.entity.Equipment;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDao;

/**
 * 设备层次Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentFieldService extends BaseService {

	@Autowired
	private EquipmentFieldDao equipmentFieldDao;
	@Autowired
	private EquipmentDao equipmentDao;
	
	public EquipmentField get(String id) {
		return equipmentFieldDao.get(id);
	}
	
	public Page<EquipmentField> find(Page<EquipmentField> page, EquipmentField equipmentField, String nodeId) {
		DetachedCriteria dc = equipmentFieldDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(equipmentField.getCcmc())){
			dc.add(Restrictions.like("ccmc", "%"+equipmentField.getCcmc()+"%"));
		}
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("parent.id", nodeId));
		}
		dc.add(Restrictions.eq(EquipmentField.FIELD_DEL_FLAG, EquipmentField.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentFieldDao.find(page, dc);
	}
	
	/*只找子节点*/
	public List<EquipmentField> findSun(String nodeId){
		List<EquipmentField> list=new ArrayList<EquipmentField>();
		DetachedCriteria dc = equipmentFieldDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", EquipmentField.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("parent.id", nodeId));
		}else return list;
		list = equipmentFieldDao.find(dc);
		return list;
	}
	
	/*只找关联信息*/
	public List<Equipment> findInfo(String nodeId){
		List<Equipment> list=new ArrayList<Equipment>();
		DetachedCriteria dc = equipmentDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", Equipment.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("equipmentField.id", nodeId));
		}else return list;
		list = equipmentDao.find(dc);
		return list;
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentField equipmentField) {
		equipmentFieldDao.clear();
		equipmentFieldDao.save(equipmentField);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//equipmentFieldDao.deleteById(id);
		EquipmentField equipmentField=equipmentFieldDao.get(id);
		equipmentFieldDao.getSession().delete(equipmentField);
	}
	
	public List<EquipmentField> findTree(String module) {
		DetachedCriteria dc = equipmentFieldDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", EquipmentField.DEL_FLAG_NORMAL));
		List<EquipmentField> list = equipmentFieldDao.find(dc);
		// 将没有父节点的节点，找到父节点
		Set<String> parentIdSet = Sets.newHashSet();
		for (EquipmentField e : list){
			if (e.getParent()!=null && StringUtils.isNotBlank(e.getParent().getId())){
				boolean isExistParent = false;
				for (EquipmentField e2 : list){
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
			dc = equipmentFieldDao.createDetachedCriteria();
			dc.add(Restrictions.in("id", parentIdSet));
			//dc.add(Restrictions.eq("disabled", false));
			//dc.addOrder(Order.asc("xh"));
			dc.add(Restrictions.eq("delFlag", EquipmentField.DEL_FLAG_NORMAL));
			list.addAll(0, equipmentFieldDao.find(dc));
		}
		//UserUtils.putCache(CACHE_TOOLCATEGORY_LIST, list);
		
		return list;
	}
	
	public List<Map<String, Object>> getTreeData() {
		List<EquipmentField> list = findTree(null);
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		for(EquipmentField equipmentField : list){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", equipmentField.getId());
			EquipmentField parent=equipmentField.getParent();
			map.put("pId",parent==null?'0':parent.getId());
			map.put("name", equipmentField.getCcmc());
			map.put("module", "equipmentField");
			listMap.add(map);
		}
		return listMap;
	}
	
	public List<EquipmentField> findAll() {
		return equipmentFieldDao.find(equipmentFieldDao.createDetachedCriteria());
	}

	public void updateStateList(String id, String state) {
		EquipmentField equipmentField = equipmentFieldDao.get(id);
		equipmentField.setSfqy(Integer.parseInt(state));
		equipmentFieldDao.getSession().save(equipmentField);	
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_sbcc where ccbm ='"+val1+"'";
			List<EquipmentField> list=equipmentFieldDao.getSession().createSQLQuery(sql).addEntity(EquipmentField.class).list();
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
