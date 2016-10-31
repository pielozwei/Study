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
import com.thinkgem.jeesite.modules.test.entity.TestMain;
import com.thinkgem.jeesite.modules.test.entity.TestChild;
import com.thinkgem.jeesite.modules.test.dao.TestMainDao;
import com.thinkgem.jeesite.modules.test.dao.TestChildDao;

/**
 * 主子表Service
 * @author LiuBJ
 * @version 2016-07-28
 */
@Component
@Transactional(readOnly = true)
public class TestMainService extends BaseService {

	@Autowired
	private TestMainDao testMainDao;
	@Autowired
	private TestChildDao testChildDao;
	
	public TestMain get(String id) {
		TestMain testMain = testMainDao.get(id);
		testMain.setTestChildList(testChildDao.findList(id));
		return testMain;
	}
	
	public Page<TestMain> find(Page<TestMain> page, TestMain testMain) {
		DetachedCriteria dc = testMainDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(testMain.getName())){
			dc.add(Restrictions.like("name", "%"+testMain.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(testMain.getSex())){
			dc.add(Restrictions.eq("sex", testMain.getSex()));
		}
		
		dc.add(Restrictions.eq(TestMain.FIELD_DEL_FLAG, TestMain.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return testMainDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TestMain testMain) {
		testMainDao.save(testMain);
		for (TestChild testChild : testMain.getTestChildList()){
			if (testChild.getId() == null){
				continue;
			}
			if (TestChild.DEL_FLAG_NORMAL.equals(testChild.getDelFlag())){
				if (StringUtils.isBlank(testChild.getId())){
					testChild.setTestMain(testMain);
					testChild.prePersist();
				}else{
					testChild.preUpdate();
				}
			}else{
				testChildDao.deleteById(testChild.getId());
			}
			testChildDao.save(testChild);
		}		
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		testMainDao.deleteById(id);
	}
	
}
