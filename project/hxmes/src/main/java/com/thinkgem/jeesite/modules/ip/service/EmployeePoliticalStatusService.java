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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePoliticalStatus;
import com.thinkgem.jeesite.modules.ip.dao.EmployeePoliticalStatusDao;

/**
 * 政治面貌经历Service
 * @author ks
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class EmployeePoliticalStatusService extends BaseService {

	@Autowired
	private EmployeePoliticalStatusDao employeePoliticalStatusDao;
	
	public EmployeePoliticalStatus get(String id) {
		return employeePoliticalStatusDao.get(id);
	}
	
	public Page<EmployeePoliticalStatus> find(Page<EmployeePoliticalStatus> page, EmployeePoliticalStatus employeePoliticalStatus) {
		DetachedCriteria dc = employeePoliticalStatusDao.createDetachedCriteria();
		if (employeePoliticalStatus.getEmployee()!=null && StringUtils.isNotEmpty(employeePoliticalStatus.getEmployee().getId())){
			dc.add(Restrictions.like("employee.id", "%"+employeePoliticalStatus.getEmployee().getId()+"%"));
		}
		
		dc.add(Restrictions.eq(EmployeePoliticalStatus.FIELD_DEL_FLAG, EmployeePoliticalStatus.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xl"));
		return employeePoliticalStatusDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeePoliticalStatus employeePoliticalStatus) {
		employeePoliticalStatusDao.save(employeePoliticalStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeePoliticalStatusDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		employeePoliticalStatusDao.deleteById(id);
	}
		public List<EmployeePoliticalStatus> findAll() {
		DetachedCriteria dc = employeePoliticalStatusDao.createDetachedCriteria();
		dc.add(Restrictions.eq(EmployeePoliticalStatus.FIELD_DEL_FLAG, EmployeePoliticalStatus.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xl"));
		return employeePoliticalStatusDao.find(dc);
	}
	public int getlist(EmployeePoliticalStatus employeePoliticalStatus){
		List<EmployeePoliticalStatus> list = findAll();
		
		for (int i=0; i<list.size(); i++){
			EmployeePoliticalStatus e = list.get(i);
			if(employeePoliticalStatus.getXl().equals(e.getXl())){
	
				return 0;
			}
				
		}
			return 1;
			
	}
}
