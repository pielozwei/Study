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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterCapacity;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterCapacityDao;

/**
 * 工作中心产能配置Service
 * @author lucl
 * @version 2016-06-22
 */
@Component
@Transactional(readOnly = true)
public class WorkCenterCapacityService extends BaseService {

	@Autowired
	private WorkCenterCapacityDao gzzxcnDao;
	
	public WorkCenterCapacity get(String id) {
		return gzzxcnDao.get(id);
	}
	
	public Page<WorkCenterCapacity> find(Page<WorkCenterCapacity> page, WorkCenterCapacity gzzxcn) {
		DetachedCriteria dc = gzzxcnDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(gzzxcn.getName())){
			dc.add(Restrictions.like("bzcpId", "%"+gzzxcn.getName()+"%"));
		}
		if(null!=gzzxcn&&null!=gzzxcn.getIpGzzxId()&&null!=gzzxcn.getIpGzzxId().getId()&&!gzzxcn.getIpGzzxId().equals("")){
			dc.add(Restrictions.eq("ipGzzxId.id", gzzxcn.getIpGzzxId().getId()));
		}
		dc.add(Restrictions.eq(WorkCenterCapacity.FIELD_DEL_FLAG, WorkCenterCapacity.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return gzzxcnDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkCenterCapacity gzzxcn) {
		gzzxcnDao.save(gzzxcn);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//gzzxcnDao.deleteById(id);
		WorkCenterCapacity workCenterCapacity=gzzxcnDao.get(id);
		gzzxcnDao.getSession().delete(workCenterCapacity);
	}
	
}
