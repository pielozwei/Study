/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.zsyb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.zsyb.entity.ZsybTree;

/**
 * 左树右表结构DAO接口
 * @author LiuBJ
 * @version 2016-07-14
 */
@Repository
public class ZsybTreeDao extends BaseDao<ZsybTree> {

	public List<ZsybTree> findByParentIdsLike(String parentIds){
		return find("from ZsybTree where parentIds like :p1", new Parameter(parentIds));
	}

	public List<ZsybTree> findAllList(){
		return find("from ZsybTree where delFlag=:p1", new Parameter(Dict.DEL_FLAG_NORMAL));
	}	
}
