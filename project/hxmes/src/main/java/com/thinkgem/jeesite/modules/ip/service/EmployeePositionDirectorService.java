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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePositionDirector;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeDao;
import com.thinkgem.jeesite.modules.ip.dao.EmployeePositionDirectorDao;

/**
 * 人员岗位设置Service
 * @author yrd
 * @version 2016-09-01
 */
@Component
@Transactional(readOnly = true)
public class EmployeePositionDirectorService extends BaseService {

	@Autowired
	private EmployeePositionDirectorDao employeePositionDirectorDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	public EmployeePositionDirector get(String id) {
		return employeePositionDirectorDao.get(id);
	}
	
	public Page<EmployeePositionDirector> find(Page<EmployeePositionDirector> page, EmployeePositionDirector employeePositionDirector) {
		DetachedCriteria dc = employeePositionDirectorDao.createDetachedCriteria();
		if (employeePositionDirector.getOrganizationposition() != null && StringUtils.isNotEmpty(employeePositionDirector.getOrganizationposition().getId())){
			dc.add(Restrictions.like("organizationposition.id", "%"+employeePositionDirector.getOrganizationposition().getId()+"%"));
		}
		if (employeePositionDirector.getEmployee() != null && StringUtils.isNotEmpty(employeePositionDirector.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeePositionDirector.getEmployee().getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeePositionDirector.FIELD_DEL_FLAG, EmployeePositionDirector.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeePositionDirectorDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeePositionDirector employeePositionDirector) {
		
			employeePositionDirectorDao.save(employeePositionDirector);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeePositionDirectorDao.deleteById(id);
	}

	public void deletezgBygwid(String zgid, String gwid) {
		// TODO Auto-generated method stub
		employeePositionDirectorDao.deletezgBygwid(zgid, gwid);
	}
	
}
