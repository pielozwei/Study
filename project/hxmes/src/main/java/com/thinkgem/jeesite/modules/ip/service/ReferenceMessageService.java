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
import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ip.entity.ReferenceMessage;
import com.thinkgem.jeesite.modules.ip.util.ScollingJobPlanUtil;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterDao;
import com.thinkgem.jeesite.modules.ip.dao.ReferenceMessageDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 参考消息Controller
 * @author LiHR
 * @version 2016-08-18
 */
@Component
@Transactional(readOnly = true)
public class ReferenceMessageService extends BaseService {

	@Autowired
	private ReferenceMessageDao referenceMessageDao;
	@Autowired
	private WorkCenterDao workCenterDao;

	public ReferenceMessage get(String id) {
		return referenceMessageDao.get(id);
	}
	
	public Page<ReferenceMessage> find(Page<ReferenceMessage> page, ReferenceMessage referenceMessage) {
		DetachedCriteria dc = referenceMessageDao.createDetachedCriteria();
		User user = UserUtils.getUser();
		dc.add(Restrictions.eq("fsr.id", user.getId()));
		if (StringUtils.isNotEmpty(referenceMessage.getBt())){
			dc.add(Restrictions.like("bt", "%"+referenceMessage.getBt()+"%"));
		}
		dc.addOrder(Order.desc("createDate"));
		return referenceMessageDao.find(page, dc);
	}
	
	public Page<ReferenceMessage> findForWorkCenter(Page<ReferenceMessage> page, ReferenceMessage referenceMessage) {
		DetachedCriteria dc = referenceMessageDao.createDetachedCriteria();
		dc.add(Restrictions.eq("sffs", 0));//已发送
		dc.add(Restrictions.in("workCenter.id", ScollingJobPlanUtil.selectWorkCenterIdsByUserId(UserUtils.getUser().getId())));
		if (StringUtils.isNotEmpty(referenceMessage.getBt())){
			dc.add(Restrictions.like("bt", "%"+referenceMessage.getBt()+"%"));
		}
		dc.addOrder(Order.desc("fssj"));
		return referenceMessageDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(ReferenceMessage referenceMessage) {
		referenceMessageDao.save(referenceMessage);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		referenceMessageDao.getSession().delete(referenceMessageDao.get(id));
	}
	
	public List<ReferenceMessage> findByWorkCenterId(String workCenterId) {
		DetachedCriteria dc = referenceMessageDao.createDetachedCriteria();
		dc.add(Restrictions.eq("workCenter.id", workCenterId));
		return referenceMessageDao.find(dc);
	}
	public List<ReferenceMessage> findAll() {
		return referenceMessageDao.find(referenceMessageDao.createDetachedCriteria());
	}

	public boolean hasSend(List<String> ids) {//有发送的
		DetachedCriteria dc = referenceMessageDao.createDetachedCriteria();
		dc.add(Restrictions.eq("sffs", 0));//已发送
		dc.add(Restrictions.in("id", ids));
		List<ReferenceMessage> list = referenceMessageDao.find(dc);
		if (list.size()>0) {
			return true;
		} else return false;
	}
	
	public boolean send(List<String> ids) {//未发送
		String qlString = "update ReferenceMessage set sffs=0  where id in :p1";
		int result = referenceMessageDao.update(qlString, new Parameter(ids));
		if (result>0) {
			return true;
		} else return false;
	}
	
	public boolean hasQuote(String id) {//有引用的
		DetachedCriteria dc = referenceMessageDao.createDetachedCriteria();
		dc.add(Restrictions.eq("sfyyg", 0));//已发送
		dc.add(Restrictions.eq("id", id));
		List<ReferenceMessage> list = referenceMessageDao.find(dc);
		if (list.size()>0) {
			return true;
		} else return false;
	}

	public boolean quote(String id) {//未发送
		String qlString = "update ReferenceMessage set sfyyg=0  where id in :p1";
		int result = referenceMessageDao.update(qlString, new Parameter(id));
		if (result>0) {
			return true;
		} else return false;
	}
	
	public boolean setRead(String id) {//设为已读
		String qlString = "update ReferenceMessage set sfyd=0  where id = :p1";
		int result = referenceMessageDao.update(qlString, new Parameter(id));
		if (result>0) {
			return true;
		} else return false;
	}

	public ReferenceMessage findByBt(String bt) {
		return referenceMessageDao.findByBt(bt);
	}

}
