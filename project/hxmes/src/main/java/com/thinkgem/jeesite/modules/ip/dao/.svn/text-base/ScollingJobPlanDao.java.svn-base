/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlan;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 滚动工作计划DAO接口
 * @author LiHR
 * @version 2016-08-09
 */
@Repository
public class ScollingJobPlanDao extends BaseDao<ScollingJobPlan> {
	
	public int deleteById(Serializable id){
		return update("delete from ScollingJobPlan" + " where id = :p1", new Parameter(id));
	}

	public Date getMaxJhjzsj(String workCenterId, String currentId) {
		clear();
		User user = UserUtils.getUser();
		String userId = user.getId();
		String qlString = "";
		Query query = null;
		if (StringUtils.isBlank(currentId)) {
			qlString = "select MAX(jhjzsj) from ScollingJobPlan" + " where (jhfzrId = :p1 or jhxzrId = :p1) and workCenter.id = :p2";
			query = getSession().createQuery(qlString);
			query.setString("p1", userId);
			query.setString("p2", workCenterId);
		} else {
			qlString = "select MAX(jhjzsj) from ScollingJobPlan" + " where (jhfzrId = :p1 or jhxzrId = :p1) and workCenter.id = :p2" +
						" and id != :p3";
			query = getSession().createQuery(qlString);
			query.setString("p1", userId);
			query.setString("p2", workCenterId);
			query.setString("p3", currentId);
		}
		
		return (Date) query.uniqueResult();
	}

	public ScollingJobPlan findByJhbh(String jhbh) {
		return getByHql("from ScollingJobPlan where jhbh=:p1 ", new Parameter(jhbh));
	}
}
