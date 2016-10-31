/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlan;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.util.ScollingJobPlanUtil;
import com.thinkgem.jeesite.modules.ip.dao.ScollingJobPlanTaskDao;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterDao;
import com.thinkgem.jeesite.modules.ip.dao.ScollingJobPlanDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 滚动工作计划Controller
 * @author LiHR
 * @version 2016-08-09
 */
@Component
@Transactional(readOnly = true)
public class ScollingJobPlanService extends BaseService {

	@Autowired
	private ScollingJobPlanDao scollingJobPlanDao;
	@Autowired
	private WorkCenterDao workCenterDao;
	@Autowired
	private ScollingJobPlanTaskDao scollingJobPlanTaskDao;
	@Autowired
	private UserDao userDao;
	
	public ScollingJobPlan get(String id) {
		return scollingJobPlanDao.get(id);
	}
	
	public Page<ScollingJobPlan> findHost(Page<ScollingJobPlan> page, ScollingJobPlan scollingJobPlan,String userId) {
		DetachedCriteria dc = scollingJobPlanDao.createDetachedCriteria();
		//dc.createAlias("workCenter", "workCenter");
		/*if (StringUtils.isNotEmpty(userId)){
			dc.add(Restrictions.or(Restrictions.eq("jhfzrId", userId), Restrictions.eq("jhxzrId", userId)));
		}*/
		dc.add(Restrictions.in("workCenter.id", ScollingJobPlanUtil.selectWorkCenterIdsByUserId(userId)));
		dc.add(Restrictions.eq("fbzt", 1));
		if (StringUtils.isNotEmpty(scollingJobPlan.getJhmc())){
			dc.add(Restrictions.like("jhmc", "%"+scollingJobPlan.getJhmc()+"%"));
		}
		//dc.add(Restrictions.lt("jhjzsj", DateUtils.getDateStart(new Date())));
		//dc.addOrder(Order.asc("workCenter.gzzxbm"));
		dc.addOrder(Order.desc("jhkssj"));
		return scollingJobPlanDao.find(page, dc);
	}
	
	public Page<ScollingJobPlan> find(Page<ScollingJobPlan> page, ScollingJobPlan scollingJobPlan,String userId) {
		//计划处于未发布状态
		DetachedCriteria dc = scollingJobPlanDao.createDetachedCriteria();
		//dc.createAlias("workCenter", "workCenter");
		/*if (StringUtils.isNotEmpty(userId)){
			dc.add(Restrictions.or(Restrictions.eq("jhfzrId", userId), Restrictions.eq("jhxzrId", userId)));
		}*/
		dc.add(Restrictions.in("workCenter.id", ScollingJobPlanUtil.selectWorkCenterIdsByUserId(userId)));
		if (StringUtils.isNotEmpty(scollingJobPlan.getJhmc())){
			dc.add(Restrictions.like("jhmc", "%"+scollingJobPlan.getJhmc()+"%"));
		}
		/*dc.add(Restrictions.or(Restrictions.eq("fbzt", 0),
				Restrictions.and(Restrictions.eq("fbzt", 1), Restrictions.ge("jhjzsj", DateUtils.getDateStart(new Date())))));*/
		dc.add(Restrictions.eq("fbzt", 0));
		//dc.addOrder(Order.asc("workCenter.gzzxbm"));
		dc.addOrder(Order.desc("jhkssj"));
		return scollingJobPlanDao.find(page, dc);
	}
	
	//判断当前工作中心 是否 还有未发布的计划
	public boolean hasNotPublished(String workCenterId) {
		DetachedCriteria dc = scollingJobPlanDao.createDetachedCriteria();
		dc.add(Restrictions.eq("workCenter.id", workCenterId));
		//dc.add(Restrictions.ge("jhkssj", DateUtils.getDateStart(new Date())));
		dc.add(Restrictions.eq("fbzt", 0));
		int size = scollingJobPlanDao.find(dc).size();
		return size > 0 ? true : false;
	}
	//找到当前工作中心 是否 还有未发布的计划
	public ScollingJobPlan onlyOneUnpublishedPlan(String workCenterId) {
		DetachedCriteria dc = scollingJobPlanDao.createDetachedCriteria();
		dc.add(Restrictions.eq("workCenter.id", workCenterId));
		dc.add(Restrictions.eq("fbzt", 0));
		List<ScollingJobPlan> list = scollingJobPlanDao.find(dc);
		int size = list.size();
		if (size == 1) {
			return list.get(0);
		} else return null;
	}
	
	@Transactional(readOnly = false)
	public void save(ScollingJobPlan scollingJobPlan) throws DataIntegrityViolationException{
		scollingJobPlanDao.clear();
		scollingJobPlanDao.save(scollingJobPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {//先删除子表，再删除主表
		ScollingJobPlan s = scollingJobPlanDao.get(id);
		if (s != null && s.getFbzt() == 0) {
			scollingJobPlanDao.getSession().delete(s);
		}
	}
	
	public List<ScollingJobPlan> findByWorkCenterId(String workCenterId) {
		DetachedCriteria dc = scollingJobPlanDao.createDetachedCriteria();
		dc.add(Restrictions.eq("workCenter.id", workCenterId));
		return scollingJobPlanDao.find(dc);
	}
	
	public List<ScollingJobPlan> findAll() {
		return scollingJobPlanDao.find(scollingJobPlanDao.createDetachedCriteria());
	}

	public Date selectMaxJhjzsj(String workCenterId, String currentId) {
		Date maxJhjzsj = scollingJobPlanDao.getMaxJhjzsj(workCenterId, currentId);
		return maxJhjzsj;
	}

	public ScollingJobPlan findByJhbh(String jhbh) {
		return scollingJobPlanDao.findByJhbh(jhbh);
	}

	//////////////////---------------------------------------------------相关权限---------------------///////////////
	/*根据工作中心id 获取该工作中心的负责人和协助人*/
	public List<User> getFzrAndXzr(String workCenterId) {
		//0，角色名字是负责人或者协助人 （命名规范***工作中心负责人/协助人）   1,找角色 数据范围为按明细设置  2，角色权限 包含该工作中心
		List<User> list = new ArrayList<User>();
		User fzr = new User();
		User xzr = new User();
		//查询非
		String sqlFzr = "SELECT DISTINCT " +
							"SYS_USER.ID,SYS_ROLE.NAME,SYS_ROLE.DATA_SCOPE,IP_GZZX.GZZXMC " +
							"FROM SYS_USER " +
							"INNER JOIN SYS_USER_ROLE ON SYS_USER.ID=SYS_USER_ROLE.USER_ID " +
							"INNER JOIN SYS_ROLE ON SYS_USER_ROLE.ROLE_ID=SYS_ROLE.ID AND SYS_ROLE.NAME LIKE '%工作中心负责人' AND SYS_ROLE.DATA_SCOPE=9 " +
							"INNER JOIN SYS_ROLE_OFFICE ON SYS_ROLE.ID=SYS_ROLE_OFFICE.ROLE_ID " +
							"INNER JOIN IP_GZZX ON IP_GZZX.ID=SYS_ROLE_OFFICE.OFFICE_ID AND IP_GZZX.ID='" + workCenterId + "'";
		List<Object[]> listFzr = scollingJobPlanDao.findBySql(sqlFzr);
		if (listFzr.size() == 0) {
			String sqlFzr2 = "SELECT DISTINCT " +
					"SYS_USER.ID,SYS_ROLE.NAME,SYS_ROLE.DATA_SCOPE,SYS_ROLE.NAME as NAME2 " +
					"FROM SYS_USER " +
					"INNER JOIN SYS_USER_ROLE ON SYS_USER.ID=SYS_USER_ROLE.USER_ID " +
					"INNER JOIN SYS_ROLE ON SYS_USER_ROLE.ROLE_ID=SYS_ROLE.ID " +
					"AND SYS_ROLE.NAME='工作中心负责人' AND SYS_ROLE.DATA_SCOPE=5 " +
					"AND SYS_USER.OFFICE_ID='" + workCenterId + "'";
			listFzr = scollingJobPlanDao.findBySql(sqlFzr2);
		}
		String sqlXzr = "SELECT DISTINCT " +
						"SYS_USER.ID,SYS_ROLE.NAME,SYS_ROLE.DATA_SCOPE,IP_GZZX.GZZXMC " +
						"FROM SYS_USER " +
						"INNER JOIN SYS_USER_ROLE ON SYS_USER.ID=SYS_USER_ROLE.USER_ID " +
						"INNER JOIN SYS_ROLE ON SYS_USER_ROLE.ROLE_ID=SYS_ROLE.ID AND SYS_ROLE.NAME LIKE '%工作中心协助人' AND SYS_ROLE.DATA_SCOPE=9 " +
						"INNER JOIN SYS_ROLE_OFFICE ON SYS_ROLE.ID=SYS_ROLE_OFFICE.ROLE_ID " +
						"INNER JOIN IP_GZZX ON IP_GZZX.ID=SYS_ROLE_OFFICE.OFFICE_ID AND IP_GZZX.ID='" + workCenterId + "'";
		List<Object[]> listXzr = scollingJobPlanDao.findBySql(sqlXzr);
		if (listXzr.size() == 0) {
			String sqlXzr2 = "SELECT DISTINCT " +
					"SYS_USER.ID,SYS_ROLE.NAME,SYS_ROLE.DATA_SCOPE,SYS_ROLE.NAME as NAME2 " +
					"FROM SYS_USER " +
					"INNER JOIN SYS_USER_ROLE ON SYS_USER.ID=SYS_USER_ROLE.USER_ID " +
					"INNER JOIN SYS_ROLE ON SYS_USER_ROLE.ROLE_ID=SYS_ROLE.ID " +
					"AND SYS_ROLE.NAME='工作中心协助人' AND SYS_ROLE.DATA_SCOPE=5 " +
					"AND SYS_USER.OFFICE_ID='" + workCenterId + "'";
			listXzr = scollingJobPlanDao.findBySql(sqlXzr2);
		}
		if (listFzr.get(0).length > 0)
			fzr = userDao.get(listFzr.get(0)[0].toString());
		if (listXzr.get(0).length > 0)
			xzr = userDao.get(listXzr.get(0)[0].toString());
		list.add(fzr);
		list.add(xzr);
		
		return list;
	}

	/*根据当前用户的id找到他管理的工作中心*/
	public List<WorkCenter> getWorkCentersByUserId(String userId) {
		List<WorkCenter> list = new ArrayList<WorkCenter>();
		
		WorkCenter workCenter = getSingleWorkCenter(userId);
		String wId = "";
		if (workCenter != null) {//如果是单一工作中心 工作中心 就是
			list.add(workCenter);
			wId = workCenter.getId();
		}
		//如果 角色设错 既有单个工作中心角色 又有多个的话 工作中心可能会重复
		List<WorkCenter> listMutil = getMutilWorkCenter(userId);
		if (listMutil.size() > 0) {
			for (WorkCenter w : listMutil) {
				if (!wId.equals(w.getId())) {
					list.add(w);
				}
			}
		}
		
		return list;
	}
	
	/*获取 单一的工作中心管理员角色*/
	public WorkCenter getSingleWorkCenter(String uId) {
		WorkCenter result = null;
		String sql = "SELECT DISTINCT " +
				"IP_GZZX.ID " +
				"FROM SYS_USER " +
				"INNER JOIN SYS_USER_ROLE ON SYS_USER.ID=SYS_USER_ROLE.USER_ID " +
				"INNER JOIN SYS_ROLE ON SYS_USER_ROLE.ROLE_ID=SYS_ROLE.ID " +
				"AND (SYS_ROLE.NAME='工作中心负责人' OR SYS_ROLE.NAME='工作中心协助人') AND SYS_ROLE.DATA_SCOPE=5 " +
				"INNER JOIN IP_GZZX ON SYS_USER.OFFICE_ID=IP_GZZX.ID " +
				"WHERE SYS_USER.ID='" + uId + "'";
		List<String> list = scollingJobPlanDao.findBySql(sql);
		if (list.size() == 1) {
			result = workCenterDao.get(list.get(0));
		}
		return result;
	}
	
	/*获取 非单一的工作中心管理员角色*/
	public List<WorkCenter> getMutilWorkCenter(String uId) {
		List<WorkCenter> result = new ArrayList<WorkCenter>();
		String sql = "SELECT DISTINCT " +
				"IP_GZZX.ID " +
				"FROM SYS_USER " +
				"INNER JOIN SYS_USER_ROLE ON SYS_USER.ID=SYS_USER_ROLE.USER_ID " +
				"INNER JOIN SYS_ROLE ON SYS_USER_ROLE.ROLE_ID=SYS_ROLE.ID " +
				"AND (SYS_ROLE.NAME LIKE '%工作中心负责人' OR SYS_ROLE.NAME LIKE '%工作中心协助人') AND SYS_ROLE.DATA_SCOPE=9 " +
				"INNER JOIN SYS_ROLE_OFFICE ON SYS_ROLE_OFFICE.ROLE_ID=SYS_ROLE.ID " +
				"INNER JOIN IP_GZZX ON SYS_ROLE_OFFICE.OFFICE_ID=IP_GZZX.ID " +
				"WHERE SYS_USER.ID='" + uId + "'";
		List<String> list = scollingJobPlanDao.findBySql(sql);
		if (list.size() > 0) {
			for (String str : list) {
				result.add(workCenterDao.get(str));
			}
		}
		return result;
	}

}
