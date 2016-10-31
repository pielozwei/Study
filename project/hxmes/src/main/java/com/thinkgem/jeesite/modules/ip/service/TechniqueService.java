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
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentField;
import com.thinkgem.jeesite.modules.ip.entity.MaterialCategory;
import com.thinkgem.jeesite.modules.ip.entity.Technique;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.web.materialIndexController;
import com.thinkgem.jeesite.modules.ip.dao.MaterialCategoryDao;
import com.thinkgem.jeesite.modules.ip.dao.MaterialDao;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueDao;

/**
 * 工艺路线基本信息Service
 * @author zzc
 * @version 2016-06-27
 */
@Component
@Transactional(readOnly = true)
public class TechniqueService extends BaseService {

	@Autowired
	private TechniqueDao techniqueDao;
	
	@Autowired
	private MaterialDao materialDao;
	
	public Technique get(String id) {
		return techniqueDao.get(id);
	}
	
	public Page<Technique> find(Page<Technique> page, Technique technique) {
		DetachedCriteria dc = techniqueDao.createDetachedCriteria();
		if (technique.getMaterial() != null && StringUtils.isNotEmpty(technique.getMaterial().getId())){
			dc.add(Restrictions.like("material.id","%"+technique.getMaterial().getId()+"%"));
			dc.add(Restrictions.eq("sfzgy",technique.getSfzgy()));
			dc.add(Restrictions.eq("jdlx",technique.getJdlx()));
			dc.add(Restrictions.eq("sfqy",technique.getSfqy()));
			
		}
		else if(technique.getSfqy()!=null){
			dc.add(Restrictions.eq("sfzgy",technique.getSfzgy()));
			dc.add(Restrictions.eq("sfqy",technique.getSfqy()));
			dc.add(Restrictions.eq("jdlx",technique.getJdlx()));
		}
		dc.add(Restrictions.eq(Technique.FIELD_DEL_FLAG, Technique.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("jdlx",technique.getJdlx()));
		dc.addOrder(Order.desc("id"));
		return techniqueDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Technique technique) {
		techniqueDao.clear();
		techniqueDao.save(technique);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueDao.deleteById(id);
	}
	
	@Transactional(readOnly = false)
	public void deletes(String id) {
		techniqueDao.deleteById(id);
	}
	
	public List<Technique> findAll() {
		DetachedCriteria dc = techniqueDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Technique.FIELD_DEL_FLAG, Technique.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return techniqueDao.find(dc);
	}
	
	public boolean validation(String val1,String val2) {
		if(null!=val1&&!val1.equals("")){
			
			String sql="select * from IP_GYLX where "+val1+"='"+val2+"'and DEL_FLAG=0";
			if(val1=="IP_WLBM_ID")
			{
				sql+="and SFZGY=1";
			}
		
			List<Technique> list=techniqueDao.getSession().createSQLQuery(sql).addEntity(Technique.class).list();
			
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		
		}
		else{return true;}
	}
}
