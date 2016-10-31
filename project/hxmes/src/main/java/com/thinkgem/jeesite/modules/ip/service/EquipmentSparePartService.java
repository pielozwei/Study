/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentSparePart;
import com.thinkgem.jeesite.modules.ip.entity.Provider;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentSparePartDao;

/**
 * 备品备件Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentSparePartService extends BaseService {
	@Autowired
	private EquipmentSparePartDao equipmentSparePartDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;


	public EquipmentSparePart get(String id) {
		return equipmentSparePartDao.get(id);
	}


	public Page<EquipmentSparePart> find(Page<EquipmentSparePart> page, EquipmentSparePart equipmentSparePart,
			String equipmentModelId) {
		DetachedCriteria dc = equipmentSparePartDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		dc.createAlias("bjwlbm", "bjwlbm");
		if (StringUtils.isNotEmpty(equipmentModelId)) {
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (equipmentSparePart.getBjwlbm() != null) {
			if (StringUtils.isNotEmpty(equipmentSparePart.getBjwlbm().getWlmc())) {
				dc.add(Restrictions.like("bjwlbm.wlmc", "%" + equipmentSparePart.getBjwlbm().getWlmc() + "%"));
			}
		}
		dc.add(Restrictions.eq(EquipmentSparePart.FIELD_DEL_FLAG, EquipmentSparePart.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("bjwlbm.wlbm"));
		return equipmentSparePartDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(EquipmentSparePart equipmentSparePart) {
		equipmentSparePartDao.clear();
		equipmentSparePartDao.save(equipmentSparePart);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentSparePart equipmentSparePart = (EquipmentSparePart) equipmentSparePartDao.getSession().get(
				EquipmentSparePart.class, id);
		equipmentSparePartDao.getSession().delete(equipmentSparePart);
	}


	public List<EquipmentSparePart> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentSparePartDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentSparePart.FIELD_DEL_FLAG, EquipmentSparePart.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentSparePartDao.find(dc);
	}


	public List<EquipmentSparePart> findAll() {
		return equipmentSparePartDao.find(equipmentSparePartDao.createDetachedCriteria());
	}
}
