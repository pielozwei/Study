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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentDefect;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDefectDao;

/**
 * 缺陷树Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentDefectService extends BaseService {

	@Autowired
	private EquipmentDefectDao equipmentDefectDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;
	public EquipmentDefect get(String id) {
		return equipmentDefectDao.get(id);
	}
	
	public Page<EquipmentDefect> find(Page<EquipmentDefect> page, EquipmentDefect equipmentDefect,String equipmentModelId) {
		DetachedCriteria dc = equipmentDefectDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(equipmentModelId)){
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (StringUtils.isNotEmpty(equipmentDefect.getQxbm())){
			dc.add(Restrictions.like("qxbm", "%"+equipmentDefect.getQxbm()+"%"));
		}
		dc.add(Restrictions.eq(EquipmentDefect.FIELD_DEL_FLAG, EquipmentDefect.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentDefectDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentDefect equipmentDefect) {
		equipmentDefectDao.save(equipmentDefect);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentDefect equipmentDefect=(EquipmentDefect) equipmentDefectDao.getSession().get(EquipmentDefect.class, id);
		equipmentDefectDao.getSession().delete(equipmentDefect);
	}
	
	public List<EquipmentDefect> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentDefectDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentDefect.FIELD_DEL_FLAG, EquipmentDefect.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentDefectDao.find(dc);
	}
	public List<EquipmentDefect> findAll() {
		return equipmentDefectDao.find(equipmentDefectDao.createDetachedCriteria());
	}

	public EquipmentDefect findByQxbm(String qxbm) {
		return equipmentDefectDao.getByHql("from EquipmentDefect where qxbm=:p1", new Parameter(qxbm));
	}


}
