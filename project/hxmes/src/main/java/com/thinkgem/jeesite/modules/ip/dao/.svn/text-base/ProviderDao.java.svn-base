/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.Provider;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 供应商基本信息DAO接口
 * @author ZhangHD
 * @version 2016-06-22
 */
@Repository
public class ProviderDao extends BaseDao<Provider> {
	/**
	 * @Title: findByGysbm
	 * @Description: TODO
	 * @param gysbm
	 * @return
	 * @return: Provider
	 */
	public Provider findByGysbm(String gysbm) {
		return getByHql("from Provider Where gysbm =:p1 And delFlag =:p2", new Parameter(gysbm, User.DEL_FLAG_NORMAL));
	}
}
