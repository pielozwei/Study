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
import com.thinkgem.jeesite.modules.ip.entity.StoreBlackList;
import com.thinkgem.jeesite.modules.ip.dao.StoreBlackListDao;

/**
 * 仓库黑名单Service
 * @author Lucl
 * @version 2016-06-17
 */
@Component
@Transactional(readOnly = true)
public class StoreBlackListService extends BaseService {

	@Autowired
	private StoreBlackListDao ckhmdDao;
	
	public StoreBlackList get(String id) {
		return ckhmdDao.get(id);
	}
	
	public Page<StoreBlackList> find(Page<StoreBlackList> page, StoreBlackList ckhmd) {
		DetachedCriteria dc = ckhmdDao.createDetachedCriteria();
		/*if (StringUtils.isNotEmpty(ckhmd.getName())){
			dc.add(Restrictions.like("ipCkCkbh", "%"+ckhmd.getName()+"%"));
		}*/
		dc.createAlias("ipCkCkbh", "ipCkCkbh");
		if(null!=ckhmd&&null!=ckhmd.getName()&&!ckhmd.getName().equals("")){
			dc.add(Restrictions.like("ipCkCkbh.ckbh", ckhmd.getName()));
		}
		if(null!=ckhmd&&null!=ckhmd.getIpCkCkbh()&&!ckhmd.getIpCkCkbh().equals("")){
			dc.add(Restrictions.eq("ipCkCkbh.id",ckhmd.getIpCkCkbh().getId()));
		}
		dc.add(Restrictions.eq(StoreBlackList.FIELD_DEL_FLAG, StoreBlackList.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("ipCkCkbh.ckbh"));
		return ckhmdDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(StoreBlackList ckhmd) {
		ckhmdDao.save(ckhmd);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//ckhmdDao.deleteById(id);
		StoreBlackList storeBlackList = ckhmdDao.get(id);
		ckhmdDao.getSession().delete(storeBlackList);
	}
	
}
