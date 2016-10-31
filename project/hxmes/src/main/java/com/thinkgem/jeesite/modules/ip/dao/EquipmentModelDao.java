/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.EquipmentModel;

/**
 * 基本信息DAO接口
 * @author LiHR
 * @version 2016-07-06
 */
@Repository
public class EquipmentModelDao extends BaseDao<EquipmentModel> {
	public EquipmentModel findBySbggxhbm(String sbggxhbm){
		return getByHql("from EquipmentModel where sbggxhbm=:p1",new Parameter(sbggxhbm));
	}
}
