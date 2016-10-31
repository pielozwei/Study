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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueParameter;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueParameterDao;

/**
 * 工艺参数Service
 * @author zzc
 * @version 2016-06-28
 */
@Component
@Transactional(readOnly = true)
public class TechniqueParameterService extends BaseService {

	@Autowired
	private TechniqueParameterDao techniqueParameterDao;
	
	public TechniqueParameter get(String id) {
		return techniqueParameterDao.get(id);
	}
	
	public Page<TechniqueParameter> find(Page<TechniqueParameter> page, TechniqueParameter techniqueParameter) {
		DetachedCriteria dc = techniqueParameterDao.createDetachedCriteria();
		if (techniqueParameter.getTechnique() != null && StringUtils.isNotEmpty(techniqueParameter.getTechnique().getId())){
			dc.add(Restrictions.like("technique.id", "%"+techniqueParameter.getTechnique().getId()+"%"));
			dc.add(Restrictions.eq("sfqy", techniqueParameter.getSfqy()));
		}
		dc.add(Restrictions.eq(TechniqueParameter.FIELD_DEL_FLAG, TechniqueParameter.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return techniqueParameterDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueParameter techniqueParameter) {
		techniqueParameterDao.save(techniqueParameter);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueParameterDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueParameterDao.deleteById(id);
	}
}
