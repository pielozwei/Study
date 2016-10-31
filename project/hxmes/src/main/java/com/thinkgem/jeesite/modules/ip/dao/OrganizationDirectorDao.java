/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.OrganizationDirector;

/**
 * 分管单位设置DAO接口
 * @author Generate Tools
 * @version 2016-06-21
 */
@Repository
public class OrganizationDirectorDao extends BaseDao<OrganizationDirector> {
	
	public int deleteByZGId(String zgid){
		int i = update("update OrganizationDirector set delFlag='" + OrganizationDirector.DEL_FLAG_DELETE + 
				"' where ZG_ID = :p1",new Parameter(zgid));
		return i;
	}
}
