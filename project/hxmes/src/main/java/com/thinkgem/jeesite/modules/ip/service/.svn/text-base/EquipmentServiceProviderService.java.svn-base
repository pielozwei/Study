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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentServiceProvider;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentServiceProviderDao;

/**
 * 服务商Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentServiceProviderService extends BaseService {

	@Autowired
	private EquipmentServiceProviderDao equipmentServiceProviderDao;
	@Autowired
	private EquipmentDao equipmentDao;
	public EquipmentServiceProvider get(String id) {
		return equipmentServiceProviderDao.get(id);
	}
	
	public Page<EquipmentServiceProvider> find(Page<EquipmentServiceProvider> page, EquipmentServiceProvider equipmentServiceProvider,String equipmentId) {
		DetachedCriteria dc = equipmentServiceProviderDao.createDetachedCriteria();
		dc.createAlias("equipment", "equipment");
		dc.createAlias("provider", "provider");
		if (StringUtils.isNotEmpty(equipmentId)){
			dc.add(Restrictions.eq("equipment.id", equipmentId));
		}
		if (equipmentServiceProvider.getProvider()!=null&&StringUtils.isNotEmpty(equipmentServiceProvider.getProvider().getGysmc())){
			dc.add(Restrictions.like("provider.gysmc", "%"+equipmentServiceProvider.getProvider().getGysmc()+"%"));
		}
		dc.add(Restrictions.eq(EquipmentServiceProvider.FIELD_DEL_FLAG, EquipmentServiceProvider.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("provider.gysbm"));
		return equipmentServiceProviderDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentServiceProvider equipmentServiceProvider) {
		equipmentServiceProviderDao.save(equipmentServiceProvider);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//equipmentServiceProviderDao.deleteById(id);
		EquipmentServiceProvider equipmentServiceProvider=equipmentServiceProviderDao.get(id);
		equipmentServiceProviderDao.getSession().delete(equipmentServiceProvider);
	}
	
	public List<EquipmentServiceProvider> findByEquipmentId(String equipmentId) {
		DetachedCriteria dc = equipmentServiceProviderDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentServiceProvider.FIELD_DEL_FLAG, EquipmentServiceProvider.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipment.id", equipmentId));
		return equipmentServiceProviderDao.find(dc);
	}
	public List<EquipmentServiceProvider> findAll() {
		return equipmentServiceProviderDao.find(equipmentServiceProviderDao.createDetachedCriteria());
	}
}
