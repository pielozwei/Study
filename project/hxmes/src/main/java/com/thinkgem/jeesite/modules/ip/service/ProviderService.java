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
import com.thinkgem.jeesite.modules.ip.entity.Equipment;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentCategory;
import com.thinkgem.jeesite.modules.ip.entity.Provider;
import com.thinkgem.jeesite.modules.ip.dao.ProviderDao;

/**
 * 供应商基本信息Service
 * @author ZhangHD
 * @version 2016-06-22
 */
@Component
@Transactional(readOnly = true)
public class ProviderService extends BaseService {
	@Autowired
	private ProviderDao providerDao;


	public Provider get(String id) {
		return providerDao.get(id);
	}


	public Page<Provider> find(Page<Provider> page, Provider provider) {
		DetachedCriteria dc = providerDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(provider.getGysmc())) {
			dc.add(Restrictions.like("gysmc", "%" + provider.getGysmc() + "%"));
		}
		dc.add(Restrictions.eq(Provider.FIELD_DEL_FLAG, Provider.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("xssx"));
		return providerDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(Provider provider) {
		providerDao.save(provider);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		Provider provider = (Provider) providerDao.getSession().get(Provider.class, id);
		providerDao.getSession().delete(provider);
	}


	public Provider findByGysbm(String gysbm) {
		return providerDao.findByGysbm(gysbm);
	}


	public Provider updateState(Provider provider, String state) {
		provider.setSfqy(Integer.parseInt(state));
		providerDao.getSession().save(provider);
		return null;
	}
	public List<Provider> findAll() {
		return providerDao.find(providerDao.createDetachedCriteria());
	}
}
