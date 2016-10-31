/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.service;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.ip.entity.Employee;
import com.thinkgem.jeesite.modules.ip.entity.Organization;
import com.thinkgem.jeesite.modules.ip.entity.OrganizationDepartment;
//import com.thinkgem.jeesite.modules.ip.form.TreeList;
import com.thinkgem.jeesite.modules.ip.dao.OrganizationDao;
import com.thinkgem.jeesite.modules.ip.dao.OrganizationDepartmentDao;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 组织机构管理Service
 * @author sse
 * @version 2016-06-15
 */
@Component
@Transactional(readOnly = true)
public class OrganizationService extends BaseService {

	public static final String CACHE_ORGANIZATION_LIST = "organizationList";
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private OrganizationDepartmentDao organizationDepartmentDao;
	
	public Organization get(String id) {
		return organizationDao.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> findById(boolean isCurrentSite,String Parentid){
		List<Organization> list = (List<Organization>)UserUtils.getCache(CACHE_ORGANIZATION_LIST);
//		List<OrganizationDepartment> listD = (List<OrganizationDepartment>)UserUtils.getCache(CACHE_ORGANIZATION_LIST);
		if(list == null){
			DetachedCriteria dc = organizationDao.createDetachedCriteria();
			dc.add(Restrictions.eq(Organization.FIELD_DEL_FLAG, Organization.DEL_FLAG_NORMAL));
			dc.addOrder(Order.desc("id"));
			list = organizationDao.find(dc);
			
//			OrganizationDepartmentDao od = new OrganizationDepartmentDao();
//			DetachedCriteria dcd = od.createDetachedCriteria();
//			dcd.add(Restrictions.eq(Organization.FIELD_DEL_FLAG, Organization.DEL_FLAG_NORMAL));
//			dcd.addOrder(Order.desc("id"));
//			listD = od.find(dcd);
			
		
//		List<Organization> list = (List<Organization>)UserUtils.getCache(CACHE_ORGANIZATION_LIST);
//		if(list == null){
//			list = organizationDao.findTreeList();
			// 将没有父节点的节点，找到父节点
			Set<String> parentIdSet = Sets.newHashSet();
			for (Organization e : list){
				if (e.getId()!=null && StringUtils.isNotBlank(e.getId())){
					boolean isExistParent = false;
					for (Organization e2 : list){
						if (e.getId().equals(e2.getId())){
							isExistParent = true;
							break;
						}
					}
					if (!isExistParent){
						parentIdSet.add(e.getId());
					}
				}
			}
			//确定已经存在父id
			if (parentIdSet.size() > 0){
				dc = organizationDao.createDetachedCriteria();
				dc.add(Restrictions.in("id", parentIdSet));
				dc.add(Restrictions.eq("delFlag", Category.DEL_FLAG_NORMAL));
				dc.addOrder(Order.asc("id")).addOrder(Order.asc("sort"));
				list.addAll(0, organizationDao.find(dc));
			}
			UserUtils.putCache(CACHE_ORGANIZATION_LIST, list);
		}
		
		if (isCurrentSite){
			List<Organization> OrganizationList = Lists.newArrayList(); 
			for (Organization e : list){
				if (e.getId() !=null ){
					if (StringUtils.isNotEmpty(Parentid)){
						if (Parentid.equals(e.getId())){
							OrganizationList.add(e);
						}
					}else{
						OrganizationList.add(e);
					}
				}
			}
			return OrganizationList;
		}
		return list;
	}
	
	
	public Page<Organization> find(Page<Organization> page, Organization organization) {
		DetachedCriteria dc = organizationDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(organization.getJgmc())){
			dc.add(Restrictions.like("jgmc", "%"+organization.getJgmc()+"%"));
		}
		if (StringUtils.isNotEmpty(organization.getId())){
			dc.add(Restrictions.like("id", "%"+organization.getId()+"%"));
		}
		dc.add(Restrictions.eq(Organization.FIELD_DEL_FLAG, Organization.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return organizationDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Organization organization) {
		organizationDao.save(organization);
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		organizationDao.deleteById(id);
	}
	
	//获取所有的部门信息，用于列表项组装
		public List<Organization> findAll() {
			DetachedCriteria dc = organizationDao.createDetachedCriteria();
			dc.add(Restrictions.eq(Employee.FIELD_DEL_FLAG, Employee.DEL_FLAG_NORMAL));
			dc.addOrder(Order.asc("xssx"));
			return organizationDao.find(dc);
		}
	
}
