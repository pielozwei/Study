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
import com.thinkgem.jeesite.modules.ip.entity.CalendarDetail;
import com.thinkgem.jeesite.modules.ip.dao.CalendarDetailDao;

/**
 * 工厂日历明细Service
 * @author lzq
 * @version 2016-08-16
 */
@Component
@Transactional(readOnly = true)
public class CalendarDetailService extends BaseService {

	@Autowired
	private CalendarDetailDao calendarDetailDao;
	
	public CalendarDetail get(String id) {
		return calendarDetailDao.get(id);
	}
	
	public Page<CalendarDetail> find(Page<CalendarDetail> page, CalendarDetail calendarDetail) {
		DetachedCriteria dc = calendarDetailDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(calendarDetail.getGcrlfa_id())){
			dc.add(Restrictions.like("gcrlfa_id", "%"+calendarDetail.getGcrlfa_id()+"%"));
		}
		if (calendarDetail.getRq() != null){
			dc.add(Restrictions.between("rq", calendarDetail.getRq(), calendarDetail.getRq()));
		}
		dc.add(Restrictions.eq(CalendarDetail.FIELD_DEL_FLAG, CalendarDetail.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("rq"));
		return calendarDetailDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(CalendarDetail calendarDetail) {
		calendarDetailDao.save(calendarDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		calendarDetailDao.deleteById(id);
	}
	
}
