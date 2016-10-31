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
import com.thinkgem.jeesite.modules.ip.entity.ToolCategory;
import com.thinkgem.jeesite.modules.ip.dao.ToolCategoryDao;
import com.thinkgem.jeesite.modules.ip.dao.ToolDao;

/**
 * 基本信息Controller
 * @author LiHR
 * @version 2016-07-06
 */
@Component
@Transactional(readOnly = true)
public class ToolService extends BaseService {

	@Autowired
	private ToolDao toolDao;
	@Autowired
	private ToolCategoryDao toolCategoryDao;
	public Tool get(String id) {
		return toolDao.get(id);
	}
	
	public Page<Tool> find(Page<Tool> page, Tool tool, String nodeId) {
		DetachedCriteria dc = toolDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(nodeId)){
			dc.add(Restrictions.eq("gzqjlbId.id", nodeId));
		}
		if (StringUtils.isNotEmpty(tool.getGzqjmc())){
			dc.add(Restrictions.like("gzqjmc", "%"+tool.getGzqjmc()+"%"));
		}
		dc.add(Restrictions.eq(Tool.FIELD_DEL_FLAG, Tool.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("xssx"));
		return toolDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Tool tool) {
		toolDao.save(tool);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		//toolDao.deleteById(id);
		Tool tool = toolDao.get(id);
		toolDao.getSession().delete(tool);
	}
	
	public List<Tool> findAll() {
		return toolDao.find(toolDao.createDetachedCriteria());
	}

	public boolean validation(String val1) {
		if(null!=val1&&!val1.equals("")){
			String sql="select * from ip_gzqj where gzqjbm ='"+val1+"'";
			List<Tool> list=toolDao.getSession().createSQLQuery(sql).addEntity(Tool.class).list();
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
