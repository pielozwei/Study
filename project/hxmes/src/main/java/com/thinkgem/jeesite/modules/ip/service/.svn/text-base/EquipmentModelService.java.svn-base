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
import com.thinkgem.jeesite.modules.ip.dao.EquipmentCategoryDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;

/**
 * 基本信息Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentModelService extends BaseService {
	@Autowired
	private EquipmentModelDao equipmentModelDao;
	@Autowired
	private EquipmentCategoryDao equipmentCategoryDao;


	public EquipmentModel get(String id) {
		return equipmentModelDao.get(id);
	}


	public Page<EquipmentModel> find(Page<EquipmentModel> page, EquipmentModel equipmentModel, String nodeId) {
		DetachedCriteria dc = equipmentModelDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(nodeId)) {
			dc.add(Restrictions.eq("equipmentCategory.id", nodeId));
		}
		if (StringUtils.isNotEmpty(equipmentModel.getSbggxhmc())) {
			dc.add(Restrictions.like("sbggxhmc", "%" + equipmentModel.getSbggxhmc() + "%"));
		}
		dc.add(Restrictions.eq(EquipmentModel.FIELD_DEL_FLAG, EquipmentModel.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentModelDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(EquipmentModel equipmentModel) {
		equipmentModelDao.save(equipmentModel);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentModel equipmentModel = (EquipmentModel) equipmentModelDao.getSession().get(EquipmentModel.class, id);
		equipmentModelDao.getSession().delete(equipmentModel);
	}


	public List<EquipmentModel> findAll() {
		return equipmentModelDao.find(equipmentModelDao.createDetachedCriteria());
	}


	public EquipmentModel findBySbggxhbm(String sbggxhbm) {
		return equipmentModelDao.findBySbggxhbm(sbggxhbm);
	}
}
