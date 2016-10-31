/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.text.ParseException;
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
import com.thinkgem.jeesite.modules.ip.entity.Calendar;
import com.thinkgem.jeesite.modules.ip.entity.CalendarDetail;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.dao.CalendarDao;
import com.thinkgem.jeesite.modules.ip.dao.CalendarDetailDao;

/**
 * 工厂日历方案Service
 * 
 * @author lzq
 * @version 2016-08-16
 */
@Component
@Transactional(readOnly = true)
public class CalendarService extends BaseService {

	@Autowired
	private CalendarDao calendarDao;

	public Calendar get(String id) {
		return calendarDao.get(id);
	}

	public Page<Calendar> find(Page<Calendar> page, Calendar calendar) {
		DetachedCriteria dc = calendarDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(calendar.getFamc())) {
			dc.add(Restrictions.like("famc", "%" + calendar.getFamc() + "%"));
		}
		if (calendar.getNf()!=null) {
			dc.add(Restrictions.eq("nf", calendar.getNf()));
		}
		dc.add(Restrictions.eq(Calendar.FIELD_DEL_FLAG,
				Calendar.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return calendarDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(Calendar calendar) throws ParseException {
		calendarDao.save(calendar);
		if(calendarDao.havecreated(calendar) == false){
		calendarDao.saves(calendar.getId(),calendar.getNf());
		}
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		calendarDao.deleteById(id);
		calendarDao.deletes(id);
	}
	
	public List<Calendar> findAll() {
		DetachedCriteria dcd = calendarDao.createDetachedCriteria();
		dcd.add(Restrictions.eq(Calendar.FIELD_DEL_FLAG, Calendar.DEL_FLAG_NORMAL));
		dcd.addOrder(Order.asc("id"));
		return calendarDao.find(dcd);
	}

}
