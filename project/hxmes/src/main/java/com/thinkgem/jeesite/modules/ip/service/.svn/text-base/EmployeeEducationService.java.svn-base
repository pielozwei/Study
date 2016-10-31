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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeEducation;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeEducationDao;

/**
 * 学习经历Service
 * @author Generate Tools
 * @version 2016-06-16
 */
@Component
@Transactional(readOnly = true)
public class EmployeeEducationService extends BaseService {

	@Autowired
	private EmployeeEducationDao employeeEducationDao;
	
	public EmployeeEducation get(String id) {
		return employeeEducationDao.get(id);
	}
	
	public Page<EmployeeEducation> find(Page<EmployeeEducation> page, EmployeeEducation employeeEducation) {
		DetachedCriteria dc = employeeEducationDao.createDetachedCriteria();

		if (employeeEducation.getEmployee()!=null && StringUtils.isNotEmpty(employeeEducation.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeeEducation.getEmployee().getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeeEducation.FIELD_DEL_FLAG, EmployeeEducation.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeEducationDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeEducation employeeEducation) {
		employeeEducationDao.save(employeeEducation);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeEducationDao.deleteById(id);
	}
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeeEducationDao.deleteById(id);
	}
	
}
