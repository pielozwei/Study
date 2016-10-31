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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePosition;
import com.thinkgem.jeesite.modules.ip.dao.EmployeePositionDao;

/**
 * 行政党派职务经历Service
 * @author yrd
 * @version 2016-06-20
 */
@Component
@Transactional(readOnly = true)
public class EmployeePositionService extends BaseService {

	@Autowired
	private EmployeePositionDao employeePositionDao;
	
	public EmployeePosition get(String id) {
		return employeePositionDao.get(id);
	}
	
	public Page<EmployeePosition> find(Page<EmployeePosition> page, EmployeePosition employeePosition) {
		DetachedCriteria dc = employeePositionDao.createDetachedCriteria();
		if (employeePosition.getEmployee()!=null && StringUtils.isNotEmpty(employeePosition.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeePosition.getEmployee().getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeePosition.FIELD_DEL_FLAG, EmployeePosition.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeePositionDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeePosition employeePosition) {
		employeePositionDao.save(employeePosition);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeePositionDao.deleteById(id);
	}

	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeePositionDao.deleteById(id);
	}
}
