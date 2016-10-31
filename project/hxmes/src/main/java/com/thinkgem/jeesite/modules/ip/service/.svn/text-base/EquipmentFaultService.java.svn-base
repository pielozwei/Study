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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentFault;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentFaultDao;

/**
 * 故障树Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentFaultService extends BaseService {

	@Autowired
	private EquipmentFaultDao equipmentFaultDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;
	public EquipmentFault get(String id) {
		return equipmentFaultDao.get(id);
	}
	
	public Page<EquipmentFault> find(Page<EquipmentFault> page, EquipmentFault equipmentFault,String equipmentModelId) {
		DetachedCriteria dc = equipmentFaultDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(equipmentModelId)){
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (StringUtils.isNotEmpty(equipmentFault.getGzbm())){
			dc.add(Restrictions.like("gzbm", "%"+equipmentFault.getGzbm()+"%"));
		}
		dc.add(Restrictions.eq(EquipmentFault.FIELD_DEL_FLAG, EquipmentFault.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentFaultDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentFault equipmentFault) {
		equipmentFaultDao.save(equipmentFault);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentFault equipmentFault=(EquipmentFault) equipmentFaultDao.getSession().get(EquipmentFault.class, id);
		equipmentFaultDao.getSession().delete(equipmentFault);
	}
	
	public List<EquipmentFault> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentFaultDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentFault.FIELD_DEL_FLAG, EquipmentFault.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentFaultDao.find(dc);
	}
	public List<EquipmentFault> findAll() {
		return equipmentFaultDao.find(equipmentFaultDao.createDetachedCriteria());
	}

	public EquipmentFault findByGzbm(String gzbm) {
		return equipmentFaultDao.getByHql("from EquipmentFault where gzbm=:p1", new Parameter(gzbm));
	}
}
