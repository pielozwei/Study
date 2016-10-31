/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.dao.MaterialDao;
import com.thinkgem.jeesite.modules.ip.dao.MaterialTechParameterDao;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.MaterialTechParameter;

/**
 * 物料技术参数Service
 * 
 * @author ZhangHD
 * @version 2016-06-14
 */
@Component
@Transactional(readOnly = true)
public class MaterialTechParameterService extends BaseService {

	@Autowired
	private MaterialTechParameterDao wljscsDao;
	@Autowired
	private MaterialDao wlbmDao;

	public MaterialTechParameter get(String id) {
		return wljscsDao.get(id);
	}

	public Page<MaterialTechParameter> find(Page<MaterialTechParameter> page, MaterialTechParameter wljscs) {
		DetachedCriteria dc = wljscsDao.createDetachedCriteria();
		dc.createAlias("wlbm", "wlbm");
		if (wljscs.getWlbm() != null && StringUtils.isNotBlank(wljscs.getWlbm().getId())) {
			Material wlbm = wlbmDao.get(wljscs.getWlbm().getId());
			if (wlbm != null) {
				dc.add(Restrictions.eq("wlbm.id", wljscs.getWlbm().getId()));
				wljscs.setWlbm(wlbm);
			}
		}
		if (StringUtils.isNotEmpty(wljscs.getCsmc())) {
			dc.add(Restrictions.like("csmc", "%" + wljscs.getCsmc() + "%"));
		}
		dc.add(Restrictions.eq(MaterialTechParameter.FIELD_DEL_FLAG, MaterialTechParameter.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("xssx"));
		return wljscsDao.find(page, dc);
	}

	@Transactional(readOnly = false)
	public void save(MaterialTechParameter wljscs) {
		wljscsDao.clear();
		wljscsDao.save(wljscs);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		MaterialTechParameter materialTechParameter=(MaterialTechParameter) wljscsDao.getSession().get(MaterialTechParameter.class, id);
		wljscsDao.getSession().delete(materialTechParameter);
	}

	public MaterialTechParameter findByCsdm(String csdm) {
		return wljscsDao.getByHql("from MaterialTechParameter where csdm=:p1", new Parameter(csdm));
	}

}
