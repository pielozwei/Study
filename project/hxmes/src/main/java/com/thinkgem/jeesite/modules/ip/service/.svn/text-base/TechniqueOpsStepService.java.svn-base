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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueOpsStep;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueOpsStepDao;

/**
 * 规程要求Service
 * @author WuWB
 * @version 2016-06-27
 */
@Component
@Transactional(readOnly = true)
public class TechniqueOpsStepService extends BaseService {

	@Autowired
	private TechniqueOpsStepDao techniqueOpsStepDao;
	
	public TechniqueOpsStep get(String id) {
		return techniqueOpsStepDao.get(id);
	}
	
	public Page<TechniqueOpsStep> find(Page<TechniqueOpsStep> page, TechniqueOpsStep techniqueOpsStep) {
		DetachedCriteria dc = techniqueOpsStepDao.createDetachedCriteria();
		if (techniqueOpsStep.getTechnique() != null && StringUtils.isNotEmpty(techniqueOpsStep.getTechnique().getId())){
			dc.add(Restrictions.like("technique.id", "%"+techniqueOpsStep.getTechnique().getId()+"%"));
			dc.add(Restrictions.eq("sfqy", techniqueOpsStep.getSfqy()));
		}
		dc.add(Restrictions.eq(TechniqueOpsStep.FIELD_DEL_FLAG, TechniqueOpsStep.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return techniqueOpsStepDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueOpsStep techniqueOpsStep) {
		techniqueOpsStepDao.save(techniqueOpsStep);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueOpsStepDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueOpsStepDao.deleteById(id);
	}
}
