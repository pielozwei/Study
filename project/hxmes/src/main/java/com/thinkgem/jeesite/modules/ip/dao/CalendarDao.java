/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.Calendar;
import com.thinkgem.jeesite.modules.ip.entity.CalendarDetail;


/**
 * 工厂日历方案DAO接口
 * 
 * @author lzq
 * @version 2016-08-16
 */
@Repository
public class CalendarDao extends BaseDao<Calendar> {
	@Autowired
	private CalendarDetailDao calendarDetailDao;

	public void saves(String id,int year) throws ParseException {
		java.util.Calendar cd =java.util.Calendar.getInstance();
		DateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
		Date begindate=dateformat.parse(year+"-01-01");
		int nextyear = year+1;
		Date enddate=dateformat.parse(nextyear+"-01-01");
		Date date = begindate;
		while(!date.equals(enddate)){
		cd.setTime(date);
		CalendarDetail calendarDetail = new CalendarDetail();
		calendarDetail.setGcrlfa_id(id);
		calendarDetail.setRq(date);
		if(cd.get(java.util.Calendar.DAY_OF_WEEK)!=1&&cd.get(java.util.Calendar.DAY_OF_WEEK)!=7){
		calendarDetail.setGzrlx(0);
		calendarDetail.setSfsb(1);
		}
		else{
			calendarDetail.setGzrlx(1);
			calendarDetail.setSfsb(0);
		}
		calendarDetailDao.save(calendarDetail);
		cd.add(java.util.Calendar.DATE,1);
		date=cd.getTime();
		}
	}
	
	public boolean havecreated(Calendar calendar) {
		org.hibernate.Session sess = super.getSession();
		List list = sess.createCriteria(CalendarDetail.class) 
				.add( Restrictions.like("gcrlfa_id", calendar.getId()) ) 
				.list(); 
		if(list.isEmpty() == true){
		return false;
		}
		else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deletes(String id){
		org.hibernate.Session sess = super.getSession();
		List<CalendarDetail> list = sess.createCriteria(CalendarDetail.class) 
				.add( Restrictions.like("gcrlfa_id", id) ) 
				.list();
		for(CalendarDetail cd:list){
			calendarDetailDao.deleteById(cd.getId());
		}
	}
	
	public String getNameFromID(String id){
		
		Calendar Ca= getByHql("from Calendar where ID=:p1", new Parameter(id));
		return Ca.getFamc();

	}
}
