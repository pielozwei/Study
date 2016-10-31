/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.quality.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.quality.entity.ZjWttzd;

/**
 * 质量问题通知单DAO接口
 * @author LiuBJ
 * @version 2016-08-25
 */
@Repository
public class ZjWttzdDao extends BaseDao<ZjWttzd> {

	public List<ZjWttzd> findList(){
		return find("from ZjWttzd where delFlag = :p1 ", new Parameter(ZjWttzd.DEL_FLAG_NORMAL));
	}
	
}
