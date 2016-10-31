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
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.OrganizationPosition;
import com.thinkgem.jeesite.modules.ip.dao.OrganizationPositionDao;

/**
 * 岗位信息管理Service
 * @author Iris
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class OrganizationPositionService extends BaseService {

	@Autowired
	private OrganizationPositionDao organizationPositionDao;
	
	public OrganizationPosition get(String id) {
		return organizationPositionDao.get(id);
	}
	
	public Page<OrganizationPosition> find(Page<OrganizationPosition> page, OrganizationPosition organizationPosition) {
		DetachedCriteria dc = organizationPositionDao.createDetachedCriteria();
		if (organizationPosition.getOrganizationposintionrank() != null && StringUtils.isNotEmpty(organizationPosition.getOrganizationposintionrank().getId())){
			dc.add(Restrictions.like("organizationposintionrank.id", "%"+organizationPosition.getOrganizationposintionrank().getId()+"%"));
		}
		dc.add(Restrictions.eq(OrganizationPosition.FIELD_DEL_FLAG, OrganizationPosition.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return organizationPositionDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(OrganizationPosition organizationPosition) {
		organizationPositionDao.clear();
		organizationPositionDao.save(organizationPosition);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		organizationPositionDao.deleteById(id);
	}
	
	public List<OrganizationPosition> findAll() {
		DetachedCriteria dc = organizationPositionDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Employee.FIELD_DEL_FLAG, Employee.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return organizationPositionDao.find(dc);
	}
	
}
