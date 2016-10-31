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
import com.thinkgem.jeesite.modules.ip.entity.EmployeeProject;
import com.thinkgem.jeesite.modules.ip.dao.EmployeeProjectDao;

/**
 * 科研项目记录Service
 * @author ls
 * @version 2016-06-20
 */
@Component
@Transactional(readOnly = true)
public class EmployeeProjectService extends BaseService {

	@Autowired
	private EmployeeProjectDao employeeProjectDao;
	
	public EmployeeProject get(String id) {
		return employeeProjectDao.get(id);
	}
	
	public Page<EmployeeProject> find(Page<EmployeeProject> page, EmployeeProject employeeProject) {
		DetachedCriteria dc = employeeProjectDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(employeeProject.getKyxmmc())){
			dc.add(Restrictions.like("kyxmmc", "%"+employeeProject.getKyxmmc()+"%"));
		}
		dc.add(Restrictions.eq(EmployeeProject.FIELD_DEL_FLAG, EmployeeProject.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeeProjectDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeeProject employeeProject) {
		employeeProjectDao.save(employeeProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeeProjectDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeeProjectDao.deleteById(id);
	}
}
