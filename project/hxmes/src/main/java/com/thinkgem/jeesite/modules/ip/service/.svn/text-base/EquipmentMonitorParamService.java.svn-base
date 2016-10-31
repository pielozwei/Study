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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentField;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentMonitorParam;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentMonitorParamDao;

/**
 * 关键监控参数Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentMonitorParamService extends BaseService {

	@Autowired
	private EquipmentMonitorParamDao equipmentMonitorParamDao;
	@Autowired
	private EquipmentDao equipmentDao;
	public EquipmentMonitorParam get(String id) {
		return equipmentMonitorParamDao.get(id);
	}
	
	public Page<EquipmentMonitorParam> find(Page<EquipmentMonitorParam> page, EquipmentMonitorParam equipmentMonitorParam,String equipmentId) {
		DetachedCriteria dc = equipmentMonitorParamDao.createDetachedCriteria();
		dc.createAlias("equipment", "equipment");
		if (StringUtils.isNotEmpty(equipmentId)){
			dc.add(Restrictions.eq("equipment.id", equipmentId));
		}
		if (StringUtils.isNotEmpty(equipmentMonitorParam.getCsmc())){
			dc.add(Restrictions.like("csmc", "%"+equipmentMonitorParam.getCsmc()+"%"));
		}
		dc.add(Restrictions.eq(EquipmentMonitorParam.FIELD_DEL_FLAG, EquipmentMonitorParam.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentMonitorParamDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentMonitorParam equipmentMonitorParam) {
		equipmentMonitorParamDao.save(equipmentMonitorParam);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//equipmentMonitorParamDao.deleteById(id);
		EquipmentMonitorParam equipmentMonitorParam=equipmentMonitorParamDao.get(id);
		equipmentMonitorParamDao.getSession().delete(equipmentMonitorParam);
	}
	
	public List<EquipmentMonitorParam> findByEquipmentId(String equipmentId) {
		DetachedCriteria dc = equipmentMonitorParamDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentMonitorParam.FIELD_DEL_FLAG, EquipmentMonitorParam.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipment.id", equipmentId));
		return equipmentMonitorParamDao.find(dc);
	}
	public List<EquipmentMonitorParam> findAll() {
		return equipmentMonitorParamDao.find(equipmentMonitorParamDao.createDetachedCriteria());
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_sbjkcs where csdm ='"+val1+"'";
			List<EquipmentMonitorParam> list=equipmentMonitorParamDao.getSession().createSQLQuery(sql).addEntity(EquipmentMonitorParam.class).list();
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
}
