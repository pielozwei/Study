/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.EmployeeContact;

/**
 * 个人信息DAO接口
 * @author cml
 * @version 2016-06-21
 */
@Repository
public class EmployeeContactDao extends BaseDao<EmployeeContact> {
	
	public EmployeeContact getEmployeeContactfromZGId(String Id){

		return getByHql("from EmployeeContact where IP_ZGGRXX_ID=:p1", new Parameter(Id));

	}
}
