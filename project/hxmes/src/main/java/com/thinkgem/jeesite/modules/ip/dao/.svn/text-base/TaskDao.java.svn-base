/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.Task;

/**
 * 工作任务DAO接口
 * @author LiHR
 * @version 2016-08-15
 */
@Repository
public class TaskDao extends BaseDao<Task> {

	public Task findByName(String name) {
		return getByHql("from Task where gzrwmc=:p1 ", new Parameter(name));
	}
	
}
