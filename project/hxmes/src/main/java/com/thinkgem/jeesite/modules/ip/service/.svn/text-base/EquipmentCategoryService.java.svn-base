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
import com.thinkgem.jeesite.modules.ip.dao.EquipmentCategoryDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentCategory;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;

/**
 * 设备分类Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentCategoryService extends BaseService {
	@Autowired
	private EquipmentCategoryDao equipmentCategoryDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;


	public EquipmentCategory get(String id) {
		return equipmentCategoryDao.get(id);
	}


	public Page<EquipmentCategory> find(Page<EquipmentCategory> page, EquipmentCategory equipmentCategory, String nodeId) {
		DetachedCriteria dc = equipmentCategoryDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(equipmentCategory.getSblbmc())) {
			dc.add(Restrictions.like("sblbmc", "%" + equipmentCategory.getSblbmc() + "%"));
		}
		if (StringUtils.isNotEmpty(nodeId)) {
			dc.add(Restrictions.eq("parent.id", nodeId));
		}
		dc.add(Restrictions.eq(EquipmentCategory.FIELD_DEL_FLAG, EquipmentCategory.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentCategoryDao.find(page, dc);
	}


	/* 只找子节点 */
	public List<EquipmentCategory> findSun(String nodeId) {
		List<EquipmentCategory> list = new ArrayList<EquipmentCategory>();
		DetachedCriteria dc = equipmentCategoryDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", EquipmentCategory.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)) {
			dc.add(Restrictions.eq("parent.id", nodeId));
		} else
			return list;
		list = equipmentCategoryDao.find(dc);
		return list;
	}


	/* 只找关联信息 */
	public List<EquipmentModel> findInfo(String nodeId) {
		List<EquipmentModel> list = new ArrayList<EquipmentModel>();
		DetachedCriteria dc = equipmentModelDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", EquipmentModel.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)) {
			dc.add(Restrictions.eq("equipmentCategory.id", nodeId));
		} else
			return list;
		list = equipmentModelDao.find(dc);
		return list;
	}


	@Transactional(readOnly = false)
	public void save(EquipmentCategory equipmentCategory) {
		equipmentCategoryDao.clear();
		equipmentCategoryDao.save(equipmentCategory);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentCategory equipmentCategory = (EquipmentCategory) equipmentCategoryDao.getSession().get(
				EquipmentCategory.class, id);
		equipmentCategoryDao.getSession().delete(equipmentCategory);
	}


	public List<EquipmentCategory> findTree(String module) {
		DetachedCriteria dc = equipmentCategoryDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", EquipmentCategory.DEL_FLAG_NORMAL));
		List<EquipmentCategory> list = equipmentCategoryDao.find(dc);
		if (list.size() <= 0) {
			EquipmentCategory equipmentCategory = equipmentCategoryDao.insertCategory();
			list.add(equipmentCategory);
		}
		// 将没有父节点的节点，找到父节点
		Set<String> parentIdSet = Sets.newHashSet();
		for (EquipmentCategory e : list) {
			if (e.getParent() != null && StringUtils.isNotBlank(e.getParent().getId())) {
				boolean isExistParent = false;
				for (EquipmentCategory e2 : list) {
					if (e.getParent().getId().equals(e2.getId())) {
						isExistParent = true;
						break;
					}
				}
				if (!isExistParent) {
					parentIdSet.add(e.getParent().getId());
				}
			}
		}
		if (parentIdSet.size() > 0) {
			dc = equipmentCategoryDao.createDetachedCriteria();
			dc.add(Restrictions.in("id", parentIdSet));
			dc.add(Restrictions.eq("delFlag", EquipmentCategory.DEL_FLAG_NORMAL));
			list.addAll(0, equipmentCategoryDao.find(dc));
		}
		return list;
	}


	public List<Map<String, Object>> getTreeData() {
		List<EquipmentCategory> list = findTree(null);
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (EquipmentCategory equipmentCategory : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", equipmentCategory.getId());
			EquipmentCategory parent = equipmentCategory.getParent();
			map.put("pId", parent == null ? '0' : parent.getId());
			map.put("name", equipmentCategory.getSblbmc());
			map.put("module","equipmentCategory");
			listMap.add(map);
		}
		return listMap;
	}


	public List<EquipmentCategory> findAll() {
		return equipmentCategoryDao.find(equipmentCategoryDao.createDetachedCriteria());
	}


	public EquipmentCategory insertCategory() {
		return equipmentCategoryDao.insertCategory();
	}


	public EquipmentCategory updateState(EquipmentCategory equipmentCategory, String state) {
		equipmentCategory.setSfqy(Integer.parseInt(state));
		equipmentCategoryDao.getSession().save(equipmentCategory);
		return null;
	}


	public EquipmentCategory findBySblbbm(String sblbbm) {
		return equipmentCategoryDao.findBySblbbm(sblbbm);
	}
}
