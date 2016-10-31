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
import com.thinkgem.jeesite.modules.ip.entity.WorkShift;
import com.thinkgem.jeesite.modules.ip.dao.WorkShiftDao;

/**
 * 工作班制Service
 * @author lucl
 * @version 2016-08-15
 */
@Component
@Transactional(readOnly = true)
public class WorkShiftService extends BaseService {

	@Autowired
	private WorkShiftDao workShiftDao;
	
	public WorkShift get(String id) {
		return workShiftDao.get(id);
	}
	
	public Page<WorkShift> find(Page<WorkShift> page, WorkShift workShift) {
		DetachedCriteria dc = workShiftDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(workShift.getBzmc())){
			dc.add(Restrictions.like("bzmc", "%"+workShift.getBzmc()+"%"));
		}
		dc.add(Restrictions.eq(WorkShift.FIELD_DEL_FLAG, WorkShift.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return workShiftDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkShift workShift) {
		workShiftDao.save(workShift);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//workShiftDao.deleteById(id);
		WorkShift workShift = workShiftDao.get(id);
		workShiftDao.getSession().delete(workShift);
	}
	
}
