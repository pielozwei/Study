/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterEquipmentFieldDao;
import com.thinkgem.jeesite.modules.ip.dao.WorkCenterOrganizationDao;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenter;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterEquipmentField;
import com.thinkgem.jeesite.modules.ip.entity.WorkCenterOrganization;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterOrganizationService;
import com.thinkgem.jeesite.modules.ip.service.WorkCenterService;
import com.thinkgem.jeesite.modules.ip.dao.EquipmentDao;
import com.thinkgem.jeesite.modules.ip.dao.OrganizationDao;
import com.thinkgem.jeesite.modules.ip.entity.Equipment;
import com.thinkgem.jeesite.modules.ip.entity.Organization;
import com.thinkgem.jeesite.modules.ip.service.EquipmentService;

/**
 * 工作中心机构Controller
 * @author lucl
 * @version 2016-06-23
 */
@Controller
@RequestMapping(value = "${adminPath}/gzzx/gzzxjg")
public class WorkCenterOrganizationController extends BaseController {

	@Autowired
	private WorkCenterOrganizationService gzzxjgService;
	
	@Autowired
	private WorkCenterService gzzxwhService;
	
	@Autowired
	private EquipmentService sbService;
	
	@Autowired
	private EquipmentDao sbDao;
	
	@Autowired
	private WorkCenterEquipmentFieldDao gzzxsbDao;
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private WorkCenterOrganizationDao gzzxjgDao;
	
	@ModelAttribute
	public WorkCenterOrganization get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return gzzxjgService.get(id);
		}else{
			return new WorkCenterOrganization();
		}
	}
	
	/*@RequiresPermissions("gzzx:gzzxjg:view")
	@RequestMapping(value = {"list",""})
	public String list(Gzzxjg gzzxjg, Model model) {
		model.addAttribute("gzzxjg", gzzxjg);
		//查询出所有设备的信息
		DetachedCriteria dc = sbDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", Sbcc.DEL_FLAG_NORMAL));
		List<Sb> sb=sbDao.find(dc);
		//查询出所有工作中心设备信息
		DetachedCriteria dc1 = gzzxsbDao.createDetachedCriteria();
		dc1.add(Restrictions.eq("ipGzzxId", gzzxjg.getIpGzzxId()));
		dc1.add(Restrictions.eq("delFlag","0"));
		List<Gzzxsb> gzzxsbs=gzzxsbDao.find(dc1);
		//查询出所有机构信息
		DetachedCriteria dc2 = organizationDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", Sbcc.DEL_FLAG_NORMAL));
		List<Organization> organizations=organizationDao.find(dc2);
		//查询出所有工作中心机构信息
		DetachedCriteria dc3 = gzzxjgDao.createDetachedCriteria();
		dc1.add(Restrictions.eq("ipGzzxId", gzzxjg.getIpGzzxId()));
		dc3.add(Restrictions.eq("delFlag", Sbcc.DEL_FLAG_NORMAL));
		List<Gzzxjg> gzzxjgs = gzzxjgDao.find(dc3);
		
		model.addAttribute("ipGzzxId", gzzxjg.getIpGzzxId());
		model.addAttribute("sb", sb);
		model.addAttribute("gzzxsbs", gzzxsbs);
		model.addAttribute("organizations", organizations);
		model.addAttribute("gzzxjgs", gzzxjgs);
		return "modules/" + "gzzx/gzzxjgList";
	}*/

	@RequiresPermissions("gzzx:gzzxjg:view")
	@RequestMapping(value = "form")
	public String form(WorkCenterOrganization gzzxjg, Model model) {
		model.addAttribute("gzzxjg", gzzxjg);
		//查询出所有设备的信息
		DetachedCriteria dc = sbDao.createDetachedCriteria();
		dc.add(Restrictions.eq("delFlag", WorkCenterOrganization.DEL_FLAG_NORMAL));
		List<Equipment> sb=sbDao.find(dc);
		//查询出所有工作中心设备信息
		DetachedCriteria dc1 = gzzxsbDao.createDetachedCriteria();
		dc1.add(Restrictions.eq("ipGzzxId", gzzxjg.getIpGzzxId()));
		dc1.add(Restrictions.eq("delFlag","0"));
		List<WorkCenterEquipmentField> gzzxsbs=gzzxsbDao.find(dc1);
		//查询出所有机构信息
		DetachedCriteria dc2 = organizationDao.createDetachedCriteria();
		dc2.add(Restrictions.eq("delFlag", WorkCenterOrganization.DEL_FLAG_NORMAL));
		List<Organization> organizations=organizationDao.find(dc2);
		//查询出所有工作中心机构信息
		DetachedCriteria dc3 = gzzxjgDao.createDetachedCriteria();
		dc3.add(Restrictions.eq("ipGzzxId", gzzxjg.getIpGzzxId()));
		dc3.add(Restrictions.eq("delFlag", WorkCenterOrganization.DEL_FLAG_NORMAL));
		List<WorkCenterOrganization> gzzxjgs = gzzxjgDao.find(dc3);
		
		model.addAttribute("ipGzzxId", gzzxjg.getIpGzzxId());
		model.addAttribute("sb", sb);
		model.addAttribute("gzzxsbs", gzzxsbs);
		model.addAttribute("organizations", organizations);
		model.addAttribute("gzzxjgs", gzzxjgs);
		model.addAttribute("gzzx", gzzxwhService.get(gzzxjg.getIpGzzxId()));
		return "modules/" + "ip/workCenterOrganizationForm";
	}

	@RequiresPermissions("gzzx:gzzxjg:edit")
	@RequestMapping(value = "save")
	public String save(Model model, RedirectAttributes redirectAttributes,String[] ids,String ipGzzxId,String[] ids2) {
		if(null!=ids&&ids.length>0&&!ids[0].equals("")){
			for(int i=0;i<ids.length;i++){
				//保存工作中心设备
				WorkCenterEquipmentField gzzxsb=new WorkCenterEquipmentField();
				gzzxsb.setIpGzzxId(ipGzzxId);
				gzzxsb.setSbybm(new Equipment(ids[i]));
				gzzxsbDao.save(gzzxsb);
			}
			
		}
		if(null!=ids2&&!ids2[0].equals("")&&ids2.length>0){
			for(int i=0;i<ids2.length;i++){
				//保存工作中心机构
				WorkCenterOrganization gzzxjg=new WorkCenterOrganization();
				gzzxjg.setIpGzzxId(ipGzzxId);
				gzzxjg.setZzjgbm(new Organization(ids2[i]));
				gzzxjgDao.save(gzzxjg);
			}
		}
		//addMessage(redirectAttributes, "保存工作中心机构成功");
		return "";
	}
	
	@RequiresPermissions("gzzx:gzzxjg:edit")
	@RequestMapping(value = "delete")
	public String delete(String[] id, RedirectAttributes redirectAttributes,String[] ids1) {
		if(null!=id&&id.length>0){
			for(int i=0;i<id.length;i++){
				gzzxsbDao.deleteById(id[i]);
			}
			
		}
		if(null!=ids1&&ids1.length>0){
			for(int i=0;i<ids1.length;i++){
				gzzxjgDao.deleteById(ids1[i]);
			}
		}
		//gzzxjgService.delete(id);
		//addMessage(redirectAttributes, "删除工作中心机构成功");
		return "";
	}
	
	/**
	 * 方法功能：弹出工作中心人机配置主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxjg:view")
	@RequestMapping(value = "start")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxjg";
		}
		model.addAttribute("module", module);
		return "modules/ip/workCenterOrganizationIndex";
	}
	
	/**
	 * 方法功能：显示提示信息
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxjg:view")
	@RequestMapping(value = "none")
	public String none() {
		return "modules/ip/workCenterNone";
	}
	/**
	 * 方法功能：生成仓库树
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("gzzx:gzzxwh:view")
	@RequestMapping(value = "tree")
	public String tree(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="gzzxjg";
		}
		model.addAttribute("module", module);
		model.addAttribute("gzzxList", gzzxwhService.findTree(true, null));
		List<WorkCenter> list=gzzxwhService.findTree(true, null);
		return "modules/ip/workCenterTree";
	}
	
	@RequiresPermissions("gzzx:gzzxjg:view")
	@RequestMapping(value = "saveSB")
	public String saveSB(Model model,String module,RedirectAttributes redirectAttributes,String ids,String ipGzzxId,String ids1,String ids2,String ids3) {
		String[] a1=ids.split(",");
		String[] a3=ids2.split(",");
		save(model, redirectAttributes, a1, ipGzzxId,a3);
		String[] a2=ids1.split(",");
		String[] a4=ids3.split(",");
		delete(a2, redirectAttributes,a4);
		addMessage(redirectAttributes, "操作成功");
		//String info="Success！";
		return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxjg/form?ipGzzxId="+ipGzzxId;
		//return "redirect:"+Global.getAdminPath()+"/gzzx/gzzxjg";
	}
	
}
