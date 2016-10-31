/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.test.entity.TestTree;
import com.thinkgem.jeesite.modules.test.dao.TestTreeDao;

/**
 * 树结构生成Service
 * @author Admin
 * @version 2016-07-05
 */
@Component
@Transactional(readOnly = true)
public class TestTreeService extends BaseService {

	@Autowired
	private TestTreeDao testTreeDao;
	
	public TestTree getTestTree(String id) {
		return testTreeDao.get(id);
	}

	public List<TestTree> findAllTestTree(){
		return testTreeDao.findAllList();
	}
	
	@Transactional(readOnly = false)
	public void saveTestTree(TestTree testTree) {
		testTree.setParent(this.getTestTree(testTree.getParent().getId()));
		String oldParentIds = testTree.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		testTree.setParentIds(testTree.getParent().getParentIds()+testTree.getParent().getId()+",");
		testTreeDao.clear();
		testTreeDao.save(testTree);
		// 更新子节点 parentIds
		List<TestTree> list = testTreeDao.findByParentIdsLike("%,"+testTree.getId()+",%");
		for (TestTree e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, testTree.getParentIds()));
		}
		testTreeDao.save(list);
	}

	@Transactional(readOnly = false)
	public void deleteTestTree(String id) {
		testTreeDao.deleteById(id, "%,"+id+",%");
	}
}
