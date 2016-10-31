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
import com.thinkgem.jeesite.modules.ip.entity.EmployeePositionRankDirector;
import com.thinkgem.jeesite.modules.ip.dao.EmployeePositionRankDirectorDao;

/**
 * 人员业务类别Service
 * @author yrd
 * @version 2016-09-01
 */
@Component
@Transactional(readOnly = true)
public class EmployeePositionRankDirectorService extends BaseService {

	@Autowired
	private EmployeePositionRankDirectorDao employeePositionRankDirectorDao;
	
	public EmployeePositionRankDirector get(String id) {
		return employeePositionRankDirectorDao.get(id);
	}
	
	public Page<EmployeePositionRankDirector> find(Page<EmployeePositionRankDirector> page, EmployeePositionRankDirector employeePositionRankDirector) {
		DetachedCriteria dc = employeePositionRankDirectorDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(employeePositionRankDirector.getId())){
			dc.add(Restrictions.like("id", "%"+employeePositionRankDirector.getId()+"%"));
		}
		dc.add(Restrictions.eq(EmployeePositionRankDirector.FIELD_DEL_FLAG, EmployeePositionRankDirector.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return employeePositionRankDirectorDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(EmployeePositionRankDirector employeePositionRankDirector) {
		employeePositionRankDirectorDao.save(employeePositionRankDirector);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		employeePositionRankDirectorDao.deleteById(id);
	}
	
}
