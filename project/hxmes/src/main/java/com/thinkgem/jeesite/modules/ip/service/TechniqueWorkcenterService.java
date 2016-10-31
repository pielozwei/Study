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
import com.thinkgem.jeesite.modules.ip.entity.TechniqueWorkcenter;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueWorkcenterDao;

/**
 * 基本信息Service
 * @author yrd
 * @version 2016-06-27
 */
@Component
@Transactional(readOnly = true)
public class TechniqueWorkcenterService extends BaseService {

	@Autowired
	private TechniqueWorkcenterDao techniqueWorkcenterDao;
	
	public TechniqueWorkcenter get(String id) {
		return techniqueWorkcenterDao.get(id);
	}
	
	public Page<TechniqueWorkcenter> find(Page<TechniqueWorkcenter> page, TechniqueWorkcenter techniqueWorkcenter) {
		DetachedCriteria dc = techniqueWorkcenterDao.createDetachedCriteria();
		if (techniqueWorkcenter.getWorkcenter() != null && StringUtils.isNotEmpty(techniqueWorkcenter.getWorkcenter().getId())){
			dc.add(Restrictions.like("workcenter.id", "%"+techniqueWorkcenter.getWorkcenter().getId()+"%"));
		}
		if (techniqueWorkcenter.getTechnique() != null && StringUtils.isNotEmpty(techniqueWorkcenter.getTechnique().getId())){
			dc.add(Restrictions.like("technique.id", "%"+techniqueWorkcenter.getTechnique().getId()+"%"));
		}
		if (techniqueWorkcenter.getMaterial() != null && StringUtils.isNotEmpty(techniqueWorkcenter.getMaterial().getId())){
			dc.add(Restrictions.like("material.id", "%"+techniqueWorkcenter.getMaterial().getId()+"%"));
		}
		if(techniqueWorkcenter.getSfmrgzzx() != null)
		{
			dc.add(Restrictions.eq("sfmrgzzx", techniqueWorkcenter.getSfmrgzzx()));
		}
		if(techniqueWorkcenter.getSfqy() != null)
		{
			dc.add(Restrictions.eq("sfqy", techniqueWorkcenter.getSfqy()));
		}
		
		dc.add(Restrictions.eq(TechniqueWorkcenter.FIELD_DEL_FLAG, TechniqueWorkcenter.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return techniqueWorkcenterDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueWorkcenter techniqueWorkcenter) {
		techniqueWorkcenterDao.save(techniqueWorkcenter);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueWorkcenterDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueWorkcenterDao.deleteById(id);
	}
}
