/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.ProductBOMSheet;

/**
 * 基本信息DAO接口
 * @author ZhangHD
 * @version 2016-06-16
 */
@Repository
public class ProductBOMSheetDao extends BaseDao<ProductBOMSheet> {
	/**
	 * @Title: 获取当前bom单
	 * @Description: TODO
	 * @param cpbomdbh
	 * @return
	 * @return: ProductBOMSheet
	 */
	public ProductBOMSheet findByCpbomdbh(String id) {
		return getByHql("from ProductBOMSheet where cpbomdbh=:p1 ", new Parameter(id));
	}
	/**
	 * @Title: 获取状态为启用的物料BOM单
	 * @Description: TODO
	 * @param wlbm
	 * @return
	 * @return:  List<ProductBOMSheet>
	 */
	public List<ProductBOMSheet> findWlbmState(String  wlbm) {
		return find("from ProductBOMSheet where wlbm.wlbm=:p1  and sfqy=1", new Parameter( wlbm));
	}
	/**
	 * 通过bom单号 版本号 物料 获取唯一
	 * @Title: findByOnlyCpbomdbh 
	 * @Description: TODO
	 * @param bb
	 * @param wlbm
	 * @param cpbomdbh
	 * @return
	 * @return: ProductBOMSheet
	 */
	public ProductBOMSheet findByOnlyCpbomdbh(String bb,String wlbm,String cpbomdbh) {
		return getByHql("from ProductBOMSheet where cpbomdbh=:p1 and wlbm.wlbm=:p2 and bb=:p3 ", new Parameter(cpbomdbh,wlbm,bb));
	}
	
}
