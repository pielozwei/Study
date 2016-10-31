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
import com.thinkgem.jeesite.modules.ip.dao.MaterialServiceProviderDao;
import com.thinkgem.jeesite.modules.ip.entity.MaterialServiceProvider;

/**
 * 物料服务供应商Service
 * @author ZhangHD
 * @version 2016-06-15
 */
@Component
@Transactional(readOnly = true)
public class MaterialServiceProviderService extends BaseService {
	@Autowired
	private MaterialServiceProviderDao wlfwgysDao;


	public MaterialServiceProvider get(String id) {
		return wlfwgysDao.get(id);
	}


	public Page<MaterialServiceProvider> find(Page<MaterialServiceProvider> page, MaterialServiceProvider wlfwgys) {
		DetachedCriteria dc = wlfwgysDao.createDetachedCriteria();
		dc.createAlias("provider", "provider");
		if (wlfwgys.getProvider() != null) {
			if (StringUtils.isNotEmpty(wlfwgys.getProvider().getGysmc())) {
				dc.add(Restrictions.like("provider.gysmc", "%" + wlfwgys.getProvider().getGysmc() + "%"));
			}
		}
		dc.add(Restrictions.eq("wlbm.id", wlfwgys.getWlbm().getId()));
		dc.addOrder(Order.desc("id"));
		return wlfwgysDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(MaterialServiceProvider wlfwgys) {
		wlfwgysDao.save(wlfwgys);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		MaterialServiceProvider materialServiceProvider = (MaterialServiceProvider) wlfwgysDao.getSession().get(
				MaterialServiceProvider.class, id);
		wlfwgysDao.getSession().delete(materialServiceProvider);
	}
}
