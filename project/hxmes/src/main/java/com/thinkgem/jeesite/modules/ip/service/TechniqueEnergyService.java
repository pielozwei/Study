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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueEnergy;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueEnergyDao;

/**
 * 能源定额Service
 * @author WuWB
 * @version 2016-06-27
 */
@Component
@Transactional(readOnly = true)
public class TechniqueEnergyService extends BaseService {

	@Autowired
	private TechniqueEnergyDao techniqueEnergyDao;
	
	public TechniqueEnergy get(String id) {
		return techniqueEnergyDao.get(id);
	}
	
	public Page<TechniqueEnergy> find(Page<TechniqueEnergy> page, TechniqueEnergy techniqueEnergy) {
		DetachedCriteria dc = techniqueEnergyDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(techniqueEnergy.getGygzzx_id())){
			dc.add(Restrictions.like("gygzzx_id", "%"+techniqueEnergy.getGygzzx_id()+"%"));
		}
		dc.add(Restrictions.eq(TechniqueEnergy.FIELD_DEL_FLAG, TechniqueEnergy.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return techniqueEnergyDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueEnergy techniqueEnergy) {
		techniqueEnergyDao.save(techniqueEnergy);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueEnergyDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueEnergyDao.deleteById(id);
	}
}
