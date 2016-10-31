/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.gen.entity.GenTable;

/**
 * 业务表配置DAO接口
 * @author LiuBJ
 * @version 2016-06-07
 */
@Repository
public class GenTableDao extends BaseDao<GenTable> {
	
	public List<GenTable> findByParentTableName(String parentName){
		return find("from GenTable where delFlag = :p1 and parentTable = :p2", new Parameter(GenTable.DEL_FLAG_NORMAL, parentName));
	}
}
