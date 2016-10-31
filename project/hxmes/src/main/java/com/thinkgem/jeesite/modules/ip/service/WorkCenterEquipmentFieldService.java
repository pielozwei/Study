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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterEquipmentField;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterEquipmentFieldDao;

/**
 * 工作中心设备Service
 * @author lucl
 * @version 2016-06-23
 */
@Component
@Transactional(readOnly = true)
public class WorkCenterEquipmentFieldService extends BaseService {

	@Autowired
	private WorkCenterEquipmentFieldDao gzzxsbDao;
	
	public WorkCenterEquipmentField get(String id) {
		return gzzxsbDao.get(id);
	}
	
	public Page<WorkCenterEquipmentField> find(Page<WorkCenterEquipmentField> page, WorkCenterEquipmentField gzzxsb) {
		DetachedCriteria dc = gzzxsbDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(gzzxsb.getName())){
			dc.add(Restrictions.like("name", "%"+gzzxsb.getName()+"%"));
		}
		dc.add(Restrictions.eq(WorkCenterEquipmentField.FIELD_DEL_FLAG, WorkCenterEquipmentField.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return gzzxsbDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkCenterEquipmentField gzzxsb) {
		gzzxsbDao.save(gzzxsb);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//gzzxsbDao.deleteById(id);
		WorkCenterEquipmentField workCenterEquipmentField = gzzxsbDao.get(id);
		gzzxsbDao.getSession().delete(workCenterEquipmentField);
	}
	
}
