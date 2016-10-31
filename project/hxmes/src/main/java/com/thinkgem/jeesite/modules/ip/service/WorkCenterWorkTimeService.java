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
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterWorkTime;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterWorkTimeDao;

/**
 * 工作中心班次配置Service
 * @author lucl
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class WorkCenterWorkTimeService extends BaseService {
	public static final String CACHE_GZZXWH_LIST = "gzzxwhList";
	@Autowired
	private WorkCenterWorkTimeDao gzzxbcDao;
	
	/*@Autowired GzzxwhDao gzzxwhDao;*/
	
	public WorkCenterWorkTime get(String id) {
		return gzzxbcDao.get(id);
	}
	
	public Page<WorkCenterWorkTime> find(Page<WorkCenterWorkTime> page, WorkCenterWorkTime gzzxbc) {
		DetachedCriteria dc = gzzxbcDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(gzzxbc.getName())){
			dc.add(Restrictions.like("bm", "%"+gzzxbc.getName()+"%"));
		}
		if(null!=gzzxbc&&null!=gzzxbc.getGzbzId()&&null!=gzzxbc.getGzbzId().getId()&&!gzzxbc.getGzbzId().equals("")){
			dc.add(Restrictions.eq("gzbzId.id", gzzxbc.getGzbzId().getId()));
		}
		dc.add(Restrictions.eq(WorkCenterWorkTime.FIELD_DEL_FLAG, WorkCenterWorkTime.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return gzzxbcDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkCenterWorkTime gzzxbc) {
		gzzxbcDao.save(gzzxbc);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//gzzxbcDao.deleteById(id);
		WorkCenterWorkTime workCenterWorkTime = gzzxbcDao.get(id);
		gzzxbcDao.getSession().delete(workCenterWorkTime);
	}

	
}
