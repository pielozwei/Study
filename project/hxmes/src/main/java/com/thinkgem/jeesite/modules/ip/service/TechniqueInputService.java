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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueInput;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueInputDao;

/**
 * 投入物料Service
 * @author yrd
 * @version 2016-06-28
 */
@Component
@Transactional(readOnly = true)
public class TechniqueInputService extends BaseService {

	@Autowired
	private TechniqueInputDao techniqueInputDao;
	
	public TechniqueInput get(String id) {
		return techniqueInputDao.get(id);
	}
	
	public Page<TechniqueInput> find(Page<TechniqueInput> page, TechniqueInput techniqueInput) {
		DetachedCriteria dc = techniqueInputDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(techniqueInput.getGygzzx_id())){
			dc.add(Restrictions.like("gygzzx_id", "%"+techniqueInput.getGygzzx_id()+"%"));
		}
		dc.add(Restrictions.eq(TechniqueInput.FIELD_DEL_FLAG, TechniqueInput.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return techniqueInputDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueInput techniqueInput) {
		techniqueInputDao.save(techniqueInput);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueInputDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueInputDao.deleteById(id);
	}
}
