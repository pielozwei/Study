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
import com.thinkgem.jeesite.modules.ip.entity.OrganizationPositionRank;
import com.thinkgem.jeesite.modules.ip.web.OrganizationPositionController;
import com.thinkgem.jeesite.modules.ip.dao.OrganizationPositionRankDao;

/**
 * 人员业务分类管理Service
 * @author Iris
 * @version 2016-06-21
 */
@Component
@Transactional(readOnly = true)
public class OrganizationPositionRankService extends BaseService {

	@Autowired
	private OrganizationPositionRankDao organizationPositionRankDao;
	
	public OrganizationPositionRank get(String id) {
		return organizationPositionRankDao.get(id);
	}
	
	public Page<OrganizationPositionRank> find(Page<OrganizationPositionRank> page, OrganizationPositionRank organizationPositionRank) {
		DetachedCriteria dc = organizationPositionRankDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(organizationPositionRank.getZZJG_ID())){
			dc.add(Restrictions.like("ZZJG_ID", "%"+organizationPositionRank.getZZJG_ID()+"%"));
		}
		dc.add(Restrictions.eq(OrganizationPositionRank.FIELD_DEL_FLAG, OrganizationPositionRank.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return organizationPositionRankDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(OrganizationPositionRank organizationPositionRank) {
		organizationPositionRankDao.save(organizationPositionRank);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		organizationPositionRankDao.deleteById(id);
	}

	public List<OrganizationPositionRank> findAll() {
		DetachedCriteria dc = organizationPositionRankDao.createDetachedCriteria();
		dc.add(Restrictions.eq(OrganizationPositionRank.FIELD_DEL_FLAG, OrganizationPositionRank.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return organizationPositionRankDao.find(dc);
	}
	
}
