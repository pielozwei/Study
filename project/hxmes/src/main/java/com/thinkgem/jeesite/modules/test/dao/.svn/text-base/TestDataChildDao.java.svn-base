/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.test.entity.TestDataChild;

/**
 * 子表参考用例DAO接口
 * @author LiuBJ
 * @version 2016-07-21
 */
@Repository
public class TestDataChildDao extends BaseDao<TestDataChild> {

	public List<TestDataChild> findList(String testDataMainId){
		return find("from TestDataChild where delFlag = :p1 and testDataMain.id=:p2", new Parameter(TestDataChild.DEL_FLAG_NORMAL,testDataMainId));
	}
	
}
