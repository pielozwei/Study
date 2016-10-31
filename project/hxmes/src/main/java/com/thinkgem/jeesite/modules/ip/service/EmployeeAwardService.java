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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeAward;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeAwardDao;

/**
 * 获奖情况记录Service
 * @author zzc
 * @version 2016-06-20
 */
@Component
@Transactional(readOnly = true)
public class EmployeeAwardService extends BaseService {

	@Autowired
	private EmployeeAwardDao employeeAwardDao;
	
	public EmployeeAward get(String id) {
		return employeeAwardDao.get(id);
	}
	
	public Page<EmployeeAward> find(Page<EmployeeAward> page, EmployeeAward employeeAward) {
		DetachedCriteria dc = employeeAwardDao.createDetachedCriteria();

		if (employeeAward.getEmployee()!=null && StringUtils.isNotEmpty(employeeAward.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeeAward.getEmployee().getId()+"%"));
		}
		
		dc.add(Restrictions.eq(EmployeeAward.FIELD_DEL_FLAG, EmployeeAward.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeAwardDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeAward employeeAward) {
		employeeAwardDao.save(employeeAward);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeAwardDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeeAwardDao.deleteById(id);
	}

}
