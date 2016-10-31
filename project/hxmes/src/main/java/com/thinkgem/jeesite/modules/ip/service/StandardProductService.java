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
import com.thinkgem.jeesite.modules.ip.entity.StandardProduct;
import com.thinkgem.jeesite.modules.ip.dao.StandardProductDao;

/**
 * 标准产品Service
 * @author lucl
 * @version 2016-08-16
 */
@Component
@Transactional(readOnly = true)
public class StandardProductService extends BaseService {

	@Autowired
	private StandardProductDao standardProductDao;
	
	public StandardProduct get(String id) {
		return standardProductDao.get(id);
	}
	
	public Page<StandardProduct> find(Page<StandardProduct> page, StandardProduct standardProduct) {
		DetachedCriteria dc = standardProductDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(standardProduct.getCpmc())){
			dc.add(Restrictions.like("cpmc", "%"+standardProduct.getCpmc()+"%"));
		}
		dc.add(Restrictions.eq(StandardProduct.FIELD_DEL_FLAG, StandardProduct.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return standardProductDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(StandardProduct standardProduct) {
		standardProductDao.save(standardProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		standardProductDao.deleteById(id);
	}
	
}
