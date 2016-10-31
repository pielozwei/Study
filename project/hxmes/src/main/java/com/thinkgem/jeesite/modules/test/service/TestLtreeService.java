/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.service;

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
import com.thinkgem.jeesite.modules.test.entity.TestLtree;
import com.thinkgem.jeesite.modules.test.dao.TestLtreeDao;

/**
 * 左树右表_同一表Service
 * @author LiuBJ
 * @version 2016-07-20
 */
@Component
@Transactional(readOnly = true)
public class TestLtreeService extends BaseService {

	@Autowired
	private TestLtreeDao testLtreeDao;
	
	public TestLtree getTestLtree(String id) {
		return testLtreeDao.get(id);
	}
	
	public List<TestLtree> findAllTestLtree(){
		return testLtreeDao.findAllList();
	}
	
	public Page<TestLtree> find(Page<TestLtree> page, TestLtree testLtree) {
		DetachedCriteria dc = testLtreeDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(testLtree.getName())){
			dc.add(Restrictions.like("name", "%"+testLtree.getName()+"%"));
		}
		if (StringUtils.isNotEmpty(testLtree.getId())){
			dc.add(Restrictions.or(Restrictions.like("parentIds","%"+testLtree.getId()+"%"),Restrictions.eqOrIsNull("id", testLtree.getId())));
		}
		
		dc.add(Restrictions.eq(TestLtree.FIELD_DEL_FLAG, TestLtree.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return testLtreeDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void saveTestLtree(TestLtree testLtree) {
		testLtree.setParent(this.getTestLtree(testLtree.getParent().getId()));
		String oldParentIds = testLtree.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		testLtree.setParentIds(testLtree.getParent().getParentIds()+testLtree.getParent().getId()+",");
		testLtreeDao.clear();
		testLtreeDao.save(testLtree);
		// 更新子节点 parentIds
		List<TestLtree> list = testLtreeDao.findByParentIdsLike("%,"+testLtree.getId()+",%");
		for (TestLtree e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, testLtree.getParentIds()));
		}
		testLtreeDao.save(list);
	}

	@Transactional(readOnly = false)
	public void deleteTestLtree(String id) {
		testLtreeDao.deleteById(id, "%,"+id+",%");
	}
}
