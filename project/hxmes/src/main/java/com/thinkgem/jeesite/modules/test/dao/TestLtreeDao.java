/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.test.entity.TestLtree;

/**
 * 左树右表_同一表DAO接口
 * @author LiuBJ
 * @version 2016-07-20
 */
@Repository
public class TestLtreeDao extends BaseDao<TestLtree> {

	public List<TestLtree> findByParentIdsLike(String parentIds){
		return find("from TestLtree where parentIds like :p1", new Parameter(parentIds));
	}

	public List<TestLtree> findAllList(){
		return find("from TestLtree where delFlag=:p1", new Parameter(Dict.DEL_FLAG_NORMAL));
	}	
}
