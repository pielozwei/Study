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
import com.thinkgem.jeesite.modules.ip.entity.Store;
import com.thinkgem.jeesite.modules.ip.dao.StoreDao;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 仓库Service
 * @author Lucl
 * @version 2016-06-13
 */
@Component
@Transactional(readOnly = true)
public class StoreService extends BaseService {
	public static final String CACHE_CK_LIST = "ckList";
	@Autowired
	private StoreDao ckDao;
	
	public Store get(String id) {
		return ckDao.get(id);
	}
	
	public Page<Store> find(Page<Store> page, Store ck,String name) {
		DetachedCriteria dc = ckDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(ck.getName())){
			dc.add(Restrictions.like("mc", "%"+ck.getName()+"%"));
		}
		if(StringUtils.isNotEmpty(ck.getBh())){
			dc.add(Restrictions.like("ckbh", "%"+ck.getBh()+"%"));
		}
		if(null!=ck&&null!=ck.getZn()&&!ck.getZn().equals("")){
			dc.add(Restrictions.eq("ckzn", ck.getZn()));
		}
		if(null!=ck&&null!=ck.getJz()&&!ck.getJz().equals("")){
			dc.add(Restrictions.eq("ckjz", ck.getJz()));
		}
		if(null!=ck&&null!=ck.getCcxzfl()&&!ck.getCcxzfl().equals("")){
			dc.add(Restrictions.eq("ckxz", ck.getCcxzfl()));
		}
		if (null!=ck&&null!=ck.getId()&&!ck.getId().equals("")){
			dc.add(Restrictions.eq("sjjd.id", ck.getId()));
		}
		dc.add(Restrictions.eq(Store.FIELD_DEL_FLAG, Store.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return ckDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Store ck) {
		ckDao.clear();
		ckDao.save(ck);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		ckDao.deleteById(id);
		Store store=(Store) ckDao.getSession().get(Store.class, id);
		ckDao.getSession().delete(store);
	}
	
	/**
	 * 方法功能：从数据库中查询出树的相关数据
	 * @param b
	 * @param object
	 * @return
	 */
	public List<Store> findTree(boolean b, Object object) {
		List<Store> list =null;// (List<Sbcc>)UserUtils.getCache(CACHE_SBCC_LIST);
		if (list == null){
			//User user = UserUtils.getUser();
			DetachedCriteria dc = ckDao.createDetachedCriteria();
			dc.add(Restrictions.eq("delFlag", Store.DEL_FLAG_NORMAL));
			list = ckDao.find(dc);
			// 将没有父节点的节点，找到父节点
			Set<String> parentIdSet = Sets.newHashSet();
			for (Store e : list){
				if (e.getSjjd()!=null && StringUtils.isNotBlank(e.getSjjd().getId())){
					boolean isExistParent = false;
					for (Store e2 : list){
						if (e.getSjjd().getId().equals(e2.getId())){
							isExistParent = true;
							break;
						}
					}
					if (!isExistParent){
						parentIdSet.add(e.getSjjd().getId());
					}
				}
			}
			if (parentIdSet.size() > 0){
				dc = ckDao.createDetachedCriteria();
				dc.add(Restrictions.in("id", parentIdSet));
				dc.add(Restrictions.eq("delFlag", Store.DEL_FLAG_NORMAL));
				list.addAll(0, ckDao.find(dc));
			}
			UserUtils.putCache(CACHE_CK_LIST, list);
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
		for(int i=0;i<ids.length;i++){
			DetachedCriteria dc=ckDao.createDetachedCriteria();
			dc.add(Restrictions.eq("sjjd.id",ids[i]));
			dc.add(Restrictions.eq("delFlag",Store.DEL_FLAG_NORMAL ));
			List<Store> cks=ckDao.find(dc);
			if(null!=cks&&cks.size()>0){
				mapper.put("flag", "1");
				//查询出当前仓库id的名称
				Store ck=ckDao.get(ids[i]);
				mapper.put("mc",ck.getMc());
			}else{
				mapper.put("flag","0");
			}
		}
		return mapper;
	}

	public Page<Store> findSuns(Page<Store> page, Store ck,String name) {
		DetachedCriteria dc = ckDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(name)){
			dc.add(Restrictions.like("name", "%"+name+"%"));
		}
		if (StringUtils.isNotEmpty(ck.getId())){
			dc.add(Restrictions.eq("sjjd.id", ck.getId()));
		}
		//dc.add(Restrictions.eq(Sbcc.FIELD_DEL_FLAG, Sbcc.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("delFlag", Store.DEL_FLAG_NORMAL));
		//dc.addOrder(Order.desc("id"));
		return ckDao.find(page, dc);
	}

	public void updateStateList(String id,String state) {
		//根据id得到对
		Store store=ckDao.get(id);
		store.setSfqy(Integer.parseInt(state));
		ckDao.getSession().save(store);
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_ck where ckbh ='"+val1+"'";
			List<Store> list=ckDao.getSession().createSQLQuery(sql).addEntity(Store.class).list();
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
		
	}
	
}
