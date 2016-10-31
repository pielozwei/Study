/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ip.dao.MaterialDao;
import com.thinkgem.jeesite.modules.ip.entity.Material;
import com.thinkgem.jeesite.modules.ip.entity.Store;
import com.thinkgem.jeesite.modules.ip.entity.StoreWhiteList;
import com.thinkgem.jeesite.modules.ip.service.MaterialService;
import com.thinkgem.jeesite.modules.ip.service.StoreService;
import com.thinkgem.jeesite.modules.ip.service.StoreWhiteListService;

/**
 * 仓库白名单Controller
 * @author Lucl
 * @version 2016-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cangku/ckbmd")
public class StoreWhiteListController extends BaseController {

	@Autowired
	private StoreWhiteListService ckbmdService;
	
	@Autowired
	private StoreService ckService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private MaterialDao materialDao;
	
	@ModelAttribute
	public StoreWhiteList get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return ckbmdService.get(id);
		}else{
			return new StoreWhiteList();
		}
	}
	
	@RequiresPermissions("cangku:ckbmd:view")
	@RequestMapping(value = {"list"})
	public String list(StoreWhiteList ckbmd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			ckbmd.setCreateBy(user);
		}
        Page<StoreWhiteList> page = ckbmdService.find(new Page<StoreWhiteList>(request, response), ckbmd,request); 
        model.addAttribute("ipCkCkbh",request.getParameter("ipCkCkbh"));
        model.addAttribute("page", page);
        model.addAttribute("ckbmd", ckbmd);
		return "modules/" + "ip/storeWhiteListList";
	}

	@RequiresPermissions("cangku:ckbmd:view")
	@RequestMapping(value = "form")
	public String form(StoreWhiteList ckbmd, Model model) {
		model.addAttribute("ckbmd", ckbmd);
		return "modules/" + "ip/storeWhiteListForm";
	}

	@RequiresPermissions("cangku:ckbmd:edit")
	@RequestMapping(value = "save")
	public String save(StoreWhiteList ckbmd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ckbmd)){
			return form(ckbmd, model);
		}
		ckbmdService.save(ckbmd);
		addMessage(redirectAttributes, "保存仓库白名单成功");
		return "redirect:"+Global.getAdminPath()+"/cangku/ckbmd/list?ipCkCkbh="+ckbmd.getIpCkCkbh();
	}
	
	@RequiresPermissions("cangku:ckbmd:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes,String ipCkCkbh) {
		ckbmdService.delete(id);
		addMessage(redirectAttributes, "删除仓库白名单成功");
		return "redirect:"+Global.getAdminPath()+"/cangku/ckbmd/list?ipCkCkbh="+ipCkCkbh;
	}
	
	/**
	 * 方法功能：点击仓库白名单管理弹出主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("cangku:ck:view")
	@RequestMapping(value = "")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="ckbmd";
		}
		model.addAttribute("module", module);
		return "modules/ip/storeIndex";
	}
	
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) Long extId,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Store ck=new Store();
		List<Store> list =  (List<Store>) ckService.findTree(true, ck);
		Store s1 = ckService.get("0");
		if(null == s1){
			Store store1 = new Store();
			store1.setId("0");
			store1.setMc("无上级节点");
			store1.setCkbh("89545678");
			store1.setWllbjc("798465");
			store1.setSfqjxdw(0);
			store1.setSfstdw(0);
			store1.setSjjd(store1);
			ckService.save(store1);
			list.add(store1);
		}
		for (int i=0; i<list.size(); i++){
			Store e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getSjjd()!=null?e.getSjjd().getId():0);
				map.put("name", e.getMc());
				mapList.add(map);
			}
		}
		return mapList;
	}
	/**
	 * 批量删除
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("cangku:ck:edit")
	@RequestMapping(value = "deleteList")
	public String deleteList(String[] ids, RedirectAttributes redirectAttributes) {
		int j=0;
		for(String id :ids){
			ckbmdService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个仓库白名单！");
		return "redirect:"+Global.getAdminPath()+"/cangku/ck/none";
	}
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData1")
	public List<Map<String, Object>> treeData1(@RequestParam(required=false) Long extId,HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
	//	Store ck=new Store();
		Material material = new Material();
	//	List<Store> list =  (List<Store>) ckService.findTree(true, ck);
		DetachedCriteria detachedCriteria=materialDao.createDetachedCriteria();
		List<Material> list =  (List<Material>) materialDao.find(detachedCriteria);
		for (int i=0; i<list.size(); i++){
			Material e = list.get(i);
			if (extId == null || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId",0);
				map.put("name", e.getWlbm());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}
