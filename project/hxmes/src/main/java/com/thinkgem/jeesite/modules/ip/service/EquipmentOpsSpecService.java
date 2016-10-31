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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentOpsSpec;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentOpsSpecDao;

/**
 * 操作规程Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentOpsSpecService extends BaseService {

	@Autowired
	private EquipmentOpsSpecDao equipmentOpsSpecDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;
	public EquipmentOpsSpec get(String id) {
		return equipmentOpsSpecDao.get(id);
	}
	
	public Page<EquipmentOpsSpec> find(Page<EquipmentOpsSpec> page, EquipmentOpsSpec equipmentOpsSpec,String equipmentModelId) {
		DetachedCriteria dc = equipmentOpsSpecDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(equipmentModelId)){
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (equipmentOpsSpec.getGclb()!=null){
			dc.add(Restrictions.eq("gclb", equipmentOpsSpec.getGclb()));
		}
		dc.add(Restrictions.eq(EquipmentOpsSpec.FIELD_DEL_FLAG, EquipmentOpsSpec.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentOpsSpecDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentOpsSpec equipmentOpsSpec) {
		equipmentOpsSpecDao.save(equipmentOpsSpec);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentOpsSpec equipmentOpsSpec=(EquipmentOpsSpec) equipmentOpsSpecDao.getSession().get(EquipmentOpsSpec.class, id);
		equipmentOpsSpecDao.getSession().delete(equipmentOpsSpec);
	}
	
	public List<EquipmentOpsSpec> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentOpsSpecDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentOpsSpec.FIELD_DEL_FLAG, EquipmentOpsSpec.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentOpsSpecDao.find(dc);
	}
	public List<EquipmentOpsSpec> findAll() {
		return equipmentOpsSpecDao.find(equipmentOpsSpecDao.createDetachedCriteria());
	}

	public EquipmentOpsSpec findByGcmc(String gcmc) {
		// TODO Auto-generated method stub
		return equipmentOpsSpecDao.getByHql("from EquipmentOpsSpec where gcmc=:p1", new Parameter(gcmc));
	}
}
