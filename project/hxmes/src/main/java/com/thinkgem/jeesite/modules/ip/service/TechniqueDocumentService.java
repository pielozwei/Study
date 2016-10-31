/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.TechniqueDocument;
import com.thinkgem.jeesite.modules.ip.dao.TechniqueDocumentDao;

/**
 * 人员业务分类管理Service
 * @author tianjingyi,yangsu
 * @version 2016-06-27
 */
@Component
@Transactional(readOnly = true)
public class TechniqueDocumentService extends BaseService {

	@Autowired
	private TechniqueDocumentDao techniqueDocumentDao;
	
	public TechniqueDocument get(String id) {
		return techniqueDocumentDao.get(id);
	}
	
	public Page<TechniqueDocument> find(Page<TechniqueDocument> page, TechniqueDocument techniqueDocument) {
		DetachedCriteria dc = techniqueDocumentDao.createDetachedCriteria();
		if (techniqueDocument.getTechnique() != null && StringUtils.isNotEmpty(techniqueDocument.getTechnique().getId())){
			dc.add(Restrictions.like("technique.id", "%"+techniqueDocument.getTechnique().getId()+"%"));
		}
		
		if (techniqueDocument.getSfqy() != null){
			dc.add(Restrictions.eq("sfqy", techniqueDocument.getSfqy()));
		}
		dc.add(Restrictions.eq(TechniqueDocument.FIELD_DEL_FLAG, TechniqueDocument.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("fwxh"));
		return techniqueDocumentDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(TechniqueDocument techniqueDocument) {
		techniqueDocumentDao.save(techniqueDocument);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		techniqueDocumentDao.deleteById(id);
	}
	
}
