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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterOrganization;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterOrganizationDao;

/**
 * 工作中心机构Service
 * @author lucl
 * @version 2016-06-23
 */
@Component
@Transactional(readOnly = true)
public class WorkCenterOrganizationService extends BaseService {

	@Autowired
	private WorkCenterOrganizationDao gzzxjgDao;
	
	public WorkCenterOrganization get(String id) {
		return gzzxjgDao.get(id);
	}
	
	public Page<WorkCenterOrganization> find(Page<WorkCenterOrganization> page, WorkCenterOrganization gzzxjg) {
		DetachedCriteria dc = gzzxjgDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(gzzxjg.getName())){
			dc.add(Restrictions.like("name", "%"+gzzxjg.getName()+"%"));
		}
		dc.add(Restrictions.eq(WorkCenterOrganization.FIELD_DEL_FLAG, WorkCenterOrganization.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return gzzxjgDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkCenterOrganization gzzxjg) {
		gzzxjgDao.save(gzzxjg);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//gzzxjgDao.deleteById(id);
		WorkCenterOrganization workCenterOrganization = gzzxjgDao.get(id);
		gzzxjgDao.getSession().delete(workCenterOrganization);
	}
	
}
