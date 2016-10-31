/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.test.entity.TestDataMain;

/**
 * 主子表参考用例DAO接口
 * @author LiuBJ
 * @version 2016-07-21
 */
@Repository
public class TestDataMainDao extends BaseDao<TestDataMain> {

	public List<TestDataMain> findList(){
		return find("from TestDataMain where delFlag = :p1 ", new Parameter(TestDataMain.DEL_FLAG_NORMAL));
	}
	
}
