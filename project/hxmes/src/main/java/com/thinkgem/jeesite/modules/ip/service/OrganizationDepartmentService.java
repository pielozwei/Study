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
import com.thinkgem.jeesite.modules.ip.entity.OrganizationDepartment;
import com.thinkgem.jeesite.modules.ip.dao.OrganizationDepartmentDao;

/**
 * 组织机构部门Service
 * @author xht
 * @version 2016-09-07
 */
@Component
@Transactional(readOnly = true)
public class OrganizationDepartmentService extends BaseService {

	@Autowired
	private OrganizationDepartmentDao organizationDepartmentDao;
	
	public OrganizationDepartment get(String id) {
		return organizationDepartmentDao.get(id);
	}
	
	public Page<OrganizationDepartment> find(Page<OrganizationDepartment> page, OrganizationDepartment organizationDepartment) {
		DetachedCriteria dc = organizationDepartmentDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(organizationDepartment.getBmmc())){
			dc.add(Restrictions.like("bmmc", "%"+organizationDepartment.getBmmc()+"%"));
		}
		if (organizationDepartment.getOrganization() != null && StringUtils.isNotEmpty(organizationDepartment.getOrganization().getId())){
			dc.add(Restrictions.like("organization.id", "%"+organizationDepartment.getOrganization().getId()+"%"));
		}
//		if (StringUtils.isNotEmpty(organizationDepartment.getId())){
//			dc.add(Restrictions.like("organization.id", "%"+organizationDepartment.getId()+"%"));
//		}
		dc.add(Restrictions.eq(OrganizationDepartment.FIELD_DEL_FLAG, OrganizationDepartment.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return organizationDepartmentDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(OrganizationDepartment organizationDepartment) {
		organizationDepartmentDao.save(organizationDepartment);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		organizationDepartmentDao.deleteById(id);
	}
	
	//获取所有的部门信息，用于列表项组装
	public List<OrganizationDepartment> findAll() {
		DetachedCriteria dc = organizationDepartmentDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Employee.FIELD_DEL_FLAG, Employee.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return organizationDepartmentDao.find(dc);
	}
	
}
