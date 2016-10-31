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
import com.thinkgem.jeesite.modules.test.entity.TestDataChild;
import com.thinkgem.jeesite.modules.test.entity.TestDataMain;
import com.thinkgem.jeesite.modules.test.dao.TestDataChildDao;
import com.thinkgem.jeesite.modules.test.dao.TestDataMainDao;

/**
 * 主子表参考用例Service
 * @author LiuBJ
 * @version 2016-07-21
 */
@Component
@Transactional(readOnly = true)
public class TestDataMainService extends BaseService {

	@Autowired
	private TestDataMainDao testDataMainDao;
	@Autowired
	private TestDataChildDao testDataChildDao;
	
	public TestDataMain get(String id) {
		TestDataMain testDataMain = testDataMainDao.get(id);
		testDataMain.setTestDataChildList(testDataChildDao.findList(id));
		return testDataMain;
	}
	
	public Page<TestDataMain> find(Page<TestDataMain> page, TestDataMain testDataMain) {
		DetachedCriteria dc = testDataMainDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(testDataMain.getName())){
			dc.add(Restrictions.like("name", "%"+testDataMain.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(testDataMain.getSex())){
			dc.add(Restrictions.eq("sex", testDataMain.getSex()));
		}
		
		dc.add(Restrictions.eq(TestDataMain.FIELD_DEL_FLAG, TestDataMain.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return testDataMainDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TestDataMain testDataMain) {
		testDataMainDao.save(testDataMain);
		
		for (TestDataChild testDataChild : testDataMain.getTestDataChildList()){
			if (testDataChild.getId() == null){
				continue;
			}
			if (TestDataChild.DEL_FLAG_NORMAL.equals(testDataChild.getDelFlag())){
				if (StringUtils.isBlank(testDataChild.getId())){
					testDataChild.setTestDataMain(testDataMain);
					testDataChild.prePersist();
					//testDataChildDao.insert(testDataChild);
				}else{
					testDataChild.preUpdate();
					//testDataChildDao.update(testDataChild);
				}
			}else{
				testDataChildDao.deleteById(testDataChild.getId());
			}
			testDataChildDao.save(testDataChild);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		testDataMainDao.deleteById(id);
	}
	
}
