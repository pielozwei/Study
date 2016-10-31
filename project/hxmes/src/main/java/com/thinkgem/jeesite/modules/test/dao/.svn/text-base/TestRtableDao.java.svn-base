/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.test.entity.TestRtable;

/**
 * 库房管理DAO接口
 * @author LiuBJ
 * @version 2016-07-20
 */
@Repository
public class TestRtableDao extends BaseDao<TestRtable> {

	public List<TestRtable> findList(){
		return find("from TestRtable where delFlag = :p1 ", new Parameter(TestRtable.DEL_FLAG_NORMAL));
	}
	
}
