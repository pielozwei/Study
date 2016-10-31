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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeHonor;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeHonorDao;

/**
 * 荣誉称号记录Service
 * @author lzq
 * @version 2016-06-20
 */
@Component
@Transactional(readOnly = true)
public class EmployeeHonorService extends BaseService {

	@Autowired
	private EmployeeHonorDao employeeHonorDao;
	
	public EmployeeHonor get(String id) {
		return employeeHonorDao.get(id);
	}
	
	public Page<EmployeeHonor> find(Page<EmployeeHonor> page, EmployeeHonor employeeHonor) {
		DetachedCriteria dc = employeeHonorDao.createDetachedCriteria();
		
		if (employeeHonor.getEmployee()!=null && StringUtils.isNotEmpty(employeeHonor.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeeHonor.getEmployee().getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeeHonor.FIELD_DEL_FLAG, EmployeeHonor.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeHonorDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeHonor employeeHonor) {
		employeeHonorDao.save(employeeHonor);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeHonorDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeeHonorDao.deleteById(id);
	}
	
}
