/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.modules.ip.entity.MaterialServiceProvider;
import com.thinkgem.jeesite.modules.ip.entity.Provider;

/**
 * 物料服务供应商DAO接口
 * @author ZhangHD
 * @version 2016-06-15
 */
@Repository
public class MaterialServiceProviderDao extends BaseDao<MaterialServiceProvider> {
	public List<Provider> findFwgysId(String name){
		return find(" from Provider where gysmc like '%"+name+"%' and delFlag='0'");
	}
}
