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
import com.thinkgem.jeesite.modules.ip.entity.EquipmentDocument;
import com.thinkgem.jeesite.modules.ip.entity.Provider;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentModelDao;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDocumentDao;

/**
 * 技术资料Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class EquipmentDocumentService extends BaseService {

	@Autowired
	private EquipmentDocumentDao equipmentDocumentDao;
	@Autowired
	private EquipmentModelDao equipmentModelDao;
	public EquipmentDocument get(String id) {
		return equipmentDocumentDao.get(id);
	}
	
	public Page<EquipmentDocument> find(Page<EquipmentDocument> page, EquipmentDocument equipmentDocument,String equipmentModelId) {
		DetachedCriteria dc = equipmentDocumentDao.createDetachedCriteria();
		dc.createAlias("equipmentModel", "equipmentModel");
		if (StringUtils.isNotEmpty(equipmentModelId)){
			dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		}
		if (StringUtils.isNotEmpty(equipmentDocument.getWdbt())){
			dc.add(Restrictions.like("wdbt", "%"+equipmentDocument.getWdbt()+"%"));
		}
		dc.add(Restrictions.eq(EquipmentDocument.FIELD_DEL_FLAG, EquipmentDocument.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("wdbm"));
		return equipmentDocumentDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EquipmentDocument equipmentDocument) {
		equipmentDocumentDao.save(equipmentDocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		EquipmentDocument equipmentDocument = (EquipmentDocument) equipmentDocumentDao.getSession().get(EquipmentDocument.class, id);
		equipmentDocumentDao.getSession().delete(equipmentDocument);
	}
	
	public List<EquipmentDocument> findByEquipmentModelId(String equipmentModelId) {
		DetachedCriteria dc = equipmentDocumentDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EquipmentDocument.FIELD_DEL_FLAG, EquipmentDocument.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("equipmentModel.id", equipmentModelId));
		return equipmentDocumentDao.find(dc);
	}
	public List<EquipmentDocument> findAll() {
		return equipmentDocumentDao.find(equipmentDocumentDao.createDetachedCriteria());
	}

	public EquipmentDocument findByWdbm(String wdbm) {
		return equipmentDocumentDao.getByHql("from EquipmentDocument where wdbm=:p1", new Parameter(wdbm));
	}
}
