/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.TestRtable;
import com.thinkgem.jeesite.modules.test.dao.TestRtableDao;

/**
 * 库房管理Service
 * @author LiuBJ
 * @version 2016-07-20
 */
@Component
@Transactional(readOnly = true)
public class TestRtableService extends BaseService {

	@Autowired
	private TestRtableDao testRtableDao;
	
	public TestRtable get(String id) {
		return testRtableDao.get(id);
	}
	
	public Page<TestRtable> find(Page<TestRtable> page, TestRtable testRtable) {
		DetachedCriteria dc = testRtableDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(testRtable.getToolNo())){
			dc.add(Restrictions.eq("toolNo", testRtable.getToolNo()));
		}
		if (StringUtils.isNotEmpty(testRtable.getName())){
			dc.add(Restrictions.like("name", "%"+testRtable.getName()+"%"));
		}
		
		dc.add(Restrictions.eq(TestRtable.FIELD_DEL_FLAG, TestRtable.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return testRtableDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TestRtable testRtable) {
		testRtableDao.save(testRtable);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		testRtableDao.deleteById(id);
	}
	
}
