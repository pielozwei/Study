/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.test.entity.TestChild;

/**
 * 主子表DAO接口
 * @author LiuBJ
 * @version 2016-07-28
 */
@Repository
public class TestChildDao extends BaseDao<TestChild> {

	public List<TestChild> findList(String mainId){
		return find("from TestChild where delFlag = :p1 and testMain.id=:p2", new Parameter(TestChild.DEL_FLAG_NORMAL,mainId));
	}
	
}
