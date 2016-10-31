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
import com.thinkgem.jeesite.modules.gen.entity.GenTemplate;
import com.thinkgem.jeesite.modules.gen.dao.GenTemplateDao;

/**
 * 代码生成器Service
 * @author LiuBaoJ
 * @version 2016-05-20
 */
@Component
@Transactional(readOnly = true)
public class GenTemplateService extends BaseService {

	@Autowired
	private GenTemplateDao genTemplateDao;
	
	public GenTemplate get(String id) {
		return genTemplateDao.get(id);
	}
	
	public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
		DetachedCriteria dc = genTemplateDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(genTemplate.getName())){
			dc.add(Restrictions.like("name", "%"+genTemplate.getName()+"%"));
		}
		dc.add(Restrictions.eq(GenTemplate.FIELD_DEL_FLAG, GenTemplate.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return genTemplateDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(GenTemplate genTemplate) {
		genTemplateDao.save(genTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		genTemplateDao.deleteById(id);
	}
	
}
