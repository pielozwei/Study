/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.gen.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.gen.entity.GenTableColumn;

/**
 * 代码生成DAO接口
 * @author LiuBJ
 * @version 2016-06-07
 */
@Repository
public class GenTableColumnDao extends BaseDao<GenTableColumn> {
	public List<GenTableColumn> findList(String genTableId){
		return find("from GenTableColumn where delFlag = :p1 and genTable.id = :p2", new Parameter(GenTableColumn.DEL_FLAG_NORMAL,genTableId));
	}
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteByGenTableId(String genTableId){
		return update("update GenTableColumn set del_flag = '" + BaseEntity.DEL_FLAG_DELETE + "' where gen_table_id = :p1",new Parameter(genTableId));
	}	
}
