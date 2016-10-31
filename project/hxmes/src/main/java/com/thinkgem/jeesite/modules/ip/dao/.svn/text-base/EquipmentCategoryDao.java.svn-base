/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentCategory;

/**
 * 设备分类DAO接口
 * @author LiHR
 * @version 2016-07-06
 */
@Repository
public class EquipmentCategoryDao extends BaseDao<EquipmentCategory> {
	public EquipmentCategory insertCategory() {
		String sql = "INSERT INTO IP_SBLB (ID,SBLBBM,SBLBMC,SJLBID,SBLBJC) VALUES ('0001','0000','设备类别','0','设备类别')";
		getSession().createSQLQuery(sql).executeUpdate();
		EquipmentCategory equipmentCategory = getByHql("from EquipmentCategory where SJLBID=:p1 ", new Parameter('0'));
		return equipmentCategory;
	}


	public EquipmentCategory findBySblbbm(String sblbbm) {
		return getByHql("from EquipmentCategory where SBLBBM=:p1 ", new Parameter(sblbbm));
	}
}
