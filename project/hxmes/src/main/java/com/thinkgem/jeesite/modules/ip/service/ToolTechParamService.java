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
import com.thinkgem.jeesite.modules.ip.entity.Tool;
import com.thinkgem.jeesite.modules.ip.entity.ToolTechParam;
import com.thinkgem.jeesite.modules.ip.dao.ToolDao;
import com.thinkgem.jeesite.modules.ip.dao.ToolTechParamDao;

/**
 * 关键技术参数Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class ToolTechParamService extends BaseService {

	@Autowired
	private ToolTechParamDao toolTechParamDao;
	@Autowired
	private ToolDao toolDao;
	public ToolTechParam get(String id) {
		return toolTechParamDao.get(id);
	}
	
	public Page<ToolTechParam> find(Page<ToolTechParam> page, ToolTechParam toolTechParam,String toolId) {
		DetachedCriteria dc = toolTechParamDao.createDetachedCriteria();
		dc.createAlias("tool", "tool");
		if (StringUtils.isNotEmpty(toolId)){
			dc.add(Restrictions.eq("tool.id", toolId));
		}
		if (StringUtils.isNotEmpty(toolTechParam.getCsmc())){
			dc.add(Restrictions.like("csmc", "%"+toolTechParam.getCsmc()+"%"));
		}
		dc.add(Restrictions.eq(ToolTechParam.FIELD_DEL_FLAG, ToolTechParam.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return toolTechParamDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(ToolTechParam toolTechParam) {
		toolTechParamDao.save(toolTechParam);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//toolTechParamDao.deleteById(id);
		ToolTechParam toolTechParam=toolTechParamDao.get(id);
		toolTechParamDao.getSession().delete(toolTechParam);
	}
	
	public List<ToolTechParam> findByToolId(String toolId) {
		DetachedCriteria dc = toolTechParamDao.createDetachedCriteria();
		dc.add(Restrictions.eq(ToolTechParam.FIELD_DEL_FLAG, ToolTechParam.DEL_FLAG_NORMAL));
		dc.add(Restrictions.eq("tool.id", toolId));
		return toolTechParamDao.find(dc);
	}
	public List<ToolTechParam> findAll() {
		return toolTechParamDao.find(toolTechParamDao.createDetachedCriteria());
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_gzqjjscs where csdm ='"+val1+"'";
			List<ToolTechParam> list=toolTechParamDao.getSession().createSQLQuery(sql).addEntity(ToolTechParam.class).list();
			if(list.size()>0){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
}
