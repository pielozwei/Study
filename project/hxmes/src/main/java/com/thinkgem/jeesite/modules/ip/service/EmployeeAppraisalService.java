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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeAppraisal;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeAppraisalDao;

/**
 * 组织考核经历Service
 * @author WuWB
 * @version 2016-06-20
 */
@Component
@Transactional(readOnly = true)
public class EmployeeAppraisalService extends BaseService {

	@Autowired
	private EmployeeAppraisalDao employeeAppraisalDao;
	
	public EmployeeAppraisal get(String id) {
		return employeeAppraisalDao.get(id);
	}
	
	public Page<EmployeeAppraisal> find(Page<EmployeeAppraisal> page, EmployeeAppraisal employeeAppraisal) {
		DetachedCriteria dc = employeeAppraisalDao.createDetachedCriteria();
		
		if (employeeAppraisal.getEmployee()!=null && StringUtils.isNotEmpty(employeeAppraisal.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeeAppraisal.getEmployee().getId()+"%"));
		}
		
		dc.add(Restrictions.eq(EmployeeAppraisal.FIELD_DEL_FLAG, EmployeeAppraisal.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeAppraisalDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeAppraisal employeeAppraisal) {
		employeeAppraisalDao.save(employeeAppraisal);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeAppraisalDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeeAppraisalDao.deleteById(id);
	}
}
