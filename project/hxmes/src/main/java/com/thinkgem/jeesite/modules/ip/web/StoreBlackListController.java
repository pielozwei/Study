/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite.modules.ip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.modules.ip.entity.StoreBlackList;
import com.thinkgem.jeesite.modules.ip.service.StoreBlackListService;

/**
 * 仓库黑名单Controller
 * @author Lucl
 * @version 2016-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/cangku/ckhmd")
public class StoreBlackListController extends BaseController {

	@Autowired
	private StoreBlackListService ckhmdService;
	
	@ModelAttribute
	public StoreBlackList get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return ckhmdService.get(id);
		}else{
			return new StoreBlackList();
		}
	}
	
	@RequiresPermissions("cangku:ckhmd:view")
	@RequestMapping(value = {"list"})
	public String list(StoreBlackList ckhmd, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (!user.isAdmin()){
			ckhmd.setCreateBy(user);
		}
        Page<StoreBlackList> page = ckhmdService.find(new Page<StoreBlackList>(request, response), ckhmd); 
        model.addAttribute("ipCkCkbh",ckhmd.getIpCkCkbh());
        model.addAttribute("page", page);
        model.addAttribute("ckhmd",ckhmd);
		return "modules/" + "ip/storeBlackListList";
	}

	@RequiresPermissions("cangku:ckhmd:view")
	@RequestMapping(value = "form")
	public String form(StoreBlackList ckhmd, Model model) {
		model.addAttribute("ckhmd", ckhmd);
		return "modules/" + "ip/storeBlackListForm";
	}

	@RequiresPermissions("cangku:ckhmd:edit")
	@RequestMapping(value = "save")
	public String save(StoreBlackList ckhmd, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ckhmd)){
			return form(ckhmd, model);
		}
		ckhmdService.save(ckhmd);
		addMessage(redirectAttributes, "保存仓库黑名单成功");
		return "redirect:"+Global.getAdminPath()+"/cangku/ckhmd/list?ipCkCkbh="+ckhmd.getIpCkCkbh();
	}
	
	@RequiresPermissions("cangku:ckhmd:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes,String ipCkCkbh) {
		ckhmdService.delete(id);
		addMessage(redirectAttributes, "删除仓库黑名单成功");
		return "redirect:"+Global.getAdminPath()+"/cangku/ckhmd/list?ipCkCkbh="+ipCkCkbh;
	}
	
	/**
	 * 方法功能：点击仓库黑名单管理弹出主界面
	 * @param model
	 * @param module
	 * @return
	 */
	@RequiresPermissions("cangku:ckhmd:view")
	@RequestMapping(value = "")
	public String index(Model model,String module) {
		if (StringUtils.isEmpty(module)){
			module="ckhmd";
		}
		model.addAttribute("module", module);
		return "modules/ip/storeIndex";
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
			ckhmdService.delete(id);
			j++;
		}
		addMessage(redirectAttributes, "删除"+j+"个仓库黑名单！");
		return "redirect:"+Global.getAdminPath()+"/cangku/ck/none";
	}
}
