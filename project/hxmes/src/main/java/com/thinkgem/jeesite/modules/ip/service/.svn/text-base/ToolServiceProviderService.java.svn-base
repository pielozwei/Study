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
import com.thinkgem.jeesite.modules.ip.entity.ToolServiceProvider;
import com.thinkgem.jeesite.modules.ip.dao.ToolDao;
import com.thinkgem.jeesite.modules.ip.dao.ToolServiceProviderDao;

/**
 * 服务商Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class ToolServiceProviderService extends BaseService {

	@Autowired
	private ToolServiceProviderDao toolServiceProviderDao;
	@Autowired
	private ToolDao toolDao;
	public ToolServiceProvider get(String id) {
		return toolServiceProviderDao.get(id);
	}
	
	public Page<ToolServiceProvider> find(Page<ToolServiceProvider> page, ToolServiceProvider toolServiceProvider,String toolId) {
		DetachedCriteria dc = toolServiceProviderDao.createDetachedCriteria();
		dc.createAlias("tool", "tool");
		dc.createAlias("provider", "provider");
		if (StringUtils.isNotEmpty(toolId)){
			dc.add(Restrictions.eq("tool.id", toolId));
		}
		if (toolServiceProvider.getProvider()!=null && StringUtils.isNotEmpty(toolServiceProvider.getProvider().getGysmc())){
			dc.add(Restrictions.like("provider.gysmc", "%"+toolServiceProvider.getProvider().getGysmc()+"%"));
		}
		dc.add(Restrictions.eq(ToolServiceProvider.FIELD_DEL_FLAG, ToolServiceProvider.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("provider.gysbm"));
		return toolServiceProviderDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(ToolServiceProvider toolServiceProvider) {
		toolServiceProviderDao.save(toolServiceProvider);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//toolServiceProviderDao.deleteById(id);
		ToolServiceProvider toolServiceProvider=toolServiceProviderDao.get(id);
		toolServiceProviderDao.getSession().delete(toolServiceProvider);
	}
	
	public List<ToolServiceProvider> findByToolId(String toolId) {
		DetachedCriteria dc = toolServiceProviderDao.createDetachedCriteria();
		dc.add(Restrictions.eq(ToolServiceProvider.FIELD_DEL_FLAG, ToolServiceProvider.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("tool.id", toolId));
		return toolServiceProviderDao.find(dc);
	}
	public List<ToolServiceProvider> findAll() {
		return toolServiceProviderDao.find(toolServiceProviderDao.createDetachedCriteria());
	}
}
