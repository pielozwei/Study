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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentTechParam;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentTechParamDao;

/**
 * 技术参数Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentTechParamService extends BaseService {
	@Autowired
	private EquipmentTechParamDao equipmentTechParamDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;


	public EquipmentTechParam get(String id) {
		return equipmentTechParamDao.get(id);
	}


	public Page<EquipmentTechParam> find(Page<EquipmentTechParam> page, EquipmentTechParam equipmentTechParam,
			String equipmentModelId) {
		DetachedCriteria dc = equipmentTechParamDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(equipmentModelId)) {
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (StringUtils.isNotEmpty(equipmentTechParam.getCsmc())) {
			dc.add(Restrictions.like("csmc", "%" + equipmentTechParam.getCsmc() + "%"));
		}
		dc.add(Restrictions.eq(EquipmentTechParam.FIELD_DEL_FLAG, EquipmentTechParam.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentTechParamDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(EquipmentTechParam equipmentTechParam) {
		equipmentTechParamDao.clear();
		equipmentTechParamDao.save(equipmentTechParam);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentTechParam equipmentTechParam = (EquipmentTechParam) equipmentTechParamDao.getSession().get(
				EquipmentTechParam.class, id);
		equipmentTechParamDao.getSession().delete(equipmentTechParam);
	}


	public List<EquipmentTechParam> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentTechParamDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentTechParam.FIELD_DEL_FLAG, EquipmentTechParam.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentTechParamDao.find(dc);
	}


	public List<EquipmentTechParam> findAll() {
		return equipmentTechParamDao.find(equipmentTechParamDao.createDetachedCriteria());
	}


	public EquipmentTechParam findByCsbm(String csbm) {
		return equipmentTechParamDao.getByHql("from EquipmentTechParam where csbm=:p1", new Parameter(csbm));
	}
}
