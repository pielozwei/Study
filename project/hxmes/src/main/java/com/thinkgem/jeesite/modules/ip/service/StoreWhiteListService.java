/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.ip.entity.StoreWhiteList;
import com.thinkgem.jeesite.modules.ip.dao.StoreWhiteListDao;

/**
 * 仓库白名单Service
 * @author Lucl
 * @version 2016-06-15
 */
@Component
@Transactional(readOnly = true)
public class StoreWhiteListService extends BaseService {

	@Autowired
	private StoreWhiteListDao ckbmdDao;
	
	public StoreWhiteList get(String id) {
		return ckbmdDao.get(id);
	}
	
	public Page<StoreWhiteList> find(Page<StoreWhiteList> page, StoreWhiteList ckbmd,HttpServletRequest request) {
		DetachedCriteria dc = ckbmdDao.createDetachedCriteria();
		dc.createAlias("ipCkCkbh", "ipCkCkbh");
		if (null!=ckbmd&&null!=ckbmd.getName()&&!ckbmd.getName().equals("")){
			dc.add(Restrictions.like("ipCkCkbh.ckbh", "%"+ckbmd.getName()+"%"));
		}
		if(null!=ckbmd&&null!=ckbmd.getIpCkCkbh()&&!ckbmd.getIpCkCkbh().equals("")){
			dc.add(Restrictions.eq("ipCkCkbh.id",ckbmd.getIpCkCkbh().getId()));
		}
		dc.add(Restrictions.eq(StoreWhiteList.FIELD_DEL_FLAG, StoreWhiteList.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("ipCkCkbh.ckbh"));
		return ckbmdDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(StoreWhiteList ckbmd) {
		ckbmdDao.clear();
		ckbmdDao.save(ckbmd);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//ckbmdDao.deleteById(id);
		StoreWhiteList storeWhiteList=(StoreWhiteList) ckbmdDao.getSession().get(StoreWhiteList.class, id);
		ckbmdDao.getSession().delete(storeWhiteList);
	}
	
}
