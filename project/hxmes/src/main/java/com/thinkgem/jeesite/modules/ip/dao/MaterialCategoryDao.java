/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentCategory;
import com.thinkgem.jeesite.modules.ip.entity.MaterialCategory;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 物料编码信息DAO接口
 * @author ZhangHD
 * @version 2016-06-06
 */
@Repository
public class MaterialCategoryDao extends BaseDao<MaterialCategory> {
	/**
	 * @Title: findByWllb 
	 * @Description: TODO
	 * @param wllbbm
	 * @return
	 * @return: Wllb
	 */
	public MaterialCategory findByWllb(String wllbbm) {
		return getByHql("from MaterialCategory Where wllbbm=:p1 and delFlag=:p2", new Parameter(wllbbm,User.DEL_FLAG_NORMAL));
	}
	
	public MaterialCategory findByID(String id) {
		return getByHql("from MaterialCategory Where ID=:p1 and delFlag=:p2", new Parameter(id,User.DEL_FLAG_NORMAL));
	}

	public MaterialCategory insertCategory() {
			String sql = "INSERT INTO IP_WLLB (ID,WLLBBM,WLLBMC,SJLBID,SFQY,XSSX,DEL_FLAG) VALUES ('0000','wllb','物料类别','0',1,0,'1')";
			getSession().createSQLQuery(sql).executeUpdate();
			MaterialCategory materialCategory = getByHql("from MaterialCategory where SJLBID=:p1 ", new Parameter('0'));
		return materialCategory;
	}
}
