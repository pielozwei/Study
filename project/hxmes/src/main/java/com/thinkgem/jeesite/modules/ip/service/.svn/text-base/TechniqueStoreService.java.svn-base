/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.TechniqueStore;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueStoreDao;

/**
 * 存储白名单Service
 * @author ks
 * @version 2016-06-28
 */
@Component
@Transactional(readOnly = true)
public class TechniqueStoreService extends BaseService {

	@Autowired
	private TechniqueStoreDao techniqueStoreDao;
	
	public TechniqueStore get(String id) {
		return techniqueStoreDao.get(id);
	}
	
	public Page<TechniqueStore> find(Page<TechniqueStore> page, TechniqueStore techniqueStore) {
		DetachedCriteria dc = techniqueStoreDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(techniqueStore.getWl_id())){
			dc.add(Restrictions.like("ip_wlbm_id", "%"+techniqueStore.getWl_id()+"%"));
		}
		dc.add(Restrictions.eq(TechniqueStore.FIELD_DEL_FLAG, TechniqueStore.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return techniqueStoreDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueStore techniqueStore) {
		techniqueStoreDao.save(techniqueStore);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueStoreDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueStoreDao.deleteById(id);
	}
}
