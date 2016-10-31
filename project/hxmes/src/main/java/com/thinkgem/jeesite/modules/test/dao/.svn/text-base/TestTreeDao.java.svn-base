/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author Admin
 * @version 2016-07-05
 */
@Repository
public class TestTreeDao extends BaseDao<TestTree> {
	
	public List<TestTree> findByParentIdsLike(String parentIds){
		return find("from TestTree where parentIds like :p1", new Parameter(parentIds));
	}

	public List<TestTree> findAllList(){
		return find("from TestTree where delFlag=:p1 order by sort", new Parameter(Dict.DEL_FLAG_NORMAL));
	}

	
}
