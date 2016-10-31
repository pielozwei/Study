/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite.common.persistence.BaseDao;
import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.modules.ip.entity.CalendarMaintain;

/**
 * 工厂日历维护DAO接口
 * @author yrd
 * @version 2016-08-16
 */
@Repository
public class CalendarMaintainDao extends BaseDao<CalendarMaintain> {

		public int deleteBygzzxid(String GZZXID){
			return update("update CalendarMaintain set delFlag='" + CalendarMaintain.DEL_FLAG_DELETE + "' where gzzx_id = :p1", 
					new Parameter(GZZXID));
		}
}
