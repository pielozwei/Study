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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueCheckItem;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueCheckItemDao;

/**
 * 质量要求Service
 * @author ls
 * @version 2016-06-27
 */
@Component
@Transactional(readOnly = true)
public class TechniqueCheckItemService extends BaseService {

	@Autowired
	private TechniqueCheckItemDao techniqueCheckItemDao;
	
	public TechniqueCheckItem get(String id) {
		return techniqueCheckItemDao.get(id);
	}
	
	public Page<TechniqueCheckItem> find(Page<TechniqueCheckItem> page, TechniqueCheckItem techniqueCheckItem) {
		DetachedCriteria dc = techniqueCheckItemDao.createDetachedCriteria();
		if (techniqueCheckItem.getTechnique() != null && StringUtils.isNotEmpty(techniqueCheckItem.getTechnique().getId())){
			dc.add(Restrictions.like("technique.id", "%"+techniqueCheckItem.getTechnique().getId()+"%"));
			dc.add(Restrictions.eq("sfqy", techniqueCheckItem.getSfqy()));
		}
		if (StringUtils.isNotEmpty(techniqueCheckItem.getJyxmmc())){
			dc.add(Restrictions.like("jyxmmc", "%"+techniqueCheckItem.getJyxmmc()+"%"));
		}
		dc.add(Restrictions.eq(TechniqueCheckItem.FIELD_DEL_FLAG, TechniqueCheckItem.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return techniqueCheckItemDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueCheckItem techniqueCheckItem) {
		techniqueCheckItemDao.save(techniqueCheckItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueCheckItemDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueCheckItemDao.deleteById(id);
	}
}
