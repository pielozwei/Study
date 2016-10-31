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
import com.thinkgem.jeesite.modules.ip.entity.Equipment;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentField;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentFieldDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDao;

/**
 * 基本信息Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentService extends BaseService {

	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private EquipmentFieldDao equipmentFieldDao;
	public Equipment get(String id) {
		return equipmentDao.get(id);
	}
	
	public Page<Equipment> find(Page<Equipment> page, Equipment equipment, String nodeId) {
		DetachedCriteria dc = equipmentDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("equipmentField.id", nodeId));
		}
		if (equipment.getEquipmentModel()!=null&&StringUtils.isNotEmpty(equipment.getEquipmentModel().getSbggxhmc())){
			dc.add(Restrictions.like("equipmentModel.sbggxhmc", "%"+equipment.getEquipmentModel().getSbggxhmc()+"%"));
		}
		dc.add(Restrictions.eq(Equipment.FIELD_DEL_FLAG, Equipment.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return equipmentDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Equipment equipment) {
		equipmentDao.save(equipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//equipmentDao.deleteById(id);
		Equipment equipment=equipmentDao.get(id);
		equipmentDao.getSession().delete(equipment);
	}
	
	public List<Equipment> findAll() {
		return equipmentDao.find(equipmentDao.createDetachedCriteria());
	}

	public List<Object[]> findByColumn(String columns, String orderBy, int start, int end) {
		String qlString = "select "+columns+" from (select " + columns + " from ip_sb order by " + orderBy +") where rownum > " + start +" and rownum <= " + end;
		return equipmentDao.findBySql(qlString);
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_sb where sbbm ='"+val1+"'";
			List<Equipment> list=equipmentDao.getSession().createSQLQuery(sql).addEntity(Equipment.class).list();
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
