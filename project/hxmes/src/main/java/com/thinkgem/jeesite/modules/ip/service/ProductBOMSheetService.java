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
import com.thinkgem.jeesite.modules.ip.dao.MaterialDao;
import com.thinkgem.jeesite.modules.ip.dao.ProductBOMSheetDao;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMSheet;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 基本信息Service
 * 
 * @author ZhangHD
 * @version 2016-06-16
 */
@Component
@Transactional(readOnly = true)
public class ProductBOMSheetService extends BaseService {
	public static final String CACHE_WLLB_LIST = "wllblist";
	@Autowired
	private ProductBOMSheetDao productBOMSheetDao;

	@Autowired
	private MaterialDao wlbmDao;

	public ProductBOMSheet get(String id) {
		return productBOMSheetDao.get(id);
	}

	public Page<ProductBOMSheet> find(Page<ProductBOMSheet> page, ProductBOMSheet productBOMSheet) {
		DetachedCriteria dc = productBOMSheetDao.createDetachedCriteria();
		dc.createAlias("wlbm", "wlbm");
		if (StringUtils.isNotEmpty(productBOMSheet.getCpbomdbh())) {
			dc.add(Restrictions.like("cpbomdbh", "%" + productBOMSheet.getCpbomdbh() + "%"));
		}
		if (productBOMSheet.getWlbm()!=null && StringUtils.isNotEmpty(productBOMSheet.getWlbm().getWlmc())){
			dc.add(Restrictions.like("wlbm.wlmc", "%"+productBOMSheet.getWlbm().getWlmc()+"%"));
		}
		dc.add(Restrictions.eq(ProductBOMSheet.FIELD_DEL_FLAG, ProductBOMSheet.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("cpbomdbh"));
		dc.addOrder(Order.desc("bb"));
		return productBOMSheetDao.find(page, dc);
	}

	public List<Material> findByUserWl(boolean isCurrentWllb, String module) {

		DetachedCriteria dc = wlbmDao.createDetachedCriteria();
		List<Material> list = wlbmDao.find(dc);

		UserUtils.putCache(CACHE_WLLB_LIST, list);
		return list;
	}

	@Transactional(readOnly = false)
	public void save(ProductBOMSheet productBOMSheet) {
		productBOMSheetDao.clear();
		productBOMSheetDao.save(productBOMSheet);
	}

	@Transactional(readOnly = false)
	public void delete(String id) {
		ProductBOMSheet productBOMSheet=(ProductBOMSheet) productBOMSheetDao.getSession().get(ProductBOMSheet.class, id);
		productBOMSheetDao.getSession().delete(productBOMSheet);
	}
	public ProductBOMSheet findByCpbomdbh(String id){
		return productBOMSheetDao.findByCpbomdbh(id);
	}

	public List<ProductBOMSheet> findWlbmState(String wlbm) {
		return productBOMSheetDao.findWlbmState(wlbm);
	}
	public ProductBOMSheet findByOnlyCpbomdbh(String bb,String wlbm,String cpbomdbh){
		return productBOMSheetDao.findByOnlyCpbomdbh(bb, wlbm, cpbomdbh);
	}

}
