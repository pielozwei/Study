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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeCareer;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeCareerDao;

/**
 * 工作经历Service
 * @author cml
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class EmployeeCareerService extends BaseService {

	@Autowired
	private EmployeeCareerDao employeeCareerDao;
	
	public EmployeeCareer get(String id) {
		return employeeCareerDao.get(id);
	}
	
	public Page<EmployeeCareer> find(Page<EmployeeCareer> page, EmployeeCareer employeeCareer) {
		DetachedCriteria dc = employeeCareerDao.createDetachedCriteria();

		if (employeeCareer.getEmployee()!=null && StringUtils.isNotEmpty(employeeCareer.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeeCareer.getEmployee().getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeeCareer.FIELD_DEL_FLAG, EmployeeCareer.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeCareerDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeCareer employeeCareer) {
		employeeCareerDao.save(employeeCareer);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeCareerDao.deleteById(id);
	}
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeeCareerDao.deleteById(id);
	}
//	public List<EmployeeCareer> findAll() {
//		DetachedCriteria dc = employeeCareerDao.createDetachedCriteria();
//		dc.add(Restrictions.eq(Employee.FIELD_DEL_FLAG, Employee.DEL_FLAG_NORMAL));
//		dc.addOrder(Order.asc("xssx"));
//		return employeeCareerDao.find(dc);
//	}
//	public int getlist(Employee employee){
//		List<EmployeeCareer> list = findAll();
//		
//		for (int i=0; i<list.size(); i++){
//			EmployeeCareer e = list.get(i);
//			if(employeeCareer.getXssx().equals(e.getXssx)){
//
//				return 3;
//			}
//				
//		}
//			return 1;
//			
//	}
}
