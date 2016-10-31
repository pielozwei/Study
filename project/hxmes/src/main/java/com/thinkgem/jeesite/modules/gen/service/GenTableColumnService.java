/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;
import com.thinkgem.jeesite.modules.gen.dao.GenTableColumnDao;

/**
 * 代码生成Service
 * @author LiuBJ
 * @version 2016-06-07
 */
@Component
@Transactional(readOnly = true)
public class GenTableColumnService extends BaseService {

	@Autowired
	private GenTableColumnDao genTableColumnDao;
	
	public GenTableColumn get(String id) {
		return genTableColumnDao.get(id);
	}
	
	public Page<GenTableColumn> find(Page<GenTableColumn> page, GenTableColumn genTableColumn) {
		DetachedCriteria dc = genTableColumnDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(genTableColumn.getName())){
			dc.add(Restrictions.like("name", "%"+genTableColumn.getName()+"%"));
		}
		dc.add(Restrictions.eq(GenTableColumn.FIELD_DEL_FLAG, GenTableColumn.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("sort"));
		return genTableColumnDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(GenTableColumn genTableColumn) {
		genTableColumnDao.save(genTableColumn);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		genTableColumnDao.deleteById(id);
	}
	
}
