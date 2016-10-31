/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterDao;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 工作中心维护Service
 * @author Lucl
 * @version 2016-06-20
 */
@Component
@Transactional(readOnly = true)
public class WorkCenterService extends BaseService {
	public static final String CACHE_GZZXWH_LIST = "gzzxwhList";
	@Autowired
	private WorkCenterDao gzzxwhDao;
	@Autowired
	private OfficeDao officeDao;
	
	public WorkCenter get(String id) {
		return gzzxwhDao.get(id);
	}
	
	public Page<WorkCenter> find(Page<WorkCenter> page, WorkCenter gzzxwh) {
		DetachedCriteria dc = gzzxwhDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(gzzxwh.getName())){
			dc.add(Restrictions.like("gzzxmc", "%"+gzzxwh.getName()+"%"));
		}
		dc.add(Restrictions.eq(WorkCenter.FIELD_DEL_FLAG, WorkCenter.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return gzzxwhDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(WorkCenter gzzxwh) {
		gzzxwhDao.clear();
		gzzxwhDao.save(gzzxwh);
		updateOffice(gzzxwh);
	}
	
	public void updateOffice(WorkCenter gzzxwh) {
		String id = gzzxwh.getId();
		if (!id.equals("0")&&!id.equals("1")) {
			String sqlString ="";
			Office gen = officeDao.get("1");
			if (officeDao.get(id) == null) {
				String pid = gzzxwh.getSjgzzxid().getId();
				String pids = "";
				if (pid.equals("0")) {//第一个工作中心节点
					pid = gen.getId();
					pids = gen.getParentIds() + gen.getId() + ",";
				} else {
					pids = officeDao.get(pid).getParentIds() + pid + ",";
				}
				sqlString = "INSERT INTO SYS_OFFICE(ID,PARENT_ID,PARENT_IDS,NAME,TYPE,GRADE,AREA_ID) VALUES('"+id+"','"+pid+"','"+pids+"','"
						+gzzxwh.getGzzxmc()+"','"+gen.getType()+"','"+gen.getGrade()+"','"+officeDao.get("1").getArea().getId()+"')";
			} else {
				sqlString = "UPDATE SYS_OFFICE set NAME='"+gzzxwh.getGzzxmc()+"',AREA_ID='"+gen.getArea().getId()+"' where id='"+id+"'";
			}
			
			officeDao.updateBySql(sqlString, null);
			UserUtils.updateOfficeList();
		}
	}
	
	public void deleteOffice(String id) {
		if (!id.equals("0")&&!id.equals("1")) {
			if (officeDao.get(id) != null) {
				officeDao.deleteById(id);
				UserUtils.updateOfficeList();
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//gzzxwhDao.deleteById(id);
		WorkCenter workCenter=gzzxwhDao.get(id);
		gzzxwhDao.getSession().delete(workCenter);
		deleteOffice(id);
	}

	/**
	 * 方法功能：从数据库中查询出树的相关数据
	 * @param b
	 * @param object
	 * @return
	 */
	public List<WorkCenter> findTree(boolean b, Object object) {
		List<WorkCenter> list =null;// (List<Sbcc>)UserUtils.getCache(CACHE_SBCC_LIST);
		if (list == null){
			//User user = UserUtils.getUser();
			DetachedCriteria dc = gzzxwhDao.createDetachedCriteria();
			dc.add(Restrictions.eq("delFlag", WorkCenter.DEL_FLAG_NORMAL));
			list = gzzxwhDao.find(dc);
			// 将没有父节点的节点，找到父节点
			Set<String> parentIdSet = Sets.newHashSet();
			for (WorkCenter e : list){
				if (e.getSjgzzxid()!=null && StringUtils.isNotBlank(e.getSjgzzxid().getId())){
					boolean isExistParent = false;
					for (WorkCenter e2 : list){
						if (e.getSjgzzxid().getId().equals(e2.getId())){
							isExistParent = true;
							break;
						}
					}
					if (!isExistParent){
						parentIdSet.add(e.getSjgzzxid().getId());
					}
				}
			}
			if (parentIdSet.size() > 0){
				dc = gzzxwhDao.createDetachedCriteria();
				dc.add(Restrictions.in("id", parentIdSet));
				dc.add(Restrictions.eq("delFlag", WorkCenter.DEL_FLAG_NORMAL));
				list.addAll(0, gzzxwhDao.find(dc));
			}
			UserUtils.putCache(CACHE_GZZXWH_LIST, list);
		}
		return list;
	}

	/**
	 * 方法功能：查询当前仓库是否有子节点
	 * @param ids
	 * @return
	 */
	public Map<String,String> checkHasChild(String[] ids) {
		Map<String,String> mapper=new HashMap<String, String>();
	aa:	for(int i=0;i<ids.length;i++){
			DetachedCriteria dc=gzzxwhDao.createDetachedCriteria();
			dc.add(Restrictions.eq("sjgzzxid.id",ids[i]));
			dc.add(Restrictions.eq("delFlag",WorkCenter.DEL_FLAG_NORMAL ));
			List<WorkCenter> gzzxwhs=gzzxwhDao.find(dc);
		bb:	if(null!=gzzxwhs&&gzzxwhs.size()>0){
				for(WorkCenter gzzxwh : gzzxwhs){
					DetachedCriteria dc1=gzzxwhDao.createDetachedCriteria();
					dc1.add(Restrictions.eq("id",gzzxwh.getId()));
					dc1.add(Restrictions.eq("sjgzzxid.id", gzzxwh.getId()));
					List<WorkCenter> gzzxwhs1=gzzxwhDao.find(dc1);
					if(null!=gzzxwhs1&&gzzxwhs1.size()>0&&gzzxwhs.size()==1){
						mapper.put("flag", "0");
						break bb;
					}
				}
				mapper.put("flag", "1");
				//查询出当前仓库id的名称
				WorkCenter gzzxwh=gzzxwhDao.get(ids[i]);
				mapper.put("mc",gzzxwh.getGzzxmc());
				break aa;
			}else{
				mapper.put("flag","0");
			}
		}
		return mapper;
	}

	public void updateStateList(String id, String state) {
		WorkCenter workCenter=gzzxwhDao.get(id);
		workCenter.setSfqy(state);
		gzzxwhDao.getSession().save(workCenter);
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_gzzx where gzzxbm ='"+val1+"'";
			List<WorkCenter> list=gzzxwhDao.getSession().createSQLQuery(sql).addEntity(WorkCenter.class).list();
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	public List<WorkCenter> findAll() {
		return gzzxwhDao.findAll();
	}
	
}
