/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.Date;
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
import com.thinkgem.jeesite.modules.ip.entity.CalendarDetail;
import com.thinkgem.jeesite.modules.ip.entity.CalendarMaintain;
import com.thinkgem.jeesite.modules.ip.dao.CalendarDao;
import com.thinkgem.jeesite.modules.ip.dao.CalendarDetailDao;
import com.thinkgem.jeesite.modules.ip.dao.CalendarMaintainDao;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterDao;

/**
 * 工厂日历维护Service
 * @author yrd
 * @version 2016-08-16
 */
@Component
@Transactional(readOnly = true)
public class CalendarMaintainService extends BaseService {

	@Autowired
	private CalendarMaintainDao calendarMaintainDao;
	@Autowired
	private CalendarDetailDao calendarDetailDao;
	@Autowired
	private CalendarDao calendarDao;
	@Autowired
	private WorkCenterDao workcenterDao;
	
	public CalendarMaintain get(String id) {
		return calendarMaintainDao.get(id);
	}
	
	public Page<CalendarMaintain> find(Page<CalendarMaintain> page, CalendarMaintain calendarMaintain) {
		DetachedCriteria dc = calendarMaintainDao.createDetachedCriteria();
		if (calendarMaintain.getWorkcenter() != null && StringUtils.isNotEmpty(calendarMaintain.getWorkcenter().getId())){
			dc.add(Restrictions.like("workcenter.id", "%"+calendarMaintain.getWorkcenter().getId()+"%"));
		}
		dc.add(Restrictions.eq(CalendarMaintain.FIELD_DEL_FLAG, CalendarMaintain.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("rq"));
		return calendarMaintainDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(CalendarMaintain calendarMaintain) {
		calendarMaintainDao.save(calendarMaintain);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		calendarMaintainDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void saveall(String GZZXId,String FALYId) {
		//1、删除已有的数据 deleteBygzzxid
		calendarMaintainDao.deleteBygzzxid(GZZXId);
		//2、获取方案名称对应的方案明细数据
		DetachedCriteria dc = calendarDetailDao.createDetachedCriteria();
		dc.add(Restrictions.like("gcrlfa_id", "%"+FALYId+"%"));
		dc.add(Restrictions.eq(CalendarDetail.FIELD_DEL_FLAG, CalendarDetail.DEL_FLAG_NORMAL));
		List<CalendarDetail> CDList= calendarDetailDao.find(dc);	
		
		for(CalendarDetail e : CDList){
			CalendarMaintain calendarMaintain = new CalendarMaintain();
			//calendarMaintain.gzxx_id = GZZXId;
			calendarMaintain.setWorkcenter(workcenterDao.get(GZZXId));
			calendarMaintain.rq=e.getRq();
			calendarMaintain.gzrlx=e.getGzrlx();
			calendarMaintain.sfsb=e.getSfsb();
			calendarMaintain.bc=1;
			calendarMaintain.bm="00";
			calendarMaintain.bcxz=1;
			calendarMaintain.bcsfsb=1;
			calendarMaintain.faly=calendarDao.getNameFromID(e.getGcrlfa_id());
			calendarMaintain.bz=null;
			calendarMaintainDao.save(calendarMaintain);
		}
	}
	
}
