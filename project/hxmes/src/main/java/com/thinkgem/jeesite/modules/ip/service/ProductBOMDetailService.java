/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.ip.dao.ProductBOMDetailDao;
import com.thinkgem.jeesite.modules.ip.dao.ProductBOMSheetDao;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMDetail;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMSheet;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * BOM明细Service
 * @author ZhangHD
 * @version 2016-06-16
 */
@Component
@Transactional(readOnly = true)
public class ProductBOMDetailService extends BaseService {
	public static final String CACHE_BOM_LIST = "detailList";
	@Autowired
	private ProductBOMDetailDao productBOMDetailDao;
	@Autowired
	private ProductBOMSheetDao productBOMSheetDao;


	public ProductBOMDetail get(String id) {
		return productBOMDetailDao.get(id);
	}


	public Page<ProductBOMDetail> find(Page<ProductBOMDetail> page, ProductBOMDetail productBOMDetail, String type) {
		DetachedCriteria dc = productBOMDetailDao.createDetachedCriteria();
		String cpbomdid = productBOMDetail.getProductBOMSheet().getId();
		if (type != null && type.equals("view")) {
			DetachedCriteria qddc = productBOMSheetDao.createDetachedCriteria();
			qddc.add(Restrictions.eq("wlbm.id", productBOMDetail.getZwlbm().getId()));
			qddc.add(Restrictions.eq("sfqy", 1));
			List<ProductBOMSheet> listpro = productBOMSheetDao.find(qddc);
			if (listpro.size() > 0) {
				cpbomdid = listpro.get(0).getId();
				dc.add(Restrictions.eq("productBOMSheet.id", cpbomdid));
			} else {
				dc.add(Restrictions.eq("zwlbm.id", productBOMDetail.getZwlbm().getId()));
				dc.add(Restrictions.eq("productBOMSheet.id", cpbomdid));
			}
		} else {
			dc.add(Restrictions.eq("productBOMSheet.id", cpbomdid));
		}
		dc.addOrder(Order.desc("id"));
		return productBOMDetailDao.find(page, dc);
	}


	/* bom树 */
	public List<ProductBOMDetail> findNodes(ProductBOMSheet productBOMSheet) {
		List<ProductBOMDetail> detailList = new ArrayList<ProductBOMDetail>();
		String rootId = productBOMSheet.getId();
		listMx(rootId, detailList);
		// 创建父节点
		ProductBOMDetail bean = new ProductBOMDetail();
		bean.setZwlbm(productBOMSheet.getWlbm());
		bean.setProductBOMSheet(productBOMSheet);
		detailList.add(bean);
		UserUtils.putCache(CACHE_BOM_LIST, detailList);
		return detailList;
	}


	/**
	 * 通BOM单ID， 获取BOM明细
	 * @param wlbm
	 * @return
	 */
	public List<ProductBOMDetail> listMx(String nodeId, List<ProductBOMDetail> detailList) {
		DetachedCriteria dcmx = productBOMDetailDao.createDetachedCriteria();
		dcmx.add(Restrictions.eq("productBOMSheet.id", nodeId));
		List<ProductBOMDetail> mxlist = productBOMDetailDao.find(dcmx);// 获取子物料对象
		if (mxlist.size() > 0) {
			for (ProductBOMDetail productBOMDetail : mxlist) {
				int sfmj = productBOMDetail.getSfmj();
					// 将明细存放bomlist
				if (sfmj != 1) {
					detailList.add(productBOMDetail);
					// 查看明细 是否存在bom单
					List<ProductBOMSheet> listQd = listQd(productBOMDetail.getZwlbm());// 获取BOM单
					if (listQd.size() > 0) {
						for (ProductBOMSheet productBOMSheet2 : listQd) {
							listMx(productBOMSheet2.getId(), detailList);
						}
					}
					}
			}
		}
		return mxlist;
	}


	/**
	 * 通过BOM明细子物料编码，获取bom单信息
	 * @param wlbm
	 * @return
	 */
	public List<ProductBOMSheet> listQd(Material zWlbm) {
		DetachedCriteria dcqd = productBOMSheetDao.createDetachedCriteria();
		dcqd.add(Restrictions.eq("delFlag", ProductBOMSheet.DEL_FLAG_NORMAL));
		dcqd.add(Restrictions.eq("wlbm.id", zWlbm.getId()));
		List<ProductBOMSheet> mxlist = productBOMSheetDao.find(dcqd);// 获取子物料对象
		return mxlist;
	}


	@Transactional(readOnly = false)
	public void save(ProductBOMDetail productBOMDetail) {
		productBOMDetailDao.clear();
		productBOMDetailDao.save(productBOMDetail);
	}


	@Transactional(readOnly = false)
	public void delete(String id) {
		ProductBOMDetail productBOMDetail = productBOMDetailDao.get(id);
		productBOMDetailDao.getSession().delete(productBOMDetail);
	}


	public ProductBOMDetail findByIsBommx(String wlbmStr) {
		return productBOMDetailDao.findByIsBommx(wlbmStr);
	}


	public List<ProductBOMDetail> findBomId(String id) {
		DetachedCriteria dcqd = productBOMDetailDao.createDetachedCriteria();
		dcqd.add(Restrictions.eq("productBOMSheet.id", id));
		return productBOMDetailDao.find(dcqd);
	}
}
