/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.dao.MaterialCategoryDao;
import com.thinkgem.jeesite.modules.ip.dao.MaterialDao;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.MaterialCategory;

/**
 * 物料编码信息Service
 * @author ZhangHD
 * @version 2016-06-03
 */
@Component
@Transactional(readOnly = true)
public class MaterialService extends BaseService {
	@Autowired
	private MaterialDao wlbmDao;
	@Autowired
	private MaterialCategoryDao wllbDao;


	public Material get(String id) {
		return wlbmDao.get(id);
	}


	public Page<Material> find(Page<Material> page, Material wlbm) {
		DetachedCriteria dc = wlbmDao.createDetachedCriteria();
		dc.createAlias("wllb", "wllb");
		if (wlbm.getWllb() != null && StringUtils.isNotBlank(wlbm.getWllb().getId())
				&& !MaterialCategory.isRoot(wlbm.getWllb().getId())) {
			MaterialCategory wllb = wllbDao.get(wlbm.getWllb().getId());
			if (wlbm != null) {
				dc.add(Restrictions.eq("wllb.id", wlbm.getWllb().getId()));
				wlbm.setWllb(wllb);
			}
		}
		if (wlbm.getCreateBy() != null && StringUtils.isNotBlank(wlbm.getCreateBy().getId())) {
			dc.add(Restrictions.eq("createBy.id", wlbm.getCreateBy().getId()));
		}
		if (StringUtils.isBlank(page.getOrderBy())) {
			dc.addOrder(Order.desc("updateDate"));
		}
		if (StringUtils.isNotEmpty(wlbm.getWlmc())) {
			dc.add(Restrictions.like("wlmc", "%" + wlbm.getWlmc() + "%"));
		}
		dc.add(Restrictions.eq(Material.FIELD_DEL_FLAG, Material.DEL_FLAG_NORMAL));
		 dc.addOrder(Order.desc("xssx"));
		return wlbmDao.find(page, dc);
	}


	@Transactional(readOnly = false)
	public void save(Material wlbm) {
		wlbmDao.clear();
		wlbmDao.save(wlbm);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		Material material=(Material) wlbmDao.getSession().get(Material.class, id);
		wlbmDao.getSession().delete(material);
	}


	public Material findByWlbm(String wlbm) {
		return wlbmDao.findByWlbm(wlbm);
	}
	public List<Material> wlfindAll() {
		return wlbmDao.find(wlbmDao.createDetachedCriteria());
	}
}
