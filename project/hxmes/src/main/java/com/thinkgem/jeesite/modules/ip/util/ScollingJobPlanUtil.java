package com.thinkgem.jeesite.modules.ip.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.ip.entity.ScollingJobPlanTask;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanService;
import com.thinkgem.jeesite.modules.ip.service.ScollingJobPlanTaskService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

public class ScollingJobPlanUtil {

	private static ScollingJobPlanService scollingJobPlanService = SpringContextHolder.getBean(ScollingJobPlanService.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static ScollingJobPlanTaskService scollingJobPlanTaskService = SpringContextHolder.getBean(ScollingJobPlanTaskService.class);
	
	public static final String CACHE_DICT_MAP = "dictMap";
	
	/**
	 * 查找该用户是哪些工作中心的负责人或者协助人
	 */
	public static List<WorkCenter> selectWorkCentersByUserId(String userId) {
		List<WorkCenter> list = scollingJobPlanService.getWorkCentersByUserId(userId);
		return list;
	}
	
	/**
	 * 查找该用户是哪些工作中心的负责人或者协助人--只取工作中心id
	 */
	public static List<String> selectWorkCenterIdsByUserId(String userId) {
		List<String> list = new ArrayList<String>();
		for (WorkCenter wc : selectWorkCentersByUserId(userId)) {
			list.add(wc.getId());
		}
		return list;
	}
	
	/**
	 * 查找该工作中心的所有成员 = 管理员+成员
	 */
	public static List<Map<String, Object>> selectAllMemberByWorkCenterId(String workCenterId) {//管理员和成员可能有重复
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		List<User> listUser = scollingJobPlanService.getFzrAndXzr(workCenterId);//最多两个人
		List<User> list = userDao.find("from User u where u.delFlag=:p1 and u.office.id=:p2  order by u.id", new Parameter(User.DEL_FLAG_NORMAL,workCenterId));
		if (listUser.size() == 2) {
			String fzrId = listUser.get(0).getId();
			String xzrId = listUser.get(1).getId();
			for (User u : list) {
				String uId = u.getId();
				if (!uId.equals(fzrId) && !uId.equals(xzrId)) {
					listUser.add(u);
				}
			}
		}
		for (User u : listUser) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", u.getId());
			map.put("name", u.getName());
			map.put("pId", 0);
			resultList.add(map);
		}
		return resultList;
	}
	
	/**
	 * 查找该任务所在的工作中心名称
	 */
	public static String getWorkCenterName(String taskId) {
		List<ScollingJobPlanTask> list = scollingJobPlanTaskService.findByTaskId(taskId);
		if (list == null || list.size() ==0) return null;
		else return list.get(0).getScollingJobPlan().getWorkCenter().getGzzxmc();
	}
}
