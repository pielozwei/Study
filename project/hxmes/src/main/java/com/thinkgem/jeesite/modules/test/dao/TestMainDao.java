/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.test.entity.TestMain;

/**
 * 主子表DAO接口
 * @author LiuBJ
 * @version 2016-07-28
 */
@Repository
public class TestMainDao extends BaseDao<TestMain> {

	public List<TestMain> findList(){
		return find("from TestMain where delFlag = :p1 ", new Parameter(TestMain.DEL_FLAG_NORMAL));
	}
	
}
