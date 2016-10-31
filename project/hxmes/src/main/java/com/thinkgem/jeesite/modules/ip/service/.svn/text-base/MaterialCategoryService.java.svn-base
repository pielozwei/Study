/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.ArrayList;
import java.util.List;
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
import com.thinkgem.jeesite.modules.ip.dao.MaterialCategoryDao;
import com.thinkgem.jeesite.modules.ip.entity.MaterialCategory;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 物料编码信息Service
 * @author ZhangHD
 * @version 2016-06-06
 */
@Component
@Transactional(readOnly = true)
public class MaterialCategoryService extends BaseService {
	public static final String CACHE_WLLB_LIST = "wllbList";
	@Autowired
	private MaterialCategoryDao wllbDao;


	public MaterialCategory get(String id) {
		return wllbDao.get(id);
	}



	public Page<MaterialCategory> findSuns(Page<MaterialCategory> page, MaterialCategory wllb, String nodeId) {
		DetachedCriteria dc = wllbDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(wllb.getWllbmc())) {
			dc.add(Restrictions.like("wllbmc", "%" + wllb.getWllbmc() + "%"));
		}
		if (StringUtils.isNotEmpty(nodeId)) {
			dc.add(Restrictions.eq("parent.id",nodeId));
		}
		dc.add(Restrictions.eq("delFlag", MaterialCategory.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("xssx"));
		return wllbDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(MaterialCategory wllb) {
		wllbDao.clear();
		wllbDao.save(wllb);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		MaterialCategory materialCategory = (MaterialCategory) wllbDao.getSession().get(MaterialCategory.class, id);
		wllbDao.getSession().delete(materialCategory);
	}


	public List<MaterialCategory> findByUser(boolean isCurrentWllb, String module) {
		List<MaterialCategory> list = null;// (List<Wllb>)UserUtils.getCache(CACHE_Wllb_LIST);
		if (list == null) {
			// User user = UserUtils.getUser();
			DetachedCriteria dc = wllbDao.createDetachedCriteria();
			list = wllbDao.find(dc);
			if (list.size() <= 0) {
				MaterialCategory materialCategory = wllbDao.insertCategory();
				list.add(materialCategory);
			}
			// 将没有父节点的节点，找到父节点
			Set<String> parentIdSet = Sets.newHashSet();
			for (MaterialCategory e : list) {
				if (e.getParent() != null && StringUtils.isNotBlank(e.getParent().getId())) {
					boolean isExistParent = false;
					for (MaterialCategory e2 : list) {
						if (e.getParent().getId().equals(e2.getId())) {
							isExistParent = true;
							break;
						}
					}
					if (!isExistParent) {
						parentIdSet.add(e.getParent().getId());
					}
				}
			}
			if (parentIdSet.size() > 0) {
				dc = wllbDao.createDetachedCriteria();
				dc.add(Restrictions.in("id", parentIdSet));
				list.addAll(0, wllbDao.find(dc));
			}
			UserUtils.putCache(CACHE_WLLB_LIST, list);
		}
		return list;
	}


	public MaterialCategory findByWllb(String wllbbm) {
		return wllbDao.findByWllb(wllbbm);
	}


	public MaterialCategory updateState(MaterialCategory materialCategory, String state) {
		materialCategory.setSfqy(Integer.parseInt(state));
		wllbDao.getSession().save(materialCategory);
		return null;
	}


	/* 只找子节点 */
	public List<MaterialCategory> findSun(String nodeId) {
		List<MaterialCategory> list = new ArrayList<MaterialCategory>();
		DetachedCriteria dc = wllbDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", MaterialCategory.DEL_FLAG_NORMAL));
		if (StringUtils.isNotEmpty(nodeId)) {
			dc.add(Restrictions.eq("parent.id", nodeId));
		} else
			return list;
		list = wllbDao.find(dc);
		return list;
	}
}
