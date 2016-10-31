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
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentBOMSheet;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentBOMSheetDao;

/**
 * 设备BOMController
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentBOMSheetService extends BaseService {

	@Autowired
	private EquipmentBOMSheetDao equipmentBOMSheetDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;
	public EquipmentBOMSheet get(String id) {
		return equipmentBOMSheetDao.get(id);
	}
	
	public Page<EquipmentBOMSheet> find(Page<EquipmentBOMSheet> page, EquipmentBOMSheet equipmentBOMSheet,String equipmentModelId) {
		DetachedCriteria dc = equipmentBOMSheetDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(equipmentModelId)){
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (StringUtils.isNotEmpty(equipmentBOMSheet.getSbbomdbh())){
			dc.add(Restrictions.like("sbbomdbh", "%"+equipmentBOMSheet.getSbbomdbh()+"%"));
		}
		dc.add(Restrictions.eq(EquipmentBOMSheet.FIELD_DEL_FLAG, EquipmentBOMSheet.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentBOMSheetDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentBOMSheet equipmentBOMSheet) {
		equipmentBOMSheetDao.clear();
		equipmentBOMSheetDao.save(equipmentBOMSheet);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentBOMSheet equipmentBOMSheet=(EquipmentBOMSheet) equipmentBOMSheetDao.getSession().get(EquipmentBOMSheet.class, id);
		equipmentBOMSheetDao.getSession().delete(equipmentBOMSheet);
	}
	
	public List<EquipmentBOMSheet> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentBOMSheetDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentBOMSheet.FIELD_DEL_FLAG, EquipmentBOMSheet.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentBOMSheetDao.find(dc);
	}
	public List<EquipmentBOMSheet> findAll() {
		return equipmentBOMSheetDao.find(equipmentBOMSheetDao.createDetachedCriteria());
	}

	public EquipmentBOMSheet findBySbbomdbh(String sbbomdbh) {
		return equipmentBOMSheetDao.getByHql("from EquipmentBOMSheet where sbbomdbh=:p1", new Parameter(sbbomdbh));
	}
}
