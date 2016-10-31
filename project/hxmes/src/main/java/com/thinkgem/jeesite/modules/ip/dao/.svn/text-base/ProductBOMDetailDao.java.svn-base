/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMDetail;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * BOM明细DAO接口
 * @author ZhangHD
 * @version 2016-06-16
 */
@Repository
public class ProductBOMDetailDao extends BaseDao<ProductBOMDetail> {
	/**
	 * @Title:
	 * @Description: TODO
	 * @param cpbomdbh
	 * @return
	 * @return: ProductBOMSheet
	 */
	public ProductBOMDetail findByIsBommx(String wlbmStr) {
		return getByHql("from ProductBOMDetail where zwlbm.id=:p1 and delFlag=:p2", new Parameter(wlbmStr,
				User.DEL_FLAG_NORMAL));
	}


	public List<ProductBOMDetail> findByBommx(String bomId) {
		return find("from ProductBOMDetail where productBOMSheet.id=:p1", new Parameter(bomId));
	}
}
