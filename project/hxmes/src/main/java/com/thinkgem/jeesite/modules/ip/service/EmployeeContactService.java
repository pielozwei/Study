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
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.EmployeeContact;
import com.thinkgem.jeesite.modules.ip.form.EmployeeInfForm;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeContactDao;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeDao;

/**
 * 个人信息Service
 * @author cml
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class EmployeeContactService extends BaseService {

	@Autowired
	private EmployeeContactDao employeeContactDao;
	EmployeeDao employeeDao = new EmployeeDao();
	
	public EmployeeContact get(String id) {
		return employeeContactDao.get(id);
	}
	
	public Page<EmployeeContact> find(Page<EmployeeContact> page, EmployeeContact employeeContact) {
		DetachedCriteria dc = employeeContactDao.createDetachedCriteria();
		if (employeeContact.getEmployee()!=null && StringUtils.isNotEmpty(employeeContact.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeeContact.getEmployee().getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeeContact.FIELD_DEL_FLAG, EmployeeContact.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeContactDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeContact employeeContact) {
		employeeContactDao.save(employeeContact);
	}
	
	
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeContactDao.deleteById(id);
	}
	
}
