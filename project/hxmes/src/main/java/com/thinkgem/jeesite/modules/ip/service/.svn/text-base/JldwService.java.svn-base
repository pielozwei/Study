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
import com.thinkgem.jeesite.modules.ip.entity.Jldw;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.dao.JldwDao;

/**
 * 计量单位Service
 * @author lucl
 * @version 2016-08-10
 */
@Component
@Transactional(readOnly = true)
public class JldwService extends BaseService {
	@Autowired
	private JldwDao jldwDao;


	public Jldw get(String id) {
		return jldwDao.get(id);
	}


	public Page<Jldw> find(Page<Jldw> page, Jldw jldw) {
		DetachedCriteria dc = jldwDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(jldw.getJldwmc())) {
			dc.add(Restrictions.like("jldwmc", "%" + jldw.getJldwmc() + "%"));
		}
		dc.add(Restrictions.eq(Jldw.FIELD_DEL_FLAG, Jldw.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return jldwDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(Jldw jldw) {
		jldwDao.save(jldw);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		// jldwDao.deleteById(id);
		Jldw jldw = jldwDao.get(id);
		jldwDao.getSession().delete(jldw);
	}


	public void updateStateList(String id, String state) {
		Jldw jldw = jldwDao.get(id);
		jldw.setSfqy(Integer.parseInt(state));
		jldwDao.getSession().save(jldw);
	}


	public List<Jldw> findAll() {
		return jldwDao.find(jldwDao.createDetachedCriteria());
	}


	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_jldw where jldwbm ='"+val1+"'";
			List<Jldw> list=jldwDao.getSession().createSQLQuery(sql).addEntity(Jldw.class).list();
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
}
