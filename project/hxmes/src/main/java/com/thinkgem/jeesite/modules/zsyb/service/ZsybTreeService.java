/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.zsyb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.zsyb.entity.ZsybTree;
import com.thinkgem.jeesite.modules.zsyb.dao.ZsybTreeDao;

/**
 * 左树右表结构Service
 * @author LiuBJ
 * @version 2016-07-14
 */
@Component
@Transactional(readOnly = true)
public class ZsybTreeService extends BaseService {

	@Autowired
	private ZsybTreeDao zsybTreeDao;
	
	public ZsybTree getZsybTree(String id) {
		return zsybTreeDao.get(id);
	}
	
	public List<ZsybTree> findAllZsybTree(){
		return zsybTreeDao.findAllList();
	}

	@Transactional(readOnly = false)
	public void saveZsybTree(ZsybTree zsybTree) {
		zsybTree.setParent(this.getZsybTree(zsybTree.getParent().getId()));
		String oldParentIds = zsybTree.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		zsybTree.setParentIds(zsybTree.getParent().getParentIds()+zsybTree.getParent().getId()+",");
		zsybTreeDao.clear();
		zsybTreeDao.save(zsybTree);
		// 更新子节点 parentIds
		List<ZsybTree> list = zsybTreeDao.findByParentIdsLike("%,"+zsybTree.getId()+",%");
		for (ZsybTree e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, zsybTree.getParentIds()));
		}
		zsybTreeDao.save(list);
	}

	@Transactional(readOnly = false)
	public void deleteZsybTree(String id) {
		zsybTreeDao.deleteById(id, "%,"+id+",%");
	}
}
