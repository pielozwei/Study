/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.mes.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mes.entity.Inventory;
import com.thinkgem.jeesite.modules.mes.dao.InventoryDao;

/**
 * 仓库Service
 * @author LiuBaoJ
 * @version 2016-05-19
 */
@Component
@Transactional(readOnly = true)
public class InventoryService extends BaseService {

	@Autowired
	private InventoryDao inventoryDao;
	
	public Inventory get(String id) {
		return inventoryDao.get(id);
	}
	
	public Page<Inventory> find(Page<Inventory> page, Inventory inventory) {
		DetachedCriteria dc = inventoryDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(inventory.getName())){
			dc.add(Restrictions.like("name", "%"+inventory.getName()+"%"));
		}
		dc.add(Restrictions.eq(Inventory.FIELD_DEL_FLAG, Inventory.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return inventoryDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Inventory inventory) {
		inventoryDao.save(inventory);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		inventoryDao.deleteById(id);
	}
	
}
