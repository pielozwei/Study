/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.ReferenceMessage;

/**
 * 参考消息DAO接口
 * @author LiHR
 * @version 2016-08-18
 */
@Repository
public class ReferenceMessageDao extends BaseDao<ReferenceMessage> {

	public ReferenceMessage findByBt(String bt) {
		return getByHql("from ReferenceMessage where bt=:p1 ", new Parameter(bt));
	}

}
